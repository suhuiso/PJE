package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.AbstractController;
import observer.Observer;
import twitter4j.QueryResult;
import twitter4j.Status;

@SuppressWarnings ( "serial" )
public class SearchView extends JFrame implements Observer {
	
	private JPanel container = new JPanel();
	
	private JTextField searchField = new JTextField( 20 );
	private JButton searchButton = new JButton( "Search" );
	private JButton saveButton = new JButton( "Save" );
	private JLabel searchResults = new JLabel();
	
	private AbstractController controller;
	
	private QueryResult lastQueryResult;
	
	public SearchView ( AbstractController controller ) {
		this.setSize( 800, 400 );
		this.setTitle( "PJE Twitter" );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setLocationRelativeTo( null );
		this.setResizable( true );
		this.initComposant();
		this.controller = controller;
		this.lastQueryResult = null;
		this.setContentPane( container );
		this.setVisible( true );
	}
	
	private void initComposant() {		
		SearchButtonListener searchButtonListener = new SearchButtonListener();
		this.searchButton.addActionListener( searchButtonListener );
		SaveButtonListener saveButtonListener = new SaveButtonListener();
		this.saveButton.addActionListener( saveButtonListener );
		
		this.container.add( this.searchField );
		this.container.add( this.searchButton );
		this.container.add( this.saveButton );
		this.container.add( this.searchResults );
	}   

	class SearchButtonListener implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			System.out.println( "SearchButton click received" );
			SearchView.this.controller.setQuery( searchField.getText() );
		}
	}

	class SaveButtonListener implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			System.out.println( "SaveButton click received" );
			
			try {
				BufferedWriter out = new BufferedWriter( new FileWriter( "tweetPool.csv", true ) );
				
				for ( Status status : SearchView.this.lastQueryResult.getTweets() ) {
					String content = status.getText().replace( '"', ' ' );
					
					String tweet = status.getId() + ";" + status.getUser().getScreenName() + ";" + "\"" + content + "\"" + ";" + status.getCreatedAt() + ";" + SearchView.this.lastQueryResult.getQuery();
					out.write( tweet );
					out.newLine();
				}
				
				out.close();
			} catch ( IOException e1 ) {
				e1.printStackTrace();
			}
		}
	}
	
	@Override
	public void update( QueryResult result ) {
		this.lastQueryResult = result;
		
		String tweets = "";
		
		for ( Status status : result.getTweets() ) {
			String tweet = "<p>@" + status.getUser().getScreenName() + ":" + status.getText() + "</p>";
			tweets += tweet;
		}
		
		SearchView.this.searchResults.setText( "<html>" + tweets + "</html>" );
	}

}
