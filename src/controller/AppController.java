package controller;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
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

	/**
	 * Gives the current classifier.
	 * 
	 * @return current classifier
	 */
	public Classifier getCurrentClassifier () {
		return this.currentClassifier;
	}

	/**
	 * Gives the id of the current classifier
	 * 
	 * @return id of the current classifier
	 */
	public int getCurrentClassifierId () {
		return this.prefs.getInt( "CURRENT_ASSIGNER", 0 );
	}
	
	/**
	 * Sets the current classifier
	 * 
	 * @param id
	 *            id of the new current classifier
	 */
	public void setCurrentClassifierId ( int id ) {
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
	 * Gives the number of tweets to display.
	 * 
	 * @return number of tweets
	 */
	public int getCountTweets () {
		return this.model.getTweetsNb();
	}

	/**
	 * Sets the number of tweets to display
	 * 
	 * @param newValue
	 *            new number of tweets
	 */
	public void setCountTweets ( int newValue ) {
		this.model.setTweetsNb( newValue );
	}

	/**
	 * Updates Twitter configuration to use Lille 1 Proxy
	 */
	public void setProxyTwitter () {
		this.model.setProxyTwitter();
	}

	/**
	 * Updates Twitter configuration to not use Lille 1 Proxy
	 */
	public void unsetProxyTwitter () {
		this.model.unsetProxyTwitter();
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
	 * Asks to the model to classify a tweet with the current classifier.
	 * 
	 * @param tweet
	 *            tweet to classify with the current classifier
	 */
	public void classificationRequest ( Tweet tweet ) {
		this.model.classifies( tweet, this.getCurrentClassifier() );
	}

	/**
	 * Asks to the model to save a tweet in the tweet pool.
	 * 
	 * @param tweet
	 *            tweet to save
	 * @param feeling
	 *            feeling to assign to the tweet
	 */
	public void saveRequest ( Tweet tweet, Feeling feeling ) {
		this.model.saves( tweet, feeling );
	}

	public Image pieChartImageRequest ( List< Tweet > tweets ) throws MalformedURLException,
	        IOException, IllegalArgumentException {
		if ( tweets.isEmpty() ) {
			throw new IllegalArgumentException( "List of tweets is empty." );
		}

		return this.model.generatePieChartImage( this.currentClassifier, tweets );
	}

	/**
	 * Asks to the model to save informations that need to be saved before closing the window.
	 */
	public void closingWindowSaveRequest () {
		this.model.closingWindowSave();
	}

}
