package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.AbstractController;
import observer.Observer;
import twitter4j.QueryResult;
import twitter4j.Status;

public class SearchView extends JFrame implements Observer {
	
	private JPanel container = new JPanel();
	
	private JTextField searchField = new JTextField();
	private JButton searchButton = new JButton( "Search" );
	private JLabel searchResults = new JLabel();
	
	// ???
	private double chiffre1;
	private boolean clicOperateur = false, update = false;
	private String operateur = "";
	
	private AbstractController controller;
	
	public SearchView ( AbstractController controller ) {
		this.setSize( 240, 260 );
		this.setTitle( "PJE Twitter" );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setLocationRelativeTo( null );
		this.setResizable( true );
		initComposant();
		this.controller = controller;
		this.setContentPane( container );
		this.setVisible( true );
	}
	
	private void initComposant() {
		container.add( this.searchField );
		container.add( this.searchButton );
		container.add( this.searchResults );
	}   

	class SearchButtonListener implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			controller.setQuery( searchField.getText() );
		}
	}
	
	@Override
	public void update(QueryResult result) {
		String tweets = "";
		
		for ( Status status : result.getTweets() ) {
			String tweet = "@" + status.getUser().getScreenName() + ":" + status.getText();
			tweets += tweet;
		}
		
		searchResults.setText( tweets );
	}

}
