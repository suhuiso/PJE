package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.sun.xml.internal.bind.v2.TODO;

import view.AppViewTmp.AllWindowListener;
import view.AppViewTmp.SaveButtonListener;
import view.AppViewTmp.SearchButtonListener;
import controller.AppController;

/**
 * Search bar panel of the application.
 * 
 * @author Quentin Baert & Thomas Bernard
 */
@SuppressWarnings("serial")
public class SearchBarPanel extends JPanel {

	////////////
	// FIELDS //
	////////////
	
	/**
	 * Controller of the HeaderPanel
	 */
	private AppController controller;
	
	/**
	 * Search Bar Field
	 */
	private SearchBarField searchBarField;
	
	/**
	 * Search Button
	 */
	private SearchButton searchButton;
	
	/////////////
	// METHODS //
	/////////////
	
	/**
	 * Constructor of the SearchBarPanel
	 * 
	 * @param controller
	 *            controller of the SearchBarPanel
	 */
	public SearchBarPanel( AppController controller ) {
		super();
		
		this.setBackground( new Color( 0x55ACEE ) );		/* color: blue */
		this.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );	/* padding: 0 */
		
	    this.setLayout( new BorderLayout() );

		/* Controller of SearchBarPanel is added */
		this.controller = controller;
	    
		/* Add content fields */
		this.searchBarField = new SearchBarField();
		this.searchButton = new SearchButton();
		
		/*
		 * CONTENT :
		 * 
		 * SearchBarField : Search bar field
		 * SearchButton : Search button
		 */
		this.add( this.searchBarField, BorderLayout.WEST );
		this.add( this.searchButton, BorderLayout.EAST );
		
		this.initListeners();
	}
	
	/**
	 * Add listeners to the SearchBarPanel
	 */
	private void initListeners () {
		SearchButtonListener searchButtonListener = new SearchButtonListener();
		this.searchButton.addActionListener( searchButtonListener );
	}
	
	/**
	 * Listener of the search button
	 * 
	 * @author Quentin Baert & Thomas Bernard
	 */
	class SearchButtonListener implements ActionListener {
		public void actionPerformed ( ActionEvent e ) {
			System.out.println( "SearchBarPanel: SearchButtonListener" );
			SearchBarPanel.this.controller.setQuery( SearchBarPanel.this.searchBarField.getText() );
		}
	}
	
}
