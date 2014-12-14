package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Observable;

import controller.AppController;

@SuppressWarnings("serial")
public class TendenciesPanel extends AbstractCardPanel {

    public static final String CARD_TENDENCIES =  "Tendencies Panel";
    public static final Color TENDENCIES_COLOR = new Color( 0x2ECC71 );
    
    private SearchBarPanel searchBarPanel;
    private StatisticsPanel statisticsPanel;
    
	public TendenciesPanel( AppController controller ) {
		super( controller );		
		this.setBackground( TendenciesPanel.TENDENCIES_COLOR );
						
		this.searchBarPanel = new SearchBarPanel( controller, TendenciesPanel.TENDENCIES_COLOR );
		this.statisticsPanel = new StatisticsPanel( controller, TendenciesPanel.TENDENCIES_COLOR );
		
		this.add( this.searchBarPanel, BorderLayout.NORTH );
		this.add( this.statisticsPanel, BorderLayout.CENTER );
	}

	@Override
	public void update ( Observable o, Object arg ) {
		this.statisticsPanel.update( o, arg );
	}
	
	@Override
	public void clear () {
		this.searchBarPanel.clear();
		this.statisticsPanel.clear();
	}
	
	public String toString () {
		return TendenciesPanel.CARD_TENDENCIES;
	}
}
