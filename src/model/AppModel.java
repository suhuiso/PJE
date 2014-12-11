package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import utils.MessageCleaner;
import utils.Tweet;
import utils.TweetPool;
import feeling.Classifier;
import feeling.DictionaryClassifier;
import feeling.Feeling;
import feeling.KNNClassifier;
import feeling.PresenceBayesClassifier;

/**
 * Model of the application. Handles the data.
 * 
 * @author Quentin Baert & Thomas Bernard
 */
public class AppModel extends Observable {

	////////////
	// FILEDS //
	////////////

	/**
	 * Singleton representing the Twitter API.
	 */
	private final Twitter TWITTER = TwitterFactory.getSingleton();

	/**
	 * TweetPool of the model.
	 */
	private TweetPool tweetPool;

	/**
	 * Message cleaner.
	 */
	private MessageCleaner msgCleaner;

	/**
	 * Classifiers available in the application.
	 */
	private Classifier[] classifiers;

	/**
	 * Number of tweets recover in one search.
	 */
	private int tweetsNb;

	/////////////
	// METHODS //
	/////////////

	/**
	 * Constructor of a AppModel. The model of the application building a tweet pool with a file
	 * named "tweetPool.csv".
	 */
	public AppModel () {
		this.tweetPool = new TweetPool( "resources/tweetPool.csv" );
		this.msgCleaner = MessageCleaner.getInstance();
		// TODO Mettre les classifieurs réels
		this.classifiers = new Classifier[ 5 ];
		this.classifiers[ 0 ] =
		        new DictionaryClassifier( "./resources/positive.txt", "./resources/negative.txt" );
		this.classifiers[ 1 ] = new KNNClassifier( this.tweetPool, 5 );
		this.classifiers[ 2 ] =
		        new PresenceBayesClassifier( this.tweetPool, false, new ArrayList< Integer >( 1 ) );
		this.classifiers[ 3 ] =
		        new PresenceBayesClassifier( this.tweetPool, true, new ArrayList< Integer >( 1 ) );
		this.classifiers[ 4 ] =
		        new PresenceBayesClassifier( this.tweetPool, false, new ArrayList< Integer >( 2 ) );
		this.tweetsNb = 25;
	}

	/**
	 * Gives the classifier with the id
	 * 
	 * @param id
	 *            id of the classifier
	 * @return classifier with this id
	 */
	public Classifier getClassifierById ( int id ) {
		return this.classifiers[ id ];
	}

	/**
	 * Makes a research in the Twitter API.
	 * 
	 * @param searchQuery
	 *            string representing the query
	 */
	public void search ( String searchQuery ) {
		Query query = new Query( searchQuery );
		QueryResult result = null;
		List< Tweet > res = new ArrayList< Tweet >();

		query.setLang( "fr" );
		query.setCount( this.tweetsNb );

		try {
			result = this.TWITTER.search( query );

			// TODO : Gérer le fait que res ne possède potentiellement pas assez de tweets

			for ( Status status : result.getTweets() ) {
				if ( !status.isRetweet() ) {
					res.add( new Tweet( status, searchQuery, Feeling.UNPOLARIZED ) );
				}
			}

			// Debug
			for ( Tweet tweet : res ) {
				System.out.println( tweet );
			}

			this.setChanged();
			this.notifyObservers( res );
		} catch ( TwitterException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Save the tweet with the feeling in the tweet pool.
	 * 
	 * @param tweet
	 *            tweet to save
	 * @param feeling
	 *            feeling of the tweet
	 */
	public void save ( Tweet tweet, Feeling feeling ) {
		tweet = this.msgCleaner.cleanTweet( tweet );
		tweet.setFeeling( feeling );
		String content = tweet.getMsg();
		Long id = tweet.getId();

		// A tweet is saved if it is not only composed of whitespaces
		// A tweet is saved if it is not already saved 
		if ( ( !content.trim().isEmpty() ) && ( !this.tweetPool.containsKey( id ) ) ) {
			this.tweetPool.put( id, tweet );
		}
	}

	/**
	 * Saves the tweet pool of the model in a CSV file.
	 */
	public void closingWindowSave () {
		this.tweetPool.writeCSV( "resources/tweetPool.csv" );
	}
}
