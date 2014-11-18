package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

/**
 * Search bar button of the application.
 * 
 * @author Quentin Baert & Thomas Bernard
 */
@SuppressWarnings("serial")
public class SearchButton extends JButton {

	/////////////
	// METHODS //
	/////////////

	/**
	 * Constructor of the SearchButton
	 */
	public SearchButton () {
		super( "Search" );
		
		this.setForeground( Color.BLACK );					/* color: black */
		this.setBackground( Color.WHITE );					/* color: white */
		this.setBorder( new EmptyBorder( 0, 10, 0, 10 ) );	/* padding: 0 */
		
		/* Override default style */
		this.setOpaque(true);
	}
		
	@Override
	public Dimension getPreferredSize () {
		return new Dimension( 76, 30 );
	}
}
