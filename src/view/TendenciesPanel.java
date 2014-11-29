package view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AppController;

@SuppressWarnings("serial")
public class TendenciesPanel extends JPanel {

    public static final String CARD_TENDENCIES =  "Tendencies Panel";
    public static final Color TENDENCIES_COLOR = new Color( 0x2ECC71 );
	public TendenciesPanel( AppController controller ) {
		super();		
		this.setBackground( new Color( 0x2ECC71 ) );
		this.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
	}
}
