package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.List;
import java.util.Observable;

import javax.swing.JPanel;

import utils.Tweet;
import controller.AppController;

@SuppressWarnings("serial")
public class TendenciesPanel extends AbstractCardPanel {

    public static final String CARD_TENDENCIES =  "Tendencies Panel";
    public static final Color TENDENCIES_COLOR = new Color( 0x2ECC71 );
    
    private SearchBarPanel searchBarPanel;
    
	public TendenciesPanel( AppController controller ) {
		super( controller );		
		this.setBackground( TendenciesPanel.TENDENCIES_COLOR );
						
		this.searchBarPanel = new SearchBarPanel( controller, TendenciesPanel.TENDENCIES_COLOR );
		
		this.add( this.searchBarPanel, BorderLayout.NORTH );
	}

	@Override
	public void update ( Observable o, Object arg ) {
		@SuppressWarnings ( "unchecked" )
		List< Tweet > lt = ( List< Tweet > ) arg;
		
		try {
	        this.add( new ImagePanel( this.controller.pieChartImageRequest( lt ) ) );
	        /* View have changed and need to be repaint */
			this.revalidate();
        } catch ( IllegalArgumentException | IOException e ) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
	}

	@Override
	public void clear () {
		// TODO Auto-generated method stub
	}
	
	private class ImagePanel extends JPanel {
		private Image image;
		
		public ImagePanel ( Image image ) {
			this.image = image;
		}
		
		@Override
		protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.drawImage(this.image, 0, 0, null);         
	    }
	}
}
