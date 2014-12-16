package view;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
import java.util.Observable;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utils.Tweet;
import controller.AppController;

@SuppressWarnings("serial")
public class StatisticsPanel extends JPanel {

	private AppController controller;
	private Color color;
	
	public StatisticsPanel( AppController controller, Color color ) {
		super();
		this.setBackground( color );
		this.setBorder( new EmptyBorder( 25, 25, 25, 25 ) );

		this.controller = controller;
		this.color = color;
	}
	
	public void drawPieChart ( List< Tweet > list ) {
		this.removeAll();
		
		try {
			this.add( new PieChartPanel( this.controller.pieChartImageRequest( list ), this.color ) );
	        
	        /* View have changed and need to be repaint */
			this.revalidate();
        } catch ( IOException e ) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
	}
	
	public void update ( Observable o, Object arg ) {
		@SuppressWarnings ( "unchecked" )
        List< Tweet > list = ( List< Tweet > ) arg;
		this.drawPieChart( list );
	}
	
	public void clear () {
		this.removeAll();
	}
}
