package observer;

import twitter4j.QueryResult;

public interface Observer {
	public void update( QueryResult result );
}
