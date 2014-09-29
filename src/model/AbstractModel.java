package model;

import java.util.ArrayList;

import observer.Observable;
import observer.Observer;
import twitter4j.QueryResult;

public abstract class AbstractModel implements Observable {
	
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
		
	public abstract void search ( String searchQuery );
	
	public void addObserver ( Observer obs ) {
		this.listObserver.add( obs );
	}
	
	public void notifyObserver( QueryResult result ) {
		for ( Observer obs : listObserver ) {
			obs.update( result );
		}
	}
	
	public void removeObserver () {
		listObserver = new ArrayList<Observer>();
	}  
}
