package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class MenuButton extends JButton {

	private String card;
	
	public MenuButton( String text, String card, Color color ) {
		super( text.toUpperCase() );
		this.setForeground( new Color( 0x2F3238 ) );
		this.setBackground( color );
		this.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
		
		this.card = card;
		
		this.setOpaque(true);
		
		this.initListener();
	}
	
	public String getCard () {
		return this.card;
	}
	
	private void initListener () {
		MenuButtonListener menuButtonListener = new MenuButtonListener();
		this.addActionListener( menuButtonListener );
	}
	
	class MenuButtonListener implements ActionListener {
		public void actionPerformed ( ActionEvent e ) {
			AppPanel appPanel = (AppPanel) MenuButton.this.getParent().getParent().getParent();
			ContentPanel contentPanel = (ContentPanel) appPanel.getContentPanel();
			CardLayout layout = (CardLayout) contentPanel.getLayout();
			layout.show( contentPanel, MenuButton.this.getCard() );
		}
	}}
