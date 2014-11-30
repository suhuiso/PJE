package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AppController;

@SuppressWarnings("serial")
public class MenuPanel extends JPanel {

	private MenuButton feelingsButton;
	private MenuButton tendenciesButton;
	private MenuButton learningButton;
	private MenuButton settingsButton;
	private MenuButton currentButton;
	
	public MenuPanel ( AppController controller ) {
		super();
		this.setBackground( Color.WHITE );
		this.setBorder( new EmptyBorder( -1, 0, -1, 0 ) );
		
		this.setLayout( new GridLayout( 0, 1 ) );

		this.feelingsButton = new MenuButton( "Sentiments", FeelingsPanel.CARD_FEELINGS, FeelingsPanel.FEELINGS_COLOR );
		this.tendenciesButton = new MenuButton( "Tendances", TendenciesPanel.CARD_TENDENCIES, TendenciesPanel.TENDENCIES_COLOR );
		this.learningButton = new MenuButton( "Apprentissage", LearningPanel.CARD_LEARNING, LearningPanel.LEARNING_COLOR );
		this.settingsButton = new MenuButton( "Réglages", SettingsPanel.CARD_SETTINGS, SettingsPanel.SETTINGS_COLOR );
		this.setCurrentButton( this.feelingsButton );
		
		this.add( this.feelingsButton );
		this.add( this.tendenciesButton );
		this.add( this.learningButton );
		this.add( this.settingsButton );
	}
	
	public MenuButton getCurrentButton () {
		return this.currentButton;
	}
	
	public void setCurrentButton ( MenuButton button ) {
		this.currentButton = button;
		this.unhighlightButtons();
		this.highlightCurrentButton();
	}
	
	public void unhighlightButtons () {
		this.feelingsButton.unhighlight();
		this.tendenciesButton.unhighlight();
		this.learningButton.unhighlight();
		this.settingsButton.unhighlight();
	}
	
	public void highlightCurrentButton () {
		this.getCurrentButton().highlight();
	}
	
	@Override
	public Dimension getPreferredSize () {
		return new Dimension( 200, 200 );
	}
}
