package view;

import java.awt.BorderLayout;

import utils.Tweet;
import controller.AppController;

@SuppressWarnings("serial")
public class LearningTweetPanel extends TweetPanel {

	private ClassificationsPanel classificationsPanel;
	
	public LearningTweetPanel ( AppController controller, Tweet tweet ) {
		super( controller, tweet );
		
		this.classificationsPanel = new ClassificationsPanel( controller );
		
		this.add( this.classificationsPanel, BorderLayout.SOUTH );
	}
}
