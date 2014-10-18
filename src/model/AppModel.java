package model;

import java.util.Observable;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
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

	/////////////
	// METHODS //
	/////////////

	/**
	 * Constructor of a AppModel. The model of the application building a tweet pool with a file
	 * named "tweetPool.csv".
	 */
	public AppModel () {
		this.tweetPool = new TweetPool( "resources/tweetPool.csv" );
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

	/*
	 * Cleaning tweet messages methods
	 */

	private String deleteDoubleQuotes ( String s ) {
		return s.replace( '"', ' ' );
	}

	private String deleteComa ( String s ) {
		return s.replace( ',', ' ' );
	}

	private String deleteNewLineChar ( String s ) {
		return s.replace( '\n', ' ' );
	}

	private String deleteUsername ( String s ) {
		return s.replaceAll( "@[A-Za-z0-9_-]+", "" );
	}

	private String deleteHashtag ( String s ) {
		return s.replaceAll( "#[A-Za-z0-9_-]+", "" );
	}

	private String deleteHttpUrl ( String s ) {
		return s.replaceAll( "http[s]?://[^\\s]+", "" );
	}

	private String deleteRT ( String s ) {
		return s.replaceAll( "RT\\s?\"[\\w\\s\\d]+\"", "" );
	}

	private String cleanText ( String text ) {
		return deleteHttpUrl( deleteRT( deleteHashtag( deleteUsername( deleteNewLineChar( deleteComa( deleteDoubleQuotes( text ) ) ) ) ) ) );
	}

	/**
	 * Saves the results of a query in a file named "tweetPool.csv".
	 * 
	 * @param result
	 *            result of a query previously made
	 */
	public void unpolarizedSave ( QueryResult result ) {
		for ( Status status : result.getTweets() ) {
			// Tweet created from status
			Tweet tweet = new Tweet( status, result.getQuery(), -1 );
			// Cleaning tweet message
			tweet.setMsg( this.cleanText( tweet.getMsg() ) );
			
			String content = tweet.getMsg();
			Long id = tweet.getId();

			// A tweet is saved if it is not only composed of whitespaces
			// A tweet is saved if it is not already saved 
			if ( ( !content.trim().isEmpty() ) && ( !this.tweetPool.containsKey( id ) ) ) {
				this.tweetPool.put( id, tweet );
			}
		}
	}

}
