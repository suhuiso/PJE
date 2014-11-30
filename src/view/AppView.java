package view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import controller.AppController;

@SuppressWarnings("serial")
public class AppView extends JFrame implements Observer {

	private AppPanel appPanel;

	public AppView ( AppController controller ) {
		this.setTitle( "AwesomeName" );
		this.setSize( 1000, 600 );
		this.setResizable( false );		
		this.setLocationRelativeTo( null );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		this.appPanel = new AppPanel( controller );
		
		this.setContentPane( appPanel );
		
		this.setVisible( true );
	}

	@Override
	public void update ( Observable o, Object arg ) {
		this.appPanel.update( o, arg );
	}
}
