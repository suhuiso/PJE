package view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AppController;

@SuppressWarnings("serial")
public class SettingsPanel extends JPanel {

    public static final String CARD_SETTINGS =  "Settings Panel";
    public static final Color SETTINGS_COLOR = new Color( 0x9E54BD );

	public SettingsPanel( AppController controller ) {
		super();		
		this.setBackground( new Color( 0x9E54BD ) );
		this.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
	}
}
