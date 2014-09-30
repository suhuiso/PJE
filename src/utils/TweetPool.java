package utils;

import java.util.HashMap;

import twitter4j.Status;

/**
 * // TODO
 * // SINGLETON 
 */
public class TweetPool {

	private HashMap< Long, Status > tweetPool;

	public TweetPool ( HashMap< Long, Status > tweetPool ) {
		this.tweetPool = tweetPool;
	}

}
