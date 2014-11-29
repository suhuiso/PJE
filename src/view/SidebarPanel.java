package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AppController;

@SuppressWarnings("serial")
public class SidebarPanel extends JPanel {

	private AppController controller;
	private LogoPanel logoPanel;
	private MenuPanel menuPanel;
	
	public SidebarPanel( AppController controller ) {
		super();
		this.setBackground( Color.WHITE );
		this.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
		
		this.setLayout( new BorderLayout() );
		
		this.controller = controller;
		
		this.logoPanel = new LogoPanel( controller );
		this.menuPanel = new MenuPanel( controller );
		
		this.add( this.logoPanel, BorderLayout.NORTH );
		this.add( this.menuPanel, BorderLayout.CENTER );
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension( 200, 600 );
	}
}
