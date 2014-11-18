package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controller.AppController;

/**
 * Content panel of the application.
 * 
 * @author Quentin Baert & Thomas Bernard
 */
@SuppressWarnings("serial")
public class ContentPanel extends JPanel implements Observer {

	////////////
	// FIELDS //
	////////////
	
	/**
	 * Controller of the ContentPanel
	 */
	private AppController controller;

	/**
	 * List Tweet Panel
	 */
	private ListTweetPanel listTweetPanel;
	
	/////////////
	// METHODS //
	/////////////

	/**
	 * Constructor of the ContentPanel
	 * 
	 * @param controller
	 *            controller of the ContentPanel
	 */
	public ContentPanel( AppController controller ) {
		super();
		
		this.setBackground( Color.WHITE );						/* color: white */
		this.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );		/* padding: 0px */
		
		this.setLayout( new BorderLayout() );
		
		/* Controller of ContentPanel is added */
		this.controller = controller;
		
		/* Add content fields */
		this.listTweetPanel = new ListTweetPanel( controller );
		
		/*
		 * CONTENT :
		 * 
		 * ListTweetScrollPane : List of tweets
		 */
		JScrollPane listTweetScrollPane = new JScrollPane( this.listTweetPanel );
		listTweetScrollPane.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );	/* padding: 0px */
		
		this.add( listTweetScrollPane, BorderLayout.CENTER );
	}

	@Override
	public void update( Observable o, Object arg ) {
		this.listTweetPanel.update( o, arg );
	}
}
