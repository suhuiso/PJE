package model;

import java.util.Observable;

import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

public abstract class AbstractModel extends Observable {
	
	protected final Twitter TWITTER = TwitterFactory.getSingleton();
		
	public abstract void search ( String searchQuery );
	
	public abstract void save ( QueryResult result );
	
}
