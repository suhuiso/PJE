package controller;

import model.AppModel;
import twitter4j.QueryResult;

/**
 * Controller of the application. Make the link between the view and the model.
 * 
 * @author Quentin Baert & Thomas Bernard
 */
public class AppController {
	
	////////////
	// FILEDS //
	////////////

	/**
	 * Model linked with the controller.
	 */
	protected AppModel model;
	
	/////////////
	// METHODS //
	/////////////

	/**
	 * Constructor of a AppController.
	 * 
	 * @param model
	 *            model to link with the controller
	 */
	public AppController ( AppModel model ) {
		this.model = model;
	}

	/**
	 * Controls the query passed in parameter.
	 * 
	 * @param query
	 *            query to control
	 * @return a query based on the parameters withtout illegal characters
	 */
	private String control ( String query ) {
		// Coma are not allowed in query
		return query.replace( ',', ' ' );
	}

	/**
	 * Asks to the model to make a search with a query.
	 * 
	 * @param query
	 *            query to make a search with
	 */
	public void setQuery ( String query ) {
		String correctQuery = this.control( query );
		this.model.search( correctQuery );
	}

	/**
	 * Asks to the model to save the results of a query.
	 * 
	 * @param result
	 *            results of a query previously made
	 */
	public void unpolarizedSaveRequest ( QueryResult result ) {
		this.model.unpolarizedSave( result );
	}

}
