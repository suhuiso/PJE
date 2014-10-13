package utils;

import java.util.Date;

/**
 * Class reprsenting a tweet as we want to save it.
 * 
 * @author Quentin Baert & Thomas Bernard
 */
public class Tweet {

	// //////////
	// FIELDS //
	// //////////

	/**
	 * Unique identifier of a tweet.
	 */
	private Long id;

	/**
	 * Pseudo of the person who tweets.
	 */
	private String twittos;

	/**
	 * Message of the tweet.
	 */
	private String msg;

	/**
	 * Date when the tweet was sent.
	 */
	private Date date;

	/**
	 * Request with which the tweet was obtained.
	 */
	private String request;

	// ///////////
	// METHODS //
	// ///////////

	/**
	 * Constructor of a Tweet.
	 * 
	 * @param id
	 *            unique identifier of the tweet
	 * @param twittos
	 *            person who sent the tweet
	 * @param msg
	 *            message of the tweet
	 * @param date
	 *            date when the tweet was sent
	 * @param request
	 *            request with which the tweet was obtained
	 */
	public Tweet ( Long id, String twittos, String msg, Date date, String request ) {
		this.id = id;
		this.twittos = twittos;
		this.msg = msg;
		this.date = date;
		this.request = request;
	}

	/**
	 * Gives the identifier of the tweet.
	 * 
	 * @return identifier of the tweet
	 */
	public Long getId () {
		return this.id;
	}

	/**
	 * Gives the pseudo of the person who sent the tweet.
	 * 
	 * @return pseudo of the person who sent the tweet
	 */
	public String getTwittos () {
		return this.twittos;
	}

	/**
	 * Gives the message of the tweet.
	 * 
	 * @return message of the tweet
	 */
	public String getMsg () {
		return this.msg;
	}

	/**
	 * Gives the date when the tweet was sent.
	 * 
	 * @return date when the tweet was sent
	 */
	public Date getDate () {
		return this.date;
	}

	/**
	 * Gives the request with which the tweet was obtained.
	 * 
	 * @return request with which the tweet was obtained
	 */
	public String getRequest () {
		return this.request;
	}

	/**
	 * Gives the hash code of a tweet. A tweet is uniquely identify with its identifier.
	 */
	@Override
	public int hashCode () {
		return this.getId().hashCode();
	}

}
