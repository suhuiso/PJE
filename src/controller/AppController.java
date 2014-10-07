package controller;

import twitter4j.QueryResult;
import model.AbstractModel;

public class AppController {

	protected AbstractModel model;

	public AppController ( AbstractModel model ) {
		this.model = model;
	}
	
	private String control (String query) {
		// TODO Validation du champ de recherche
		return query;
	}

	public void setQuery ( String query ) {
		String correctQuery = this.control( query );
		this.model.search( correctQuery );
	}

	public void saveRequest ( QueryResult result ) {
		this.model.save( result );
	}

}
