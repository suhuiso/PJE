package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class SearchButton extends JButton {

	public SearchButton () {
		super( "Rechercher" );		
		this.setForeground( Color.WHITE );
		this.setBackground( new Color( 0x2F3238 ) );
		this.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
		this.setFont( new Font( "Lucida Sans", Font.PLAIN, 18 ) );
		
		this.setOpaque( true );
	}
	
	@Override
	public Dimension getPreferredSize () {
		return new Dimension( 150, 100 );
	}
}
