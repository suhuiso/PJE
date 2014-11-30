package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AppController;

@SuppressWarnings("serial")
public abstract class AbstractCardPanel extends JPanel implements Observer {

	protected AppController controller;
	
	public AbstractCardPanel ( AppController controller ) {
		super();
		this.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
		
		this.setLayout( new BorderLayout() );
		
		this.controller = controller;
	}

	@Override
	public Dimension getPreferredSize () {
		return new Dimension( 800, 600 );
	}
	
	@Override
	public abstract void update ( Observable o, Object arg );
	
	public abstract void clear ();
}
