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
	
	private JTextField searchField = new JTextField( 20 );
	private JButton searchButton = new JButton( "Search" );
	private JLabel searchResults = new JLabel();
	
	private AbstractController controller;
	
	public SearchView ( AbstractController controller ) {
		this.setSize( 800, 400 );
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
		SearchButtonListener searchButtonListener = new SearchButtonListener();
		this.searchButton.addActionListener( searchButtonListener );
		
		container.add( this.searchField );
		container.add( this.searchButton );
		container.add( this.searchResults );
	}   

	class SearchButtonListener implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			System.out.println( "SearchButton click received" );
			controller.setQuery( searchField.getText() );
		}
	}
	
	@Override
	public void update(QueryResult result) {
		String tweets = "";
		
		for ( Status status : result.getTweets() ) {
			String tweet = "<p>@" + status.getUser().getScreenName() + ":" + status.getText() + "</p>";
			tweets += tweet;
		}
		
		searchResults.setText( "<html>" + tweets + "</html>" );
	}

}
