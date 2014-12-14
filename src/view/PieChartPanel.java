package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PieChartPanel extends JPanel {

	private Image image;
	
	public PieChartPanel( Image image, Color color ) {
		this.image = image;
		this.setBackground( color );
	}
	
	@Override
	protected void paintComponent(Graphics g) {
        super.paintComponent( g );
        g.drawImage( this.image, 0, 0, null );         
    }
	
	@Override
	public Dimension getPreferredSize () {
		return new Dimension( 750, 400 );
	}
}
