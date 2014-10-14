package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Search bar panel of the application.
 * 
 * @author Quentin Baert & Thomas Bernard
 */
@SuppressWarnings("serial")
public class SearchBarPanel extends JPanel {

	/////////////
	// METHODS //
	/////////////
	
	/**
	 * Constructor of the SearchBarPanel
	 */
	public SearchBarPanel() {
		super();
		
		this.setBackground( new Color( 0x55ACEE ) );		/* color: blue */
		this.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );	/* padding: 0 */
		
	    this.setLayout( new BorderLayout() );
		
		/*
		 * CONTENT :
		 * 
		 * SearchBarField : Search bar field
		 * SearchButton : Search button
		 */
		this.add( new SearchBarField(), BorderLayout.WEST );
		this.add( new SearchButton(), BorderLayout.EAST );
	}
	
}
