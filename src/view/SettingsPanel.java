package view;

import java.awt.Color;
import java.util.Observable;

import javax.swing.BoxLayout;
import javax.swing.border.EmptyBorder;

import controller.AppController;

@SuppressWarnings("serial")
public class SettingsPanel extends AbstractCardPanel {

    public static final String CARD_SETTINGS =  "Settings Panel";
    public static final Color SETTINGS_COLOR = new Color( 0x9E54BD );

    private AssignerSettingsPanel assignerSettingsPanel;
    private CountTweetsPanel countTweetsPanel;
    private ProxyPanel proxyPanel;
    
	public SettingsPanel( AppController controller ) {
		super( controller );
		this.setBorder( new EmptyBorder( 25, 25, 25, 25 ) );
		this.setBackground( SettingsPanel.SETTINGS_COLOR );
		this.setLayout( new BoxLayout( this, BoxLayout.PAGE_AXIS ) );
		
		this.assignerSettingsPanel = new AssignerSettingsPanel( controller, SettingsPanel.SETTINGS_COLOR );
		this.assignerSettingsPanel.setAlignmentX( LEFT_ALIGNMENT );
		this.countTweetsPanel = new CountTweetsPanel( controller, SettingsPanel.SETTINGS_COLOR );
		this.countTweetsPanel.setAlignmentX( LEFT_ALIGNMENT );
		this.proxyPanel = new ProxyPanel( controller, SettingsPanel.SETTINGS_COLOR );
		this.proxyPanel.setAlignmentX( LEFT_ALIGNMENT );
		
		this.add( this.assignerSettingsPanel );
		this.add( this.countTweetsPanel );
		this.add( this.proxyPanel );
	}

	@Override
	public void update ( Observable o, Object arg ) {
	}

	@Override
	public void clear () {
	}
	
	public String toString () {
		return SettingsPanel.CARD_SETTINGS;
	}

	@Override
	public void resume() {
	}
}
