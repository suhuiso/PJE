package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import feeling.Feeling;

/**
 * Pool of tweets reprsenting tweets that are already saved and tweets that going to be saved.
 * 
 * @author Quentin Baert & Thomas Bernard
 */
public class TweetPool {

	////////////
	// FILEDS //
	////////////

	/**
	 * Set to save tweets.
	 */
	private Map< Long, Tweet > tweetPool;

	/////////////
	// METHODS //
	/////////////

	/**
	 * Constructor of a TweetPool.
	 * 
	 * @param path
	 *            path of the CSV file where tweets has been previously saved
	 */
	public TweetPool ( String path ) {
		this.tweetPool = new HashMap< Long, Tweet >();
		this.readCSV( path );
	}

	/**
	 * Simple constructor.
	 */
	public TweetPool () {
		this.tweetPool = new HashMap< Long, Tweet >();
	}

	/**
	 * Gives an access to the tweets of the tweet pool.
	 */
	public Collection< Tweet > tweets () {
		return this.tweetPool.values();
	}

	/**
	 * Reads a CSV file and fill the tweet pool with.
	 * 
	 * @param path
	 *            path of the CSV file where tweets has been previously saved
	 */
	public void readCSV ( String path ) {
		File file = new File( path );

		// If a file already exists, it is open to fill the map
		if ( file.exists() && !file.isDirectory() ) {
			try {
				BufferedReader br = new BufferedReader( new FileReader( path ) );
				String line = "";

				while ( ( line = br.readLine() ) != null ) {
					String[] elem = line.split( "," );

					Tweet tweet =
					        new Tweet( Long.parseLong( elem[ 0 ] ), elem[ 1 ], elem[ 2 ].substring(
					                1, elem[ 2 ].length() - 1 ), new Date( new Long( elem[ 3 ] ) ),
					                elem[ 4 ],
					                Feeling.createByValue( Integer.parseInt( elem[ 5 ] ) ) );

					this.add( tweet );
				}

				br.close();
			} catch ( FileNotFoundException e ) {
				System.out.println( "Error: file " + path + " not found" );
				e.printStackTrace();
			} catch ( IOException e ) {
				e.printStackTrace();
			} catch ( NumberFormatException e ) {
				System.out.println( "Error: Integer cast failed");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Writes the tweet pool in a CSV file.
	 * 
	 * @param path
	 *            path of the CSV file where write the tweet pool
	 */
	public void writeCSV ( String path ) {
		try {
			BufferedWriter out = new BufferedWriter( new FileWriter( path, false ) );

			for ( Tweet tweet : this.tweets() ) {
				String msg = tweet.getMsg();
				StringBuffer tweetText =
				        new StringBuffer( tweet.getId() + "," + tweet.getTwittos() + "," );

				if ( msg.charAt( 0 ) == '"' && msg.charAt( msg.length() - 1 ) == '"' ) {
					tweetText.append( tweet.getMsg() );
				} else {
					tweetText.append( "\"" + tweet.getMsg() + "\"" );
				}

				tweetText.append( "," + tweet.getDate().getTime() + "," + tweet.getQuery() + ","
				        + tweet.getFeeling().getValue() );

				out.write( tweetText.toString() );
				out.newLine();
			}

			out.close();
		} catch ( IOException e1 ) {
			e1.printStackTrace();
		}
	}

	/**
	 * Adds a tweet in the tweet pool.
	 *
	 * @param tweet
	 *            tweet to add
	 */
	public void add ( Tweet tweet ) {
		this.tweetPool.put( tweet.getId(), tweet );
	}

	/**
	 * Removes a tweet in the tweet pool.
	 * 
	 * @param tweet
	 *            tweet to remove
	 */
	public void remove ( Tweet tweet ) {
		this.tweetPool.remove( tweet );
	}

	/**
	 * Clears the tweet pool.
	 */
	public void clear () {
		this.tweetPool.clear();
	}

}
