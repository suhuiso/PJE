package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Logo panel of the application.
 * 
 * @author Quentin Baert & Thomas Bernard
 */
@SuppressWarnings("serial")
public class LogoPanel extends JPanel {

	////////////
	// FILEDS //
	////////////

	/**
	 * Image used to display the logo
	 */
	private BufferedImage image;

	/////////////
	// METHODS //
	/////////////

	/**
	 * Constructor of the LogoPanel
	 */
	public LogoPanel() {
		super();

		try {
			/* Access to the file containing the image */
			image = ImageIO.read( new File( "logo.png" ) );
		} catch ( IOException e ) {
			/* File can't be loaded */
			System.err.println( "ERROR: Fail to load search.png file" );
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		/* Image is drawn and horizontally centered */
		g.drawImage( image, this.getWidth()/2 - image.getWidth( null )/2 , 0, this );
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension( 384, 70 );
	}
}
