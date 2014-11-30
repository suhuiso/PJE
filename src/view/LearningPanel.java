package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AppController;

@SuppressWarnings("serial")
public class LearningPanel extends JPanel implements Observer {

    public static final String CARD_LEARNING =  "Learning Panel";
    public static final Color LEARNING_COLOR = new Color( 0xFCD04B );
    
	private AppController controller;
    private SearchBarPanel searchBarPanel;
	private ListTweetPanel listTweetPanel;
    
	public LearningPanel( AppController controller ) {
		super();		
		this.setBackground( LearningPanel.LEARNING_COLOR );
		this.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
		
		this.setLayout( new BorderLayout() );
		
		this.controller = controller;
		
		this.searchBarPanel = new SearchBarPanel( controller, LearningPanel.LEARNING_COLOR );
		this.listTweetPanel = new ListTweetPanel( controller, LearningPanel.LEARNING_COLOR );
		
		this.add( this.searchBarPanel, BorderLayout.NORTH );
		this.add( this.listTweetPanel, BorderLayout.CENTER );
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension( 800, 600 );
	}

	@Override
	public void update ( Observable o, Object arg ) {
		this.listTweetPanel.update( o, arg );
	}
}
