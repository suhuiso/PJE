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
	
	public FeelingsPanel ( AppController controller ) {
		super( controller );
		this.setBackground( FeelingsPanel.FEELINGS_COLOR );
					
		this.searchBarPanel = new SearchBarPanel( controller, FeelingsPanel.FEELINGS_COLOR );
		
		this.add( this.searchBarPanel, BorderLayout.NORTH );
	}

	@Override
	public void update ( Observable o, Object arg ) {
		// TODO Auto-generated method stub
	}

	@Override
	public void clear () {
		// TODO Auto-generated method stub
	}
}
