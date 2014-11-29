package view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AppController;

@SuppressWarnings("serial")
public class FeelingsPanel extends JPanel {

    public static final String CARD_FEELINGS =  "Feelings Panel";
	
	public FeelingsPanel( AppController controller ) {
		super();		
		this.setBackground( new Color( 0x0E83CD ) );
		this.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
	}
}
