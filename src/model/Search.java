package model;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class Search extends AbstractModel {

	Twitter twitter = TwitterFactory.getSingleton();
	
	@Override
	public QueryResult getTweets( String searchQuery ) {
		Query query = new Query( searchQuery );
		QueryResult result = null;
		
		try {
			result = twitter.search( query );
		} catch ( TwitterException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

}
