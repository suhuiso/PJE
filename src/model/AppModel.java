package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Observable;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
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

	/**
	 * Saves the results of a query in a file named "tweetPool.csv".
	 * 
	 * @param result
	 *            result of a query previously made
	 */
	public void unpolarizedSave ( QueryResult result ) {
		try {
			BufferedWriter out =
			        new BufferedWriter( new FileWriter( "resources/tweetPool.csv", true ) );

			for ( Status status : result.getTweets() ) {
				String content = status.getText().replace( '"', ' ' ).replace( '\n', ' ' );

				String tweet =
				        status.getId() + ";" + status.getUser().getScreenName() + ";" + "\""
				                + content + "\"" + ";" + status.getCreatedAt() + ";"
				                + result.getQuery() + ";" + "-1";
				out.write( tweet );
				out.newLine();
			}

			out.close();
		} catch ( IOException e1 ) {
			e1.printStackTrace();
		}
	}

	private String replaceUsername ( String s ) {
		return s.replaceAll( "@[A-Za-z0-9_-]+", "@" );
	}
}
