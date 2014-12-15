package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class MenuButton extends JButton {

	private String card;
	private Color color;
	
	public MenuButton ( String text, String card, Color color ) {
		super( text );
		this.setForeground( this.getColor() );
		this.setBackground( new Color( 0x2F3238 ) );
		this.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
		this.setFont( new Font( "Lucida Sans", Font.PLAIN, 20 ) );
		
		this.card = card;
		this.color = color;
		
		this.setOpaque( true );
		
		this.initListener();
	}
	
	public String getCard () {
		return this.card;
	}
	
	public Color getColor () {
		return this.color;
	}
	
	public void highlight () {
		this.setForeground( new Color( 0x2F3238 ) );
		this.setBackground( this.getColor() );
		this.repaint();
	}
	
	public void unhighlight () {
		this.setForeground( this.getColor() );
		this.setBackground( new Color( 0x2F3238 ) );
		this.repaint();
	}
	
	private void initListener () {
		MenuButtonListener menuButtonListener = new MenuButtonListener();
		this.addActionListener( menuButtonListener );
	}
	
	class MenuButtonListener implements ActionListener {
		public void actionPerformed ( ActionEvent e ) {
			MenuButton buttonPressed = ( MenuButton ) e.getSource();
			MenuPanel menuPanel = ( MenuPanel ) MenuButton.this.getParent();
			AppPanel appPanel = ( AppPanel ) menuPanel.getParent().getParent();
			ContentPanel contentPanel = ( ContentPanel ) appPanel.getContentPanel();
			CardLayout layout = ( CardLayout ) contentPanel.getLayout();
			contentPanel.setCurrentPanel( contentPanel.getPanelByName( buttonPressed.getCard() ) );
			menuPanel.setCurrentButton( buttonPressed );
			contentPanel.getCurrentPanel().resume();
			layout.show( contentPanel, buttonPressed.getCard() );
		}
	}
}
