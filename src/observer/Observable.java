package observer;

import twitter4j.QueryResult;

public interface Observable {
	public void addObserver( Observer obs );
	public void removeObserver();
	public void notifyObserver( QueryResult result );
}
