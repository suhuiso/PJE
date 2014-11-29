package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AppController;

/**
 * Main panel of the application.
 * 
 * @author Quentin Baert & Thomas Bernard
 */
@SuppressWarnings("serial")
public class AppPanel extends JPanel implements Observer {

	////////////
	// FIELDS //
	////////////
	
	/**
	 * Controller of the AppPanel
	 */
	private AppController controller;
	
	/**
	 * Header Panel
	 */
	private HeaderPanel headerPanel;
	
	/**
	 * Content Panel
	 */
	private SidebarPanel sidebarPanel;
	private ContentPanel contentPanel;
	
	/////////////
	// METHODS //
	/////////////

	/**
	 * Constructor of the AppPanel
	 * 
	 * @param controller
	 *            controller of the AppPanel
	 */
	public AppPanel ( AppController controller ) {
		super();
		
		this.setBackground( new Color( 0x55ACEE ) );		/* color: blue */
		this.setBorder( new EmptyBorder( 8, 8, 8, 8 ) );	/* padding : 8px */

		this.setLayout( new BorderLayout() );
		
		/* Controller of AppPanel is added */
		this.controller = controller;
		
		/* Add content fields */
		this.sidebarPanel = new SidebarPanel( controller );
		this.contentPanel = new ContentPanel( controller );
		
		/*
		 * CONTENT :
		 * 
		 * HeaderPanel : Header of the application
		 * ContentPanel : Main content 
		 */
		this.add( this.sidebarPanel, BorderLayout.WEST );
		this.add( this.contentPanel, BorderLayout.CENTER );
	}
	
	public SidebarPanel getSidebarPanel () {
		return this.sidebarPanel;
	}
	
	public ContentPanel getContentPanel () {
		return this.contentPanel;
	}
}
