package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import controller.AppController;

@SuppressWarnings("serial")
public class AppView extends JFrame implements Observer {

	private AppPanel appPanel;
	private AppController controller;
	
	public AppView ( AppController controller ) {
		this.setTitle( "AwesomeName" );
		this.setSize( 1000, 600 );
		this.setResizable( false );		
		this.setLocationRelativeTo( null );
		this.setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );
		
		this.appPanel = new AppPanel( controller );
		this.controller = controller;
		
		WindowListener exitListener = new WindowAdapter () {
			@Override
			public void windowClosing ( WindowEvent e ) {
				AppView.this.controller.closingWindowSaveRequest();
				System.exit(0);
			}
		};
        this.addWindowListener( exitListener );
		
		this.setContentPane( appPanel );
		
		this.setVisible( true );
	}

	@Override
	public void update ( Observable o, Object arg ) {
		this.appPanel.update( o, arg );
	}
}
