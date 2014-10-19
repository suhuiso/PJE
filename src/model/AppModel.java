package model;

import java.util.Observable;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import utils.Feeling;
import utils.MessageCleaner;
import utils.Tweet;
import utils.TweetPool;

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

	/**
	 * Saves the results of a query in a file named "tweetPool.csv".
	 * 
	 * @param result
	 *            result of a query previously made
	 */
	public void unpolarizedSave ( QueryResult result ) {
		MessageCleaner msgCleaner = MessageCleaner.getInstance();

		for ( Status status : result.getTweets() ) {
			// Tweet created from status
			Tweet tweet = new Tweet( status, result.getQuery(), Feeling.UNPOLARIZED );
			// Cleaning tweet message
			tweet.setMsg( msgCleaner.cleanText( tweet.getMsg() ) );

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
	 * Saves the tweet pool of the model in a CSV file.
	 */
	public void closingWindowSave () {
		this.tweetPool.writeCSV( "resources/tweetPool.csv" );
	}

}
