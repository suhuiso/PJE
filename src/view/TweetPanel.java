package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utils.Tweet;
import controller.AppController;

@SuppressWarnings("serial")
public class TweetPanel extends JPanel {

	protected Tweet tweet;
	protected static int TWEET_PANEL_COUNT = 0;
	
	public TweetPanel ( AppController controller, Tweet tweet, Color color ) {
		super();
		
		if ( TweetPanel.TWEET_PANEL_COUNT++ % 2 == 0 ) {
			this.setBackground( Color.WHITE );
		} else {
			this.setBackground( color );
		}
		this.setBorder( new EmptyBorder( 10, 10, 10, 10 ) );
		
		this.setLayout( new BorderLayout() );
				
		this.tweet = tweet;
		
		JLabel usernameComputed = new JLabel( "@" + this.tweet.getTwittos() );
		usernameComputed.setFont( new Font( "Lucida Sans", Font.BOLD, 18 ) );
		
		JLabel messageComputed = new JLabel( "<html>" + this.tweet.getMsg() + "</html>" );
		messageComputed.setFont( new Font( "Lucida Sans", Font.PLAIN, 18 ) );
		
		JLabel feelingComputed = new JLabel( this.tweet.getFeeling().toString() + "  " );
		feelingComputed.setFont( new Font( "Lucida Sans", Font.BOLD, 50 ) );
		
		this.add( usernameComputed, BorderLayout.NORTH );
		this.add( messageComputed, BorderLayout.CENTER );
		this.add( feelingComputed, BorderLayout.EAST );
	}

	public Tweet getTweet () {
		return this.tweet;
	}
	
	@Override
	public Dimension getPreferredSize () {
		return new Dimension( 750, 100 );
	}
	
	@Override
	public Dimension getMaximumSize () {
		return new Dimension( 750, 100 );
	}
}
