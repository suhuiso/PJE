package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AppController;

@SuppressWarnings("serial")
public class AppPanel extends JPanel implements Observer {

	private SidebarPanel sidebarPanel;
	private ContentPanel contentPanel;
	
	public AppPanel ( AppController controller ) {
		super();
		this.setBackground( Color.WHITE );
		this.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );

		this.setLayout( new BorderLayout() );
				
		this.sidebarPanel = new SidebarPanel( controller );
		this.contentPanel = new ContentPanel( controller );
		
		this.add( this.sidebarPanel, BorderLayout.WEST );
		this.add( this.contentPanel, BorderLayout.CENTER );
	}
	
	public SidebarPanel getSidebarPanel () {
		return this.sidebarPanel;
	}
	
	public ContentPanel getContentPanel () {
		return this.contentPanel;
	}

	@Override
	public void update( Observable o, Object arg ) {
		this.contentPanel.update( o, arg );
	}
}
