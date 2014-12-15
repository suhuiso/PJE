package feeling;

/**
 * Abstract class representing objects that classify a message of a tweet.
 * 
 * @author Quentin Baert & Thomas Bernard
 */
public abstract class Classifier {

	/**
	 * Classifies a tweet.
	 * 
	 * @param msg
	 *            message to analyse and to give a feeling
	 * @return feeling of the tweet
	 */
	public abstract Feeling classifies ( String msg );

	/**
	 * Tells if the classifier is cross validable.
	 * 
	 * @return true if the classifier is cross validable, false otherwise
	 */
	public boolean isCrossValidable () {
		return false;
	}

}
