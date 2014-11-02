package feeling;

/**
 * Abstract class representing objects that assign a feeling to a message of a tweet.
 * 
 * @author Quentin Baert & Thomas Bernard
 */
public abstract class FeelingAssigner {

	/**
	 * Assigns a feeling to a tweet.
	 * 
	 * @param msg
	 *            message to analyse and to give a feeling
	 * @return feeling of the tweet
	 */
	public abstract Feeling assigns ( String msg );

}
