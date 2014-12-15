package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class SearchBarField extends JTextField {

	public SearchBarField () {
		super();
		this.setBackground( Color.WHITE );
		this.setForeground( new Color( 0x2F3238 ) );
		this.setBorder( new EmptyBorder( 0, 20, 0, 20 ) );
		this.setFont( new Font( "Lucida Sans", Font.PLAIN, 18 ) );
	}
	
	@Override
	public Dimension getPreferredSize () {
		return new Dimension( 280, 100 );
	}
}
