package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AppController;

@SuppressWarnings("serial")
public class ClassificationsPanel extends JPanel {

	private JButton negativeButton;
	private JButton neutralButton;
	private JButton positiveButton;
	
	public ClassificationsPanel ( AppController controller ) {
		super();
		
		this.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
		
		this.negativeButton = new JButton( "Negatif" );
		this.neutralButton = new JButton( "Neutre" );
		this.positiveButton = new JButton( "Positif" );
		
		this.add( this.negativeButton );
		this.add( this.neutralButton );
		this.add( this.positiveButton );
	}
	
	@Override
	public Dimension getPreferredSize () {
		return new Dimension( 500, 50 );
	}
}
