package query;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class QueryBuilder {

	/* Fields */

	/**
	 * Twitter singleton.
	 */
	private final Twitter TWITTER = TwitterFactory.getSingleton();

	/**
	 * String representing the search query.
	 */
	private String searchQuery;

	/* Methods */

	/**
	 * Constructor of the QueryBuilder class.
	 * 
	 * @param searchQuery
	 *            string representing the search query
	 */
	public QueryBuilder ( String searchQuery ) {
		this.searchQuery = searchQuery;
	}
	
	/**
	 * Gets the results of the query.
	 * 
	 * @return results of the query.
	 * @throws TwitterException //TODO
	 */
	public QueryResult getResults () throws TwitterException {
		Query query = new Query( this.searchQuery );

		return TWITTER.search( query );
	}
}
