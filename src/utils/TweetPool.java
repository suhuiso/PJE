package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.HashMap;

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
	 * Hash map to save tweets.
	 */
	private HashMap< Long, Tweet > tweetPool;

	/////////////
	// METHODS //
	/////////////

	/**
	 * Constroctor of a TweetPool.
	 * 
	 * @param path
	 *            path of the CSV file where tweets has been previously saved.
	 */
	public TweetPool ( String path ) {
		this.tweetPool = new HashMap< Long, Tweet >();
		File file = new File( path );

		// If a file already exists, it is open to fill the map
		if ( file.exists() && !file.isDirectory() ) {
			try {
				BufferedReader br = new BufferedReader( new FileReader( path ) );
				String line = "";

				while ( ( line = br.readLine() ) != null ) {
					String[] elem = line.split( "," );

					Tweet tweet =
					        new Tweet( Long.parseLong( elem[ 0 ] ), elem[ 1 ], elem[ 2 ],
					                DateFormat.getDateInstance( DateFormat.MEDIUM ).parse( elem[ 3 ] ), elem[ 4 ],
					                Integer.parseInt( elem[ 5 ] ) );

					this.tweetPool.put( tweet.getId(), tweet );
				}

				br.close();
			} catch ( FileNotFoundException e ) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch ( IOException e ) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch ( NumberFormatException e ) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch ( ParseException e ) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Tells if the TweetPool contains an id as key.
	 * 
	 * @param id
	 *            id to know if it is contained in the hash map
	 * @return true if id is a key in the hash map, false otherwise
	 */
	public boolean containsKey ( long id ) {
		return this.tweetPool.containsKey( id );
	}

}
