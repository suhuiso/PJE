package model;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterException;

public class AppModel extends AbstractModel {
	
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
		
		this.setChanged();
		this.notifyObservers( result );
	}

	@Override
    public void save ( QueryResult result ) {
    }
}
