package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import twitter4j.QueryResult;
import twitter4j.Status;
import controller.AppController;

@SuppressWarnings("serial")
public class ListTweetPanel extends JPanel implements Observer {

	private AppController controller;
	private DefaultListModel<TweetPanel> listTweetModel;
	private JList<TweetPanel> listTweet;
	private JScrollPane scrollPane;
	
	public ListTweetPanel( AppController controller, Color color ) {
		super();		
		this.setBackground( color );
		this.setBorder( new EmptyBorder( 0, 25, 25, 25 ) );
		
		this.setLayout( new BorderLayout() );

		this.controller = controller;
		
		this.listTweetModel = new DefaultListModel<TweetPanel>();
		
		this.listTweet = new JList<TweetPanel>( this.listTweetModel );
	    this.listTweet.setCellRenderer( new TweetRenderer() );
		this.listTweet.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
		
		this.scrollPane = new JScrollPane( this.listTweet );		
		this.scrollPane.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
		this.scrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
		this.scrollPane.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
		
		System.out.println( this.scrollPane.getVerticalScrollBar().getUnitIncrement() );
		
		this.add( this.scrollPane, BorderLayout.CENTER );
	}

	@Override
	public void update( Observable o, Object arg ) {
		QueryResult qr = ( QueryResult ) arg;

		for ( Status status : qr.getTweets() ) {
			this.listTweetModel.addElement( new TweetPanel( this.controller, status ) );
		}		

		/* View have changed and need to be repaint */
		this.revalidate();
	}	
}
