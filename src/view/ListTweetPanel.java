package view;

import java.awt.Color;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utils.Tweet;
import controller.AppController;

@SuppressWarnings ( "serial" )
public class ListTweetPanel extends JPanel implements Observer {

	protected AppController controller;

	public ListTweetPanel ( AppController controller, Color color ) {
		super();
		this.setBackground( color );
		this.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );

		this.setLayout( new BoxLayout( this, BoxLayout.PAGE_AXIS ) );

		this.controller = controller;
	}

	@Override
	public void update ( Observable o, Object arg ) {
		@SuppressWarnings ( "unchecked" )
		List< Tweet > lt = ( List< Tweet > ) arg;

		for ( Tweet tweet : lt ) {
			this.controller.classificationRequest( tweet );
			this.add( new TweetPanel( this.controller, tweet ) );
		}

		/* View have changed and need to be repaint */
		this.revalidate();
	}

	public void clear () {
		this.removeAll();
	}
}
