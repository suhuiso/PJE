package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import controller.AppController;

@SuppressWarnings("serial")
public class SearchBarPanel extends JPanel {

	private AppController controller;
	private SearchBarField searchBarField;
	private JLabel classifierLabel;
	private SearchButton searchButton;
	
	public SearchBarPanel ( AppController controller, Color color ) {
		super();	
		this.setBackground( color );
		this.setBorder( new EmptyBorder( 25, 25, 25, 25 ) );
		
	    this.setLayout( new BorderLayout() );

		this.controller = controller;
	    
		this.searchBarField = new SearchBarField();
		this.searchButton = new SearchButton();
		
		this.classifierLabel = new JLabel( "avec " + this.controller.getCurrentClassifier().toString(), SwingConstants.RIGHT );
		this.classifierLabel.setBackground( Color.WHITE );
		this.classifierLabel.setForeground( new Color( 0x2F3238 ) );
		this.classifierLabel.setFont( new Font( "Lucida Sans", Font.BOLD, 18 ) );
		this.classifierLabel.setBorder( new EmptyBorder( 25, 25, 25, 25 ) );
		this.classifierLabel.setBorder( new CompoundBorder(
			BorderFactory.createMatteBorder( 0, 0, 0, 20, color ),
			BorderFactory.createMatteBorder( 0, 0, 0, 20, Color.WHITE )
		) );
		this.classifierLabel.setOpaque( true );
		
		this.add( this.searchBarField, BorderLayout.WEST );
		this.add( this.classifierLabel, BorderLayout.CENTER );
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
	
	public void clear () {
		this.searchBarField.setText( "" );
	}
	
	public void resume () {
		this.classifierLabel.setText( "avec " + this.controller.getCurrentClassifier().toString() );
	}
}
