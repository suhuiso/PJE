package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Main panel of the application.
 * 
 * @author Quentin Baert & Thomas Bernard
 */
@SuppressWarnings("serial")
public class AppPanel extends JPanel {

	/////////////
	// METHODS //
	/////////////

	/**
	 * Constructor of the AppPanel
	 */
	public AppPanel () {
		super();
		
		this.setBackground( new Color( 0x55ACEE ) );		/* color: blue */
		this.setBorder( new EmptyBorder( 8, 8, 8, 8 ) );	/* padding : 8px */

		this.setLayout( new BorderLayout() );

		/*
		 * CONTENT :
		 * 
		 * HeaderPanel : Header of the application
		 * HeaderPanel : Main content 
		 */
		this.add( new HeaderPanel(), BorderLayout.NORTH );
		this.add( new ContentPanel(), BorderLayout.CENTER );
	}
}
