package view;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import controller.AppController;

/**
 * Main view of the application.
 * 
 * @author Quentin Baert & Thomas Bernard
 */
@SuppressWarnings("serial")
public class AppView extends JFrame implements Observer {

	////////////
	// FIELDS //
	////////////
	
	/**
	 * Controller of the AppView
	 */
	private AppController controller;
	
	/**
	 * App panel
	 */
	private AppPanel appPanel;
	
	/////////////
	// METHODS //
	/////////////

	/**
	 * Constructor of the AppView
	 * 
	 * @param controller
	 *            controller of the AppView
	 */
	public AppView ( AppController controller ) {

		/* Title of the window */
		this.setTitle( "AwesomeName" );

		/* Size of the window, in pixels */
		this.setSize( 400, 600 );

		/* Minimal size to avoid shrinking of the window */
		this.setMinimumSize( new Dimension( 400, 600 ) );

		/* App launch at the middle of the screen */
		this.setSize( 1000, 600 );
		this.setResizable( false );		
		this.setLocationRelativeTo( null );

		/* App closes when close button is pressed */
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		/* Controller of AppView is added */
		this.controller = controller;
		
		/* Add content fields */
		this.appPanel = new AppPanel( controller );
		
		/*
		 * CONTENT :
		 * 
		 * AppPanel : Main Panel of the window
		 */
		this.setContentPane( appPanel );
		
		/* Window is made visible */
		this.setVisible( true );
	}

	@Override
	public void update(Observable o, Object arg) {
		this.appPanel.update( o, arg );
	}

}
