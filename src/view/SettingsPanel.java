package view;

import java.awt.Color;
import java.util.Observable;

import controller.AppController;

@SuppressWarnings("serial")
public class SettingsPanel extends AbstractCardPanel {

    public static final String CARD_SETTINGS =  "Settings Panel";
    public static final Color SETTINGS_COLOR = new Color( 0x9E54BD );

	public SettingsPanel( AppController controller ) {
		super( controller );		
		this.setBackground( SettingsPanel.SETTINGS_COLOR );
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
