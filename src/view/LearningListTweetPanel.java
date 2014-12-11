package view;

import java.awt.Color;
import java.util.List;
import java.util.Observable;

import utils.Tweet;
import controller.AppController;

@SuppressWarnings ( "serial" )
public class LearningListTweetPanel extends ListTweetPanel {

	public LearningListTweetPanel ( AppController controller, Color color ) {
		super( controller, color );
	}

	@Override
	public void update ( Observable o, Object arg ) {
		@SuppressWarnings ( "unchecked" )
		List< Tweet > lt = ( List< Tweet > ) arg;

		for ( Tweet tweet : lt ) {
			this.controller.classificationRequest( tweet );
			this.add( new LearningTweetPanel( this.controller, tweet, LearningPanel.LEARNING_COLOR_BRIGHTER ) );
		}

		/* View have changed and need to be repaint */
		this.revalidate();
	}
}
