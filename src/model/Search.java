package model;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterException;

public class Search extends AbstractModel {
	
	@Override
	public void search( String searchQuery ) {
		Query query = new Query( searchQuery );
		QueryResult result = null;
		
		try {
			result = this.TWITTER.search( query );
		} catch ( TwitterException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Debug
		for ( Status status : result.getTweets() ) {
			System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
		}
		
		notifyObserver( result );
	}
}
