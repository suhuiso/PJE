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
		this.setLocationRelativeTo( null );

		/* App closes when close button is pressed */
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		/* Window is made visible */
		this.setVisible( true );

		/* Controller of AppView is added */
		this.controller = controller;
		
		/*
		 * CONTENT :
		 * 
		 * AppPanel : Main Panel of the window
		 */
		this.setContentPane( new AppPanel() );
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
