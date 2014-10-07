package controller;

import model.AppModel;
import twitter4j.QueryResult;

public class AppController {

	protected AppModel model;

	public AppController ( AppModel model ) {
		this.model = model;
	}

	private String control ( String query ) {
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
