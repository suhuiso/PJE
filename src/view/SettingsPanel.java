package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Observable;

import javax.swing.border.EmptyBorder;

import controller.AppController;

@SuppressWarnings("serial")
public class SettingsPanel extends AbstractCardPanel {

    public static final String CARD_SETTINGS =  "Settings Panel";
    public static final Color SETTINGS_COLOR = new Color( 0x9E54BD );

    private AssignerSettingsPanel assignerSettingsPanel;
    
	public SettingsPanel( AppController controller ) {
		super( controller );
		this.setBorder( new EmptyBorder( 25, 25, 25, 25 ) );
		this.setBackground( SettingsPanel.SETTINGS_COLOR );
		this.setLayout( new FlowLayout() );
		
		this.assignerSettingsPanel = new AssignerSettingsPanel( controller, SettingsPanel.SETTINGS_COLOR );
		
		this.add( this.assignerSettingsPanel );
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
