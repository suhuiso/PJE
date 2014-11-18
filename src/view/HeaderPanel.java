package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AppController;

/**
 * Header panel of the application.
 * 
 * @author Quentin Baert & Thomas Bernard
 */
@SuppressWarnings("serial")
public class HeaderPanel extends JPanel implements Observer {

	////////////
	// FIELDS //
	////////////
	
	/**
	 * Controller of the HeaderPanel
	 */
	private AppController controller;
	
	/////////////
	// METHODS //
	/////////////

	/**
	 * Constructor of the HeaderPanel
	 * 
	 * @param controller
	 *            controller of the HeaderPanel
	 */
	public HeaderPanel( AppController controller ) {
		super();

		this.setBackground( new Color( 0x55ACEE ) );		/* color: blue */
		this.setBorder( new EmptyBorder( 0, 0, 10, 0 ) );	/* padding-bottom: 10px */

		this.setLayout(new BorderLayout());

		/* Controller of HeaderPanel is added */
		this.controller = controller;
		
		/*
		 * CONTENT :
		 * 
		 * SearchBarPanel : Content of the search bar and associated buttons 
		 */
		this.add( new SearchBarPanel( controller ), BorderLayout.CENTER );
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
	}

}
