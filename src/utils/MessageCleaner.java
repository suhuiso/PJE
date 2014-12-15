package utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MessageCleaner {

	////////////
	// FILEDS //
	////////////

	/**
	 * Only accessible instance of MessageCleaner.
	 */
	private static final MessageCleaner INSTANCE = new MessageCleaner();

	/**
	 * List of methods to apply to clean a message.
	 */
	private List< Method > methods;

	/////////////
	// METHODS //
	/////////////

	// Unaccessible constructor of MessageCleaner
	private MessageCleaner () {
		this.methods = new ArrayList< Method >();

		for ( Method m : this.getClass().getMethods() ) {
			if ( m.getName().startsWith( "delete" ) ) {
				this.methods.add( m );
			}
		}
	}

	/**
	 * Gives the only instance of MessageCleaner.
	 * 
	 * @return only instance of MessageCleaner
	 */
	public static MessageCleaner getInstance () {
		return INSTANCE;
	}

	/**
	 * Removes double quotes from a message.
	 * 
	 * @param s
	 *            string in which remove double quotes
	 * @return s with spaces instaed of double quotes
	 */
	public String deleteDoubleQuotes ( String s ) {
		return s.replace( '"', ' ' );
	}

	/**
	 * Removes coma from a message.
	 * 
	 * @param s
	 *            string in which remove coma
	 * @return s with spaces instead of comas
	 */
	public String deleteComa ( String s ) {
		return s.replace( ',', ' ' );
	}

	/**
	 * Removes new line chars from a message.
	 * 
	 * @param s
	 *            string in which remove new line chars
	 * @return s with spaces instead of new line chars
	 */
	public String deleteNewLineChar ( String s ) {
		return s.replace( '\n', ' ' );
	}

	/**
	 * Removes user names from a message.
	 * 
	 * @param s
	 *            string in which remove usernames
	 * @return s without usernames
	 */
	public String deleteUsername ( String s ) {
		return s.replaceAll( "@[A-Za-z0-9_-]+", "" );
	}

	/**
	 * Reomves hashtags from a message.
	 * 
	 * @param s
	 *            string in which remove hashtags
	 * @return s without hashtags
	 */
	public String deleteHashtag ( String s ) {
		return s.replaceAll( "#[A-Za-z0-9_-]+", "" );
	}

	/**
	 * Removes urls from a message.
	 * 
	 * @param s
	 *            string in which remove urls
	 * @return s without urls
	 */
	public String deleteHttpUrl ( String s ) {
		return s.replaceAll( "http[s]?://[^\\s]+", "" );
	}

	/**
	 * Removes numbers from a message.
	 * 
	 * @param s
	 *            string in which remove numbers
	 * @return s with "X" instead of numbers
	 */
	public String deleteNumbers ( String s ) {
		return s.replaceAll( "[0-9]+", "X" );
	}

	/**
	 * Removes extra spaces from a message.
	 * 
	 * @param s
	 *            string in which remove extra spaces
	 * @return s without extra spaces
	 */
	public String deleteExtraSpaces ( String s ) {
		return s.replaceAll( "\\s+", " " );
	}

	/**
	 * Cleans a message using all previous methods.
	 * 
	 * @param text
	 *            text to clean
	 * @return clening text
	 */
	public String cleanText ( String text ) {
		String s = text;

		for ( Method m : this.methods ) {
			try {
				s = ( String ) m.invoke( this, s );
			} catch ( Exception e ) {
				// TODO
				e.printStackTrace();
			}
		}

		return s;
	}

	/**
	 * Cleans a tweet message using all previous methods.
	 * 
	 * @param tweet
	 *            tweet to clean
	 * @return cleaning tweet
	 */
	public void cleanTweet ( Tweet tweet ) {
		tweet.setMsg( this.cleanText( tweet.getMsg() ) );
	}
}
