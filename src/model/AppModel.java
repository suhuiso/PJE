package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
		for ( Status status : result.getTweets() ) {
			// Tweet created from status
			Tweet tweet = new Tweet( status, result.getQuery(), Feeling.UNPOLARIZED );
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

	// Load a file into a string
	private String fileToString ( String path ) {
		StringBuffer res = new StringBuffer();
		File file = new File( path );

		if ( file.exists() && !file.isDirectory() ) {
			try {
				BufferedReader br = new BufferedReader( new FileReader( path ) );
				String line = "";

				while ( ( line = br.readLine() ) != null ) {
					res.append( line );
				}

				br.close();
			} catch ( FileNotFoundException e ) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch ( IOException e ) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return res.toString();
	}

	// Gives a feeling to a tweet using dictonaries
	private Feeling msgDictionaryPolarize ( String msg, String[] positiveWords,
	        String[] negativeWords ) {
		int cpt = 0;

		for ( String positiveWord : positiveWords ) {
			if ( msg.contains( positiveWord ) ) {
				cpt++;
			}
		}

		for ( String negativeWord : negativeWords ) {
			if ( msg.contains( negativeWord ) ) {
				cpt--;
			}
		}

		if ( cpt < 0 ) {
			return Feeling.NEGATIVE;
		} else if ( cpt > 0 ) {
			return Feeling.POSITIVE;
		} else {
			return Feeling.NEUTRAL;
		}
	}

	/*
	 * ATTENTION : Répétition de code avec unpolarizedSave ici.
	 * Pour éviter cela ?
	 *  - Faire une méthode générique prend un objet de type FeelingAllocator en paramètre ?
	 *  - Différencier la méthode d'attribution des sentiments dans différents objets de type FeelingAllocator ?
	 */

	/**
	 * Polarizes all tweets of the tweet pool based on dictonary files.
	 */
	public void dictionaryPolarize ( QueryResult result ) {
		String[] positiveWords = this.fileToString( "resources/positive.txt" ).split( "," );
		String[] negativeWords = this.fileToString( "resources/negative.txt" ).split( "," );

		for ( Status status : result.getTweets() ) {
			// Tweet created from status
			Tweet tweet =
			        new Tweet( status, result.getQuery(), this.msgDictionaryPolarize(
			                status.getText(), positiveWords, negativeWords ) );
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
	 * Saves the tweet pool of the model in a CSV file.
	 */
	public void closingWindowSave () {
		this.tweetPool.writeCSV( "resources/tweetPool.csv" );
	}

}
