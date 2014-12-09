package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Observable;

import controller.AppController;

@SuppressWarnings("serial")
public class FeelingsPanel extends AbstractCardPanel {

    public static final String CARD_FEELINGS =  "Feelings Panel";
    public static final Color FEELINGS_COLOR = new Color( 0x0E83CD );
    
    private SearchBarPanel searchBarPanel;
	private ListTweetPanel listTweetPanel;
    
	public FeelingsPanel ( AppController controller ) {
		super( controller );
		this.setBackground( FeelingsPanel.FEELINGS_COLOR );
					
		this.searchBarPanel = new SearchBarPanel( controller, FeelingsPanel.FEELINGS_COLOR );
		this.listTweetPanel = new ListTweetPanel( controller, FeelingsPanel.FEELINGS_COLOR );		

		this.add( this.searchBarPanel, BorderLayout.NORTH );
		this.add( this.listTweetPanel, BorderLayout.CENTER );
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
}
