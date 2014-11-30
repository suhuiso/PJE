package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Observable;

import controller.AppController;

@SuppressWarnings("serial")
public class LearningPanel extends AbstractCardPanel {

    public static final String CARD_LEARNING =  "Learning Panel";
    public static final Color LEARNING_COLOR = new Color( 0xFCD04B );
    
    private SearchBarPanel searchBarPanel;
	private ListTweetPanel listTweetPanel;
    
	public LearningPanel( AppController controller ) {
		super( controller );
		this.setBackground( LearningPanel.LEARNING_COLOR );

		this.searchBarPanel = new SearchBarPanel( controller, LearningPanel.LEARNING_COLOR );
		this.listTweetPanel = new ListTweetPanel( controller, LearningPanel.LEARNING_COLOR );

		this.add( this.searchBarPanel, BorderLayout.NORTH );
		this.add( this.listTweetPanel, BorderLayout.CENTER );
	}

	@Override
	public void update ( Observable o, Object arg ) {
		this.listTweetPanel.update( o, arg );
	}

	@Override
	public void clear () {
		// TODO Auto-generated method stub
	}
}
