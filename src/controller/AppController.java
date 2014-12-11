package controller;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import model.AppModel;
import utils.Tweet;
import feeling.Classifier;
import feeling.Feeling;

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

	/**
	 * Preferences of the application
	 */
	private Preferences prefs;

	/**
	 * Current classifier of the application
	 */
	private Classifier currentClassifier;

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
		this.prefs = Preferences.userNodeForPackage( this.getClass() );
		this.currentClassifier =
		        this.model.getClassifierById( this.prefs.getInt( "CURRENT_ASSIGNER", 0 ) );
	}

	public Classifier getCurrentClassifier () {
		System.out
		        .println( "getCurrentCLassifier : current classifier = " + this.currentClassifier );
		return this.currentClassifier;
	}

	public void setCurrentClassifierId ( int id ) {
		System.out.println( "setCurrentClassifierId: current classifier = " + id );
		this.currentClassifier = this.model.getClassifierById( id );
		this.prefs.putInt( "CURRENT_ASSIGNER", id );
		try {
			this.prefs.flush();
		} catch ( BackingStoreException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	/**
	 * Asks to the model to save informations that need to be saved before closing the window.
	 */
	public void closingWindowSaveRequest () {
		this.model.closingWindowSave();
	}

}
