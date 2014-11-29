package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AppController;

@SuppressWarnings("serial")
public class FeelingsPanel extends JPanel {

    public static final String CARD_FEELINGS =  "Feelings Panel";
    public static final Color FEELINGS_COLOR = new Color( 0x0E83CD );
    
	private AppController controller;
    private SearchBarPanel searchBarPanel;
	
	public FeelingsPanel( AppController controller ) {
		super();		
		this.setBackground( FeelingsPanel.FEELINGS_COLOR );
		this.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
		
		this.setLayout( new BorderLayout() );
		
		this.controller = controller;
		
		this.searchBarPanel = new SearchBarPanel( controller, FeelingsPanel.FEELINGS_COLOR );
		
		this.add( this.searchBarPanel, BorderLayout.NORTH );
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension( 800, 600 );
	}
}
