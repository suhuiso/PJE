package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AppController;

@SuppressWarnings("serial")
public class MenuPanel extends JPanel {

	private AppController controller;
	private MenuButton feelingsButton;
	private MenuButton tendenciesButton;
	private MenuButton learningButton;
	private MenuButton settingsButton;
	
	public MenuPanel( AppController controller ) {
		super();
		this.setBackground( Color.WHITE );
		this.setBorder( new EmptyBorder( -1, 0, -1, 0 ) );
		
		this.setLayout( new GridLayout( 0, 1 ) );
		
		this.controller = controller;
		
		this.feelingsButton = new MenuButton( "Sentiments", FeelingsPanel.CARD_FEELINGS, new Color( 0x0E83CD ) );
		this.tendenciesButton = new MenuButton( "Tendances", TendenciesPanel.CARD_TENDENCIES, new Color( 0x2ECC71 ) );
		this.learningButton = new MenuButton( "Apprentissage", LearningPanel.CARD_LEARNING, new Color( 0xFCD04B ) );
		this.settingsButton = new MenuButton( "Réglages", SettingsPanel.CARD_SETTINGS, new Color( 0x9E54BD ) );
		
		this.add( this.feelingsButton );
		this.add( this.tendenciesButton );
		this.add( this.learningButton );
		this.add( this.settingsButton );
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension( 200, 200 );
	}
}
