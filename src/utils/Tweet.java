package utils;

import java.util.Date;

import feeling.Feeling;
import twitter4j.Status;

/**
 * Class reprsenting a tweet as we want to save it.
 * 
 * @author Quentin Baert & Thomas Bernard
 */
public class Tweet {

	////////////
	// FIELDS //
	////////////

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
	 * Query with which the tweet was obtained.
	 */
	private String query;

	/**
	 * Feeling of the tweet.
	 * -1 for untreated tweet,
	 * 0 for negative tweet,
	 * 2 for neutral tweet,
	 * 4 for positive tweet
	 */
	private Feeling feeling;

	/////////////
	// METHODS //
	/////////////

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
	 * @param query
	 *            query with which the tweet was obtained
	 * @param feeling
	 *            feeling of the tweet
	 */
	public Tweet ( Long id, String twittos, String msg, Date date, String query, Feeling feeling ) {
		this.id = id;
		this.twittos = twittos;
		this.msg = msg;
		this.date = date;
		this.query = query;
		this.feeling = feeling;
	}

	/**
	 * Constructor of a Tweet since a Status object.
	 * 
	 * @param status
	 *            status in which build the tweet
	 * @param query
	 *            query with which the tweet was obtained
	 * @param feeling
	 *            feeling of the tweet
	 */
	public Tweet ( Status status, String query, Feeling feeling ) {
		this.id = status.getId();
		this.twittos = status.getUser().getScreenName();
		this.msg = status.getText();
		this.date = status.getCreatedAt();
		this.query = query;
		this.feeling = feeling;
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
	 * Gives the query with which the tweet was obtained.
	 * 
	 * @return query with which the tweet was obtained
	 */
	public String getQuery () {
		return this.query;
	}

	/**
	 * Gives the feeling of the tweet.
	 * 
	 * @return feeling of the tweet
	 */
	public Feeling getFeeling () {
		return this.feeling;
	}

	/**
	 * Sets the message of the tweet.
	 * 
	 * @param newMsg
	 *            new message of the tweet
	 */
	public void setMsg ( String newMsg ) {
		this.msg = newMsg;
	}

	/**
	 * Sets the feeling of the tweet.
	 * 
	 * @param newFeeling
	 *            new feeling of the tweet
	 */
	public void setFeeling ( Feeling newFeeling ) {
		this.feeling = newFeeling;
	}

	/**
	 * Returns a descritpion of the tweet.
	 * 
	 * @return descritpion of the tweet
	 */
	public String toString () {
		return "----------\n" + "id : " + this.getId() + "\n" + "twittos : " + this.getTwittos()
		        + "\n" + "message : " + this.getMsg() + "\n" + "date : " + this.getDate() + "\n"
		        + "query : " + this.getQuery() + "\n" + "feeling : " + this.getFeeling() + "\n"
		        + "----------\n";
	}

	@Override
	public boolean equals ( Object obj ) {
		if ( this == obj ) {
			return true;
		}
		if ( obj == null ) {
			return false;
		}
		if ( ! ( obj instanceof Tweet ) ) {
			return false;
		}
		Tweet other = ( Tweet ) obj;
		if ( date == null ) {
			if ( other.date != null ) {
				return false;
			}
		} else if ( !date.equals( other.date ) ) {
			return false;
		}
		if ( feeling != other.feeling ) {
			return false;
		}
		if ( id == null ) {
			if ( other.id != null ) {
				return false;
			}
		} else if ( !id.equals( other.id ) ) {
			return false;
		}
		if ( msg == null ) {
			if ( other.msg != null ) {
				return false;
			}
		} else if ( !msg.equals( other.msg ) ) {
			return false;
		}
		if ( query == null ) {
			if ( other.query != null ) {
				return false;
			}
		} else if ( !query.equals( other.query ) ) {
			return false;
		}
		if ( twittos == null ) {
			if ( other.twittos != null ) {
				return false;
			}
		} else if ( !twittos.equals( other.twittos ) ) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( date == null ) ? 0 : date.hashCode() );
		result = prime * result + ( ( feeling == null ) ? 0 : feeling.hashCode() );
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( msg == null ) ? 0 : msg.hashCode() );
		result = prime * result + ( ( query == null ) ? 0 : query.hashCode() );
		result = prime * result + ( ( twittos == null ) ? 0 : twittos.hashCode() );
		return result;
	}

}
