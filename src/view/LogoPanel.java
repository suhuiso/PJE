package view;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AppController;

@SuppressWarnings("serial")
public class LogoPanel extends JPanel {

	private AppController controller;

	public LogoPanel( AppController controller ) {
		super();
		this.setBackground( new Color( 0x2F3238 ) );
		this.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
		
		this.controller = controller;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension( 200, 200 );
	}
}
