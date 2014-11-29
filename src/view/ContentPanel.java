package view;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AppController;

@SuppressWarnings("serial")
public class ContentPanel extends JPanel {

	private AppController controller;
	private FeelingsPanel feelingsPanel;
	private TendenciesPanel tendenciesPanel;
	private LearningPanel learningPanel;
	private SettingsPanel settingsPanel;
	
	public ContentPanel( AppController controller ) {
		super();		
		this.setBackground( Color.WHITE );
		this.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );

		this.setLayout( new CardLayout() );
		
		this.controller = controller;
		
		this.feelingsPanel = new FeelingsPanel( controller );
		this.tendenciesPanel = new TendenciesPanel( controller );
		this.learningPanel = new LearningPanel( controller );
		this.settingsPanel = new SettingsPanel( controller );
		
		this.add( this.feelingsPanel, FeelingsPanel.CARD_FEELINGS );
		this.add( this.tendenciesPanel, TendenciesPanel.CARD_TENDENCIES );
		this.add( this.learningPanel, LearningPanel.CARD_LEARNING );
		this.add( this.settingsPanel, SettingsPanel.CARD_SETTINGS );
	}
}
