package controller;

import twitter4j.QueryResult;
import model.AbstractModel;

public abstract class AbstractController {
	
	protected AbstractModel model;
	protected String query = "";
	
	public AbstractController ( AbstractModel model ){
		this.model = model;
	}

	public void setQuery ( String query ){
		this.query = query;
		this.control();
	}
	
	public void saveRequest ( QueryResult result ) {
		this.model.save( result );
	}
	
	abstract void control();
	
}
