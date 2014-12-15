package feeling;

import utils.TweetPool;

/**
 * Abstract class representing classifier that are cross validable.
 * 
 * @author Quentin Baert & Thomas Bernard
 */
public abstract class CrossValidable extends Classifier {

	////////////
	// FIELDS //
	////////////

	/**
	 * Tweet pool using by the classifier as learning base.
	 */
	protected TweetPool tweetPool;

	/////////////
	// METHODS //
	/////////////

	/**
	 * Constructor.
	 * 
	 * @param tweetPool
	 *            learning base used for classification
	 */
	public CrossValidable ( TweetPool tweetPool ) {
		this.tweetPool = tweetPool;
	}

	/**
	 * Sets the tweet pool of the cross validable classifier.
	 * 
	 * @param newTweetPool
	 *            new tweet pool of the cross validable classifier
	 */
	public void setTweetPool ( TweetPool newTweetPool ) {
		this.tweetPool = newTweetPool;
	}

}
