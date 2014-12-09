package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utils.Tweet;
import controller.AppController;

@SuppressWarnings("serial")
public class TweetPanel extends JPanel {

	private Tweet tweet;
	
	public TweetPanel ( AppController controller, Tweet tweet ) {
		super();
		this.setBackground( Color.WHITE );
		this.setBorder( new EmptyBorder( 10, 10, 10, 10 ) );
		
		this.setLayout( new BorderLayout() );
				
		this.tweet = tweet;
		
		this.add( new JLabel( "@" + this.tweet.getTwittos() ), BorderLayout.NORTH );
		this.add( new JLabel( "<html>" + this.tweet.getMsg() + "</html>" ), BorderLayout.CENTER );
		this.add( new JLabel( "Classe = " + this.tweet.getFeeling().toString() + "  " ),  BorderLayout.EAST );
	}

	@Override
	public Dimension getPreferredSize () {
		return new Dimension( 750, 125 );
	}
}
