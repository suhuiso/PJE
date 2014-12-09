package model;

import java.util.ArrayList;
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
import feeling.DefaultClassifier;
import feeling.DictionaryClassifier;
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
	 * Classifiers available in the application
	 */
	private Classifier[] classifiers;
	
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
		this.classifiers[ 0 ] = new DictionaryClassifier( "./resources/positive.txt", "./resources/negative.txt" );
		this.classifiers[ 1 ] = new KNNClassifier( this.tweetPool, 5 );
		this.classifiers[ 2 ] = new PresenceBayesClassifier( this.tweetPool, false, new ArrayList<Integer>( 1 ) );
		this.classifiers[ 3 ] = new PresenceBayesClassifier( this.tweetPool, true, new ArrayList<Integer>( 1 ) );
		this.classifiers[ 4 ] = new PresenceBayesClassifier( this.tweetPool, false, new ArrayList<Integer>( 2 ) );
	}

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

		try {
			result = this.TWITTER.search( query );
		} catch ( TwitterException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Debug
		for ( Status status : result.getTweets() ) {
			System.out.println( "@" + status.getUser().getScreenName() + ":" + status.getText() );
		}

		this.setChanged();
		this.notifyObservers( result );
	}

	// Save the results of a query using the feeling assigner passed in parameter
	private void save ( QueryResult result, Classifier classifier ) {
		for ( Status status : result.getTweets() ) {
			// Tweet created from status
			Tweet tweet =
			        new Tweet( status, result.getQuery(), classifier.classifies( status.getText() ) );
			// Cleaning tweet message
			tweet = this.msgCleaner.cleanTweet( tweet );

			String content = tweet.getMsg();
			Long id = tweet.getId();

			// A tweet is saved if it is not only composed of whitespaces
			// A tweet is saved if it is not already saved 
			if ( ( !content.trim().isEmpty() ) && ( !this.tweetPool.containsKey( id ) ) ) {
				this.tweetPool.put( id, tweet );
			}
		}
	}

	/**
	 * Saves the results of a query in a file named "tweetPool.csv".
	 * The tweets which are saved are non polarized.
	 * 
	 * @param result
	 *            result of a query previously made
	 */
	public void unpolarizedSave ( QueryResult result ) {
		this.save( result, DefaultClassifier.getInstance() );
	}

	/**
	 * Saves the results of a query in a file named "tweetPool.csv".
	 * The tweets which are saved are polarized with a dictionnary method.
	 * 
	 * @param result
	 *            result of a query previously made
	 */
	public void dictionarySave ( QueryResult result ) {
		this.save( result, new DictionaryClassifier( "resources/positive.txt",
		        "resources/negative.txt" ) );
	}

	/**
	 * Saves the tweet pool of the model in a CSV file.
	 */
	public void closingWindowSave () {
		this.tweetPool.writeCSV( "resources/tweetPool.csv" );
	}
}
