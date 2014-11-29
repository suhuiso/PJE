package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AppController;

@SuppressWarnings("serial")
public class TendenciesPanel extends JPanel {

    public static final String CARD_TENDENCIES =  "Tendencies Panel";
    public static final Color TENDENCIES_COLOR = new Color( 0x2ECC71 );
    
	private AppController controller;
    private SearchBarPanel searchBarPanel;
    
	public TendenciesPanel( AppController controller ) {
		super();		
		this.setBackground( TendenciesPanel.TENDENCIES_COLOR );
		this.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
		
		this.setLayout( new BorderLayout() );
		
		this.controller = controller;
		
		this.searchBarPanel = new SearchBarPanel( controller, TendenciesPanel.TENDENCIES_COLOR );
		
		this.add( this.searchBarPanel, BorderLayout.NORTH );
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension( 800, 600 );
	}
}
