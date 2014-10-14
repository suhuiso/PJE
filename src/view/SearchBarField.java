package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JTextField;

/**
 * Search bar field of header panel.
 * 
 * @author Quentin Baert & Thomas Bernard
 */
@SuppressWarnings("serial")
public class SearchBarField extends JTextField {

	////////////
	// FILEDS //
	////////////

	private Shape shape;

	/////////////
	// METHODS //
	/////////////

	/**
	 * Constructor of the SearchBarField
	 */
	public SearchBarField () {
		super();

		this.setBackground( Color.WHITE );	/* color: white */
		this.setOpaque( false );		
	}

	@Override
	protected void paintComponent ( Graphics g ) {
		g.setColor( Color.WHITE );
		g.fillRoundRect(
				0,					/* X-origin */
				0,					/* Y-origin */
				this.getWidth(),	/* width */
				this.getHeight(),	/* height */
				this.getHeight(),	/* horizontal diameter of the arc at the four corners */
				this.getHeight()	/* vertical diameter of the arc at the four corners */
				);

		super.paintComponent(g);
	}

	@Override
	protected void paintBorder ( Graphics g ) {
		g.setColor( Color.WHITE );
		g.drawRoundRect(
				0,					/* X-origin */
				0,					/* Y-origin */
				this.getWidth(),	/* width */
				this.getHeight(),	/* height */
				this.getHeight(),	/* horizontal diameter of the arc at the four corners */
				this.getHeight()	/* vertical diameter of the arc at the four corners */
		);
	}
	
	@Override
	public boolean contains ( int x, int y ) {
        if ( shape == null || !shape.getBounds().equals( getBounds() ) ) {
            shape = new RoundRectangle2D.Float(
            		0,					/* X-origin */
            		0,					/* Y-origin */
            		this.getWidth(),	/* width */
            		this.getHeight(),	/* height */
            		this.getHeight(),	/* horizontal diameter of the arc at the four corners */
            		this.getHeight()	/* vertical diameter of the arc at the four corners */
            );
        }
        
        return shape.contains( x, y );
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension( 346, 30 );
	}
	
}
