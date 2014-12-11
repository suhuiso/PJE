package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import utils.Tweet;
import controller.AppController;

@SuppressWarnings ( "serial" )
public class ListTweetPanel extends JPanel implements Observer {

	protected AppController controller;

	protected DefaultListModel< TweetPanel > listTweetModel;

	protected JList< TweetPanel > listTweet;

	protected JScrollPane scrollPane;

	public ListTweetPanel ( AppController controller, Color color ) {
		super();
		this.setBackground( color );
		this.setBorder( new EmptyBorder( 0, 25, 25, 25 ) );

		this.setLayout( new BorderLayout() );

		this.controller = controller;

		this.listTweetModel = new DefaultListModel< TweetPanel >();

		this.listTweet = new JList< TweetPanel >( this.listTweetModel );
		this.listTweet.setCellRenderer( new TweetRenderer() );
		this.listTweet.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );

		this.scrollPane = new JScrollPane( this.listTweet );
		this.scrollPane.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
		this.scrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
		this.scrollPane.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );

		this.add( this.scrollPane, BorderLayout.CENTER );
	}

	@Override
	public void update ( Observable o, Object arg ) {
		@SuppressWarnings ( "unchecked" )
		List< Tweet > lt = ( List< Tweet > ) arg;

		for ( Tweet tweet : lt ) {
			this.controller.classificationRequest( tweet );
			this.listTweetModel.addElement( new TweetPanel( this.controller, tweet ) );
		}

		/* View have changed and need to be repaint */
		this.revalidate();
	}

	public void clear () {
		this.listTweetModel.clear();
	}
}
