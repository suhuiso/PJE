package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Content panel of the application.
 * 
 * @author Quentin Baert & Thomas Bernard
 */
@SuppressWarnings("serial")
public class ContentPanel extends JPanel {


	/////////////
	// METHODS //
	/////////////

	/**
	 * Constructor of the ContentPanel
	 */
	public ContentPanel() {
		super();
		
		this.setBackground( Color.WHITE );						/* color: white */
		this.setBorder( new EmptyBorder( 10, 10, 10, 10 ) );	/* padding: 10px */
		
		this.setLayout( new BorderLayout() );
		
		/*
		 * CONTENT :
		 * 
		 * ListTweetPanel : List of tweets
		 */
		this.add( new ListTweetPanel(), BorderLayout.CENTER );
	}
}
