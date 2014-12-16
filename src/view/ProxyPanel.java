package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.AppController;

@SuppressWarnings("serial")
public class ProxyPanel extends JPanel {

	private AppController controller;
	private JLabel label;
	private JComboBox<String> selectField;
	private final static String[] PROXY_USE = { "Non", "Oui" };
	
	public ProxyPanel( AppController controller, Color color ) {
		super();
		this.setBackground( color );
		
		this.setLayout( new BorderLayout() );
		
		this.controller = controller;
		this.label = new JLabel( "Utilisation du proxy Lille 1 :" );
		this.selectField = new JComboBox<String>( ProxyPanel.PROXY_USE );
		this.selectField.setSelectedIndex( 0 );
		
		this.label.setFont( new Font( "Lucida Sans", Font.PLAIN, 18 ) );
		this.selectField.setFont( new Font( "Lucida Sans", Font.PLAIN, 18 ) );

		this.add( this.label, BorderLayout.WEST );
		this.add( this.selectField, BorderLayout.EAST );
		
		this.initListeners();
	}
	
	private void initListeners () {
		this.selectField.addActionListener( new SelectFieldListener() );
	}
	
	@Override
	public Dimension getPreferredSize () {
		return new Dimension( 400, 50 );
	}

	@Override
	public Dimension getMaximumSize () {
		return new Dimension( 400, 50 );
	}
	
	class SelectFieldListener implements ActionListener {
		public void actionPerformed ( ActionEvent e ) {
			@SuppressWarnings ( "unchecked" )
            JComboBox<String> selectField = ( JComboBox<String> ) e.getSource();
			String proxyUse = ( String ) selectField.getSelectedItem();
			
			if ( proxyUse.equals( ProxyPanel.PROXY_USE[ 1 ] ) ) {
				ProxyPanel.this.controller.setProxyTwitter();
			} else {
				ProxyPanel.this.controller.unsetProxyTwitter();
			} 
		}
	}
}
