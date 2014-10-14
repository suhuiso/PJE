package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Header panel of the application.
 * 
 * @author Quentin Baert & Thomas Bernard
 */
@SuppressWarnings("serial")
public class HeaderPanel extends JPanel {

	/////////////
	// METHODS //
	/////////////

	/**
	 * Constructor of the HeaderPanel
	 */
	public HeaderPanel() {
		super();

		this.setBackground( new Color( 0x55ACEE ) );		/* color: blue */
		this.setBorder( new EmptyBorder( 0, 0, 10, 0 ) );	/* padding-bottom: 10px */

		this.setLayout(new BorderLayout());

		/*
		 * CONTENT :
		 * 
		 * LogoPanel : Logo of the application
		 * SearchBarPanel : Content of the search bar and associated buttons 
		 */
		this.add( new LogoPanel(), BorderLayout.NORTH );
		this.add( new SearchBarPanel(), BorderLayout.CENTER );
	}

}
