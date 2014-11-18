package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * Search bar field of header panel.
 * 
 * @author Quentin Baert & Thomas Bernard
 */
@SuppressWarnings("serial")
public class SearchBarField extends JTextField {

	/////////////
	// METHODS //
	/////////////

	/**
	 * Constructor of the SearchBarField
	 */
	public SearchBarField () {
		super();

		this.setBackground( Color.WHITE );					/* color: white */
		this.setBorder( new EmptyBorder( 0, 10, 0, 10 ) );	/* padding: 0 */
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension( 300, 30 );
	}
	
}
