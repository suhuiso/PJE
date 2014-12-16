package model;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import statistics.CrossValidation;
import statistics.PieChartBuilder;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import utils.MessageCleaner;
import utils.Tweet;
import utils.TweetPool;
import feeling.Classifier;
import feeling.CrossValidable;
import feeling.DictionaryClassifier;
import feeling.Feeling;
import feeling.FrequencyBayesClassifier;
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
	private Twitter twitter;

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
	private int nbTweets;

	/////////////
	// METHODS //
	/////////////

	/**
	 * Constructor of a AppModel. The model of the application building a tweet pool with a file
	 * named "tweetPool.csv".
	 */
	public AppModel () {
		this.twitter = TwitterFactory.getSingleton();
		this.tweetPool = new TweetPool( "resources/tweetPool.csv" );
		this.msgCleaner = MessageCleaner.getInstance();

		// Classifiers
		this.classifiers = new Classifier[ 14 ];

		// Lists representing unigram, bigram, and unigram + bigram parameters 
		List< Integer > uni = new ArrayList< Integer >();
		uni.add( 1 );
		List< Integer > bi = new ArrayList< Integer >();
		bi.add( 2 );
		List< Integer > uniAndBi = new ArrayList< Integer >();
		uniAndBi.add( 1 );
		uniAndBi.add( 2 );

		// Classifiers initialisation

		// Dictionnary
		this.classifiers[ 0 ] =
		        new DictionaryClassifier( "./resources/positive.txt", "./resources/negative.txt" );
		// KNN
		this.classifiers[ 1 ] = new KNNClassifier( this.tweetPool, 5 );
		// Presence Bayes
		this.classifiers[ 2 ] = new PresenceBayesClassifier( this.tweetPool, false, uni );
		this.classifiers[ 3 ] = new PresenceBayesClassifier( this.tweetPool, true, uni );
		this.classifiers[ 4 ] = new PresenceBayesClassifier( this.tweetPool, false, bi );
		this.classifiers[ 5 ] = new PresenceBayesClassifier( this.tweetPool, true, bi );
		this.classifiers[ 6 ] = new PresenceBayesClassifier( this.tweetPool, false, uniAndBi );
		this.classifiers[ 7 ] = new PresenceBayesClassifier( this.tweetPool, true, uniAndBi );
		// Frequency Bayes
		this.classifiers[ 8 ] = new FrequencyBayesClassifier( this.tweetPool, false, uni );
		this.classifiers[ 9 ] = new FrequencyBayesClassifier( this.tweetPool, true, uni );
		this.classifiers[ 10 ] = new FrequencyBayesClassifier( this.tweetPool, false, bi );
		this.classifiers[ 11 ] = new FrequencyBayesClassifier( this.tweetPool, true, bi );
		this.classifiers[ 12 ] = new FrequencyBayesClassifier( this.tweetPool, false, uniAndBi );
		this.classifiers[ 13 ] = new FrequencyBayesClassifier( this.tweetPool, true, uniAndBi );

		// Default tweetsNb
		this.nbTweets = 25;
	}

	/**
	 * Updates Twitter configuration to use Lille 1 Proxy
	 */
	public void setProxyTwitter () {
		ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
		configurationBuilder.setHttpProxyHost( "cache-etu.univ-lille1.fr" );
		configurationBuilder.setHttpProxyPort( 3128 );

		this.twitter = new TwitterFactory( configurationBuilder.build() ).getInstance();
	}

	/**
	 * Updates Twitter configuration to not use Lille 1 Proxy
	 */
	public void unsetProxyTwitter () {
		ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();

		this.twitter = new TwitterFactory( configurationBuilder.build() ).getInstance();
	}

	/**
	 * Gives the number of tweets to display.
	 * 
	 * @return number of tweets
	 */
	public int getTweetsNb () {
		return this.nbTweets;
	}

	/**
	 * Set the number of tweets to display.
	 * 
	 * @param newValue
	 *            new number of tweets
	 */
	public void setTweetsNb ( int newValue ) {
		this.nbTweets = newValue;
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
		query.setCount( this.nbTweets );

		try {
			result = this.twitter.search( query );

			// Try to have extactly nbTweets tweets in the res list
			while ( result.hasNext() && ( res.size() < this.nbTweets ) ) {
				List< Status > list = result.getTweets();
				int i = 0;

				while ( ( i < list.size() ) && ( res.size() < this.nbTweets ) ) {
					Status status = list.get( i );
					if ( !status.isRetweet() ) {
						res.add( new Tweet( status, searchQuery, Feeling.UNPOLARIZED ) );
					}
					i++;
				}

				query = result.nextQuery();
				result = this.twitter.search( query );
			}

			this.setChanged();
			this.notifyObservers( res );
		} catch ( TwitterException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Classifies the tweet with the given classifier.
	 * 
	 * @param tweet
	 *            tweet to classify
	 * @param classifier
	 *            classifier to use to classify the tweet
	 */
	public void classifies ( Tweet tweet, Classifier classifier ) {
		tweet.setFeeling( classifier.classifies( tweet.getMsg() ) );
	}

	/**
	 * Saves the tweet with the feeling in the tweet pool.
	 * 
	 * @param tweet
	 *            tweet to save
	 * @param feeling
	 *            feeling of the tweet
	 */
	public void saves ( Tweet tweet, Feeling feeling ) {
		this.msgCleaner.cleanTweet( tweet );
		String content = tweet.getMsg();

		// A tweet is saved if it is not only composed of whitespaces
		if ( !content.trim().isEmpty() ) {
			tweet.setFeeling( feeling );
			this.tweetPool.add( tweet );
		}
	}

	/**
	 * Gives a pie char image from a classifier and list of tweets.
	 * 
	 * @param classifier
	 *            classifier used for the classification
	 * @param tweets
	 *            tweets to classify
	 * @return pie char image from a classifier and list of tweets
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public Image generatePieChartImage ( Classifier classifier, List< Tweet > tweets )
	        throws MalformedURLException, IOException {
		return new PieChartBuilder( classifier, tweets.get( 0 ).getQuery(), tweets )
		        .getPieChartImage();
	}

	/**
	 * Saves the tweet pool of the model in a CSV file.
	 */
	public void closingWindowSave () {
		this.tweetPool.writeCSV( "resources/tweetPool.csv" );
	}

	/**
	 * Build a CrossValidation object to evaluate the classifier
	 * 
	 * @param classifier
	 *            classifier to evaluate
	 */
	public void evaluateClassifier ( Classifier classifier ) {
		if ( classifier.isCrossValidable() ) {
			CrossValidation crossValidation =
			        new CrossValidation( this.tweetPool, ( CrossValidable ) classifier, 10 );
			Double res = crossValidation.evaluates();
			this.setChanged();
			this.notifyObservers( res );
		}
	}
}
