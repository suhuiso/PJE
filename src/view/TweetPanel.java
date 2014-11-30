package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import twitter4j.Status;
import controller.AppController;

@SuppressWarnings("serial")
public class TweetPanel extends JPanel {

	private Status status;
	
	public TweetPanel ( AppController controller, Status status ) {
		super();
		this.setBackground( Color.WHITE );
		this.setBorder( new EmptyBorder( 10, 10, 10, 10 ) );
		
		this.setLayout( new BorderLayout() );
				
		this.status = status;
		
		this.add( new JLabel( "@" + this.status.getUser().getScreenName() ), BorderLayout.NORTH );
		this.add( new JLabel( this.status.getText() ), BorderLayout.CENTER );
	}

	@Override
	public Dimension getPreferredSize () {
		return new Dimension( 750, 100 );
	}
}
