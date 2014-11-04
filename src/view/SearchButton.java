package view;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

/**
 * Search bar button of the application.
 * 
 * @author Quentin Baert & Thomas Bernard
 */
@SuppressWarnings("serial")
public class SearchButton extends JButton {

	////////////
	// FILEDS //
	////////////

	/**
	 * Image used into the search button
	 */
	private BufferedImage image;

	/////////////
	// METHODS //
	/////////////

	/**
	 * Constructor of the SearchButton
	 */
	public SearchButton () {
		super();

		try {
			/* Access to the file containing the image */
			image = ImageIO.read( new File( "resources/img/search.png" ) );
		} catch ( IOException e ) {
			/* File can't be loaded */
			System.err.println( "ERROR: Fail to load search.png file" );
			e.printStackTrace();
		}
	}
	
	@Override
	protected void paintComponent ( Graphics g ) {
		g.fillRoundRect( 0, 0, 30, 30, 30, 30 );
		
		/* Image is drawn and horizontally centered */
		g.drawImage( image, 7, 7, 15, 15, this );
		
		this.setCursor( new Cursor( Cursor.HAND_CURSOR ) );
	}
	
	@Override
	public Dimension getPreferredSize () {
		return new Dimension( 30, 30 );
	}
}
