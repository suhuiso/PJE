package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.AppController;
import twitter4j.QueryResult;
import twitter4j.Status;

@SuppressWarnings ( "serial" )
public class AppViewTmp extends JFrame implements Observer {

	private JPanel container = new JPanel();

	private JTextField searchField = new JTextField( 20 );

	private JButton searchButton = new JButton( "Search" );

	private JButton saveButton = new JButton( "Save" );

	private JLabel searchResults = new JLabel();

	private AppController controller;

	private QueryResult lastQueryResult;

	public AppViewTmp ( AppController controller ) {
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

	private void initComposant () {
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

		public void actionPerformed ( ActionEvent e ) {
			System.out.println( "SearchButton click received" );
			AppViewTmp.this.controller.setQuery( searchField.getText() );
		}
	}

	class SaveButtonListener implements ActionListener {

		public void actionPerformed ( ActionEvent e ) {
			System.out.println( "SaveButton click received" );
			AppViewTmp.this.controller.unpolarizedSaveRequest( lastQueryResult );
		}
	}

	@Override
	public void update ( Observable o, Object arg ) {
		QueryResult qr = ( QueryResult ) arg;

		this.lastQueryResult = qr;

		String tweets = "";

		for ( Status status : qr.getTweets() ) {
			String tweet =
			        "<p>@" + status.getUser().getScreenName() + ":" + status.getText() + "</p>";
			tweets += tweet;
		}

		AppViewTmp.this.searchResults.setText( "<html>" + tweets + "</html>" );
	}

}