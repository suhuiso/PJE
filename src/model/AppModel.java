package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterException;

public class AppModel extends AbstractModel {

	@Override
	public void search ( String searchQuery ) {
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
			System.out.println( "@" + status.getUser().getScreenName() + ":" + status.getText() );
		}

		this.setChanged();
		this.notifyObservers( result );
	}

	@Override
	public void save ( QueryResult result ) {
		try {
			BufferedWriter out = new BufferedWriter( new FileWriter( "tweetPool.csv", true ) );

			for ( Status status : result.getTweets() ) {
				String content = status.getText().replace( '"', ' ' );

				String tweet =
				        status.getId() + ";" + status.getUser().getScreenName() + ";" + "\""
				                + content + "\"" + ";" + status.getCreatedAt() + ";"
				                + result.getQuery();
				out.write( tweet );
				out.newLine();
			}

			out.close();
		} catch ( IOException e1 ) {
			e1.printStackTrace();
		}
	}

	private String replaceUsername( String s ) {
		return s.replaceAll( "@[A-Za-z0-9_-]+", "@" );
	}
}
