package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Observable;

import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controller.AppController;

@SuppressWarnings("serial")
public class FeelingsPanel extends AbstractCardPanel {

    public static final String CARD_FEELINGS =  "Feelings Panel";
    public static final Color FEELINGS_COLOR = new Color( 0x0E83CD );
    public static final Color FEELINGS_COLOR_BRIGHTER = new Color( 0xC3E0F2 );
    
    private SearchBarPanel searchBarPanel;
	private ListTweetPanel listTweetPanel;
    
	public FeelingsPanel ( AppController controller ) {
		super( controller );
		this.setBackground( FeelingsPanel.FEELINGS_COLOR );
					
		this.searchBarPanel = new SearchBarPanel( controller, FeelingsPanel.FEELINGS_COLOR );
		this.listTweetPanel = new ListTweetPanel( controller, FeelingsPanel.FEELINGS_COLOR );		
		
		JScrollPane scrollContainer = new JScrollPane( this.listTweetPanel );
		scrollContainer.getVerticalScrollBar().setUnitIncrement(10);
		scrollContainer.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
		scrollContainer.setBackground( FeelingsPanel.FEELINGS_COLOR );
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
		return FeelingsPanel.CARD_FEELINGS;
	}

	@Override
	public void resume() {
		this.searchBarPanel.resume();
	}
}
