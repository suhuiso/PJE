package view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ListTweetPanel extends JPanel {

	/////////////
	// METHODS //
	/////////////

	/**
	 * Constructor of the ListTweetPanel
	 */
	public ListTweetPanel() {
		super();
		
		this.setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );
		
		/*
		 * CONTENT :
		 * 
		 * HeaderPanel : Header of the application
		 * ContentPanel : Main content 
		 */
		this.add( new TweetPanel() );
	}
}
