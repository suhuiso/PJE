package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import utils.Tweet;
import controller.AppController;

@SuppressWarnings("serial")
public class LearningTweetPanel extends TweetPanel {

	private ClassificationsPanel classificationsPanel;
	
	public LearningTweetPanel ( AppController controller, Tweet tweet, Color color ) {
		super( controller, tweet, color );
		
		this.classificationsPanel = new ClassificationsPanel( controller );
		if ( TweetPanel.TWEET_PANEL_COUNT % 2 == 0 ) {
			this.classificationsPanel.setBackground( color );
		} else {
			this.classificationsPanel.setBackground( Color.WHITE );
		}
		
		this.add( this.classificationsPanel, BorderLayout.SOUTH );
	}
	
	@Override
	public Dimension getPreferredSize () {
		return new Dimension( 750, 145 );
	}
	
	@Override
	public Dimension getMaximumSize () {
		return new Dimension( 750, 145 );
	}
}
