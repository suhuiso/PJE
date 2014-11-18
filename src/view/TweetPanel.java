package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import twitter4j.Status;
import controller.AppController;

@SuppressWarnings("serial")
public class TweetPanel extends JPanel {

	////////////
	// FIELDS //
	////////////
	
	/**
	 * Controller of the TweetPanel
	 */
	private AppController controller;

	/**
	 * Status of the tweet
	 */
	private Status status;
	
	/////////////
	// METHODS //
	/////////////
	
	/**
	 * Constructor of the TweetPanel
	 * 
	 * @param controller
	 *            controller of the TweetPanel
	 */
	public TweetPanel( AppController controller, Status status ) {
		super();

		this.setBackground( Color.WHITE );		/* color: white */
		
		this.setLayout( new BorderLayout() );
		
		/* Controller of TweetPanel is added */
		this.controller = controller;
		/* Status of this tweet is added */
		this.status = status;
		
		this.add( new JLabel( "@" + status.getUser().getScreenName() ), BorderLayout.NORTH );
		this.add( new JLabel( status.getText() ), BorderLayout.CENTER );
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension( 400, 60 );
	}
}
