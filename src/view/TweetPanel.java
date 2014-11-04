package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TweetPanel extends JPanel {

	/////////////
	// METHODS //
	/////////////

	/**
	 * Constructor of the TweetPanel
	 */
	public TweetPanel() {
		super();

		this.setBackground( new Color( 0x55ACEE ) );		/* color: blue */
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension( 50, 50 );
	}
}
