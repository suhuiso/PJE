package view;

import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Main view of the application.
 * 
 * @author Quentin Baert & Thomas Bernard
 */
@SuppressWarnings("serial")
public class AppView extends JFrame {

	/////////////
	// METHODS //
	/////////////

	/**
	 * Constructor of the AppView
	 */
	public AppView () {

		/* Title of the window */
		this.setTitle("AwesomeName");

		/* Size of the window, in pixels */
		this.setSize(400, 600);

		/* Minimal size to avoid shrinking of the window */
		this.setMinimumSize(new Dimension(400, 600));

		/* App launch at the middle of the screen */
		this.setLocationRelativeTo(null);

		/* App closes when close button is pressed */
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* Window is made visible */
		this.setVisible(true);

		/*
		 * CONTENT :
		 * 
		 * AppPanel : Main Panel of the window
		 */
		this.setContentPane(new AppPanel());
	}

}
