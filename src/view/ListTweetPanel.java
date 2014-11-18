package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.Scrollable;
import javax.swing.border.EmptyBorder;

import twitter4j.QueryResult;
import twitter4j.Status;
import controller.AppController;

@SuppressWarnings("serial")
public class ListTweetPanel extends JPanel implements Scrollable, Observer {

	////////////
	// FIELDS //
	////////////
	
	/**
	 * Controller of the ListTweetPanel
	 */
	private AppController controller;
	
	/////////////
	// METHODS //
	/////////////

	/**
	 * Constructor of the ListTweetPanel
	 * 
	 * @param controller
	 *            controller of the ListTweetPanel
	 */
	public ListTweetPanel( AppController controller ) {
		super();
		
		this.setBackground( new Color( 0x55ACEE ) );			/* color: blue */
		this.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );		/* padding: 0px */
		
		this.setLayout( new GridLayout( 0, 1, 0, 1 ) );

		/* Controller of ListTweetPanel is added */
		this.controller = controller;
		
		/*
		 * CONTENT :
		 * 
		 * TweetPanel : Tweet
		 */
	}

	@Override
	public Dimension getPreferredScrollableViewportSize() {
		return this.getPreferredSize();
	}

	@Override
	public int getScrollableUnitIncrement( Rectangle visibleRect, int orientation, int direction ) {
		return 5;
	}

	@Override
	public int getScrollableBlockIncrement( Rectangle visibleRect, int orientation, int direction ) {
		return 5;
	}

	@Override
	public boolean getScrollableTracksViewportWidth() {
		return true;
	}

	@Override
	public boolean getScrollableTracksViewportHeight() {
		return false;
	}

	@Override
	public void update( Observable o, Object arg ) {
		QueryResult qr = ( QueryResult ) arg;

		for ( Status status : qr.getTweets() ) {
			this.add( new TweetPanel( this.controller, status ) );
		}		

		/* View have changed and need to be repaint */
		this.revalidate();
	}
	
}
