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

		for ( Status status : qr.getTweets() ) {
			Tweet tweet = new Tweet( status, qr.getQuery(), Feeling.UNPOLARIZED );
			tweet.setFeeling( this.controller.getCurrentClassifier().classifies( tweet.getMsg() ) );
			this.listTweetModel.addElement( new LearningTweetPanel( this.controller, tweet ) );
		}

		/* View have changed and need to be repaint */
		this.revalidate();
	}
}
