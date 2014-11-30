package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AppController;

@SuppressWarnings("serial")
public class SearchBarPanel extends JPanel {

	private AppController controller;
	private SearchBarField searchBarField;
	private SearchButton searchButton;
	
	public SearchBarPanel ( AppController controller, Color color ) {
		super();	
		this.setBackground( color );
		this.setBorder( new EmptyBorder( 25, 25, 25, 25 ) );
		
	    this.setLayout( new BorderLayout() );

		this.controller = controller;
	    
		this.searchBarField = new SearchBarField();
		this.searchButton = new SearchButton();
		
		this.add( this.searchBarField, BorderLayout.WEST );
		this.add( this.searchButton, BorderLayout.EAST );
		
		this.initListeners();
	}
	
	private void initListeners () {
		SearchButtonListener searchButtonListener = new SearchButtonListener();
		this.searchButton.addActionListener( searchButtonListener );
	}
	
	class SearchButtonListener implements ActionListener {
		public void actionPerformed ( ActionEvent e ) {
			SearchBarPanel.this.controller.setQuery( SearchBarPanel.this.searchBarField.getText() );
		}
	}
	
	@Override
	public Dimension getPreferredSize () {
		return new Dimension( 800, 100 );
	}
}
