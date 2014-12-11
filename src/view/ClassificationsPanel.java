package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AppController;
import feeling.Feeling;

@SuppressWarnings("serial")
public class ClassificationsPanel extends JPanel {

	private ClassificationButton negativeButton;
	private ClassificationButton neutralButton;
	private ClassificationButton positiveButton;
	
	public ClassificationsPanel ( AppController controller ) {
		super();
		
		this.setBackground( Color.WHITE );
		this.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
		
		this.negativeButton = new ClassificationButton( controller, "Negatif", Feeling.NEGATIVE );
		this.neutralButton = new ClassificationButton( controller, "Neutre", Feeling.NEUTRAL );
		this.positiveButton = new ClassificationButton( controller, "Positif", Feeling.POSITIVE );
		
		this.add( this.negativeButton );
		this.add( this.neutralButton );
		this.add( this.positiveButton );
	}
	
	@Override
	public Dimension getPreferredSize () {
		return new Dimension( 500, 50 );
	}
}
