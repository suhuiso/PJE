package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Observable;

import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controller.AppController;

@SuppressWarnings("serial")
public class LearningPanel extends AbstractCardPanel {

    public static final String CARD_LEARNING =  "Learning Panel";
    public static final Color LEARNING_COLOR = new Color( 0xFCD04B );
    public static final Color LEARNING_COLOR_BRIGHTER = new Color( 0xFEF3D2 );
    
    private SearchBarPanel searchBarPanel;
	private LearningListTweetPanel listTweetPanel;
    
	public LearningPanel ( AppController controller ) {
		super( controller );
		this.setBackground( LearningPanel.LEARNING_COLOR );

		this.searchBarPanel = new SearchBarPanel( controller, LearningPanel.LEARNING_COLOR );
		this.listTweetPanel = new LearningListTweetPanel( controller, LearningPanel.LEARNING_COLOR );

		JScrollPane scrollContainer = new JScrollPane( this.listTweetPanel );
		scrollContainer.getVerticalScrollBar().setUnitIncrement(10);
		scrollContainer.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
		scrollContainer.setBackground( LearningPanel.LEARNING_COLOR );
		scrollContainer.setBorder( new EmptyBorder( 0, 25, 25, 25 ) );
		
		this.add( this.searchBarPanel, BorderLayout.NORTH );
		this.add( scrollContainer, BorderLayout.CENTER );
	}

	@Override
	public void update ( Observable o, Object arg ) {
		this.listTweetPanel.update( o, arg );
	}

	@Override
	public void clear () {
		this.searchBarPanel.clear();
		this.listTweetPanel.clear();
	}
	
	public String toString () {
		return LearningPanel.CARD_LEARNING;
	}
}
