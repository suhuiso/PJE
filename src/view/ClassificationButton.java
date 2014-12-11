package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import utils.Tweet;
import controller.AppController;
import feeling.Feeling;

@SuppressWarnings("serial")
public class ClassificationButton extends JButton {

	private AppController controller;
	private Feeling feeling;
	
	public ClassificationButton ( AppController controller, String text, Feeling feeling ) {
		super( text );
		this.setForeground( Color.WHITE );
		this.setBackground( new Color( 0x2F3238 ) );
		this.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
		this.setFont( new Font( "Lucida Sans", Font.PLAIN, 15 ) );
		
		this.controller = controller;
		this.feeling = feeling;
		
		this.setOpaque( true );
				
		this.initListener();
	}
	
	@Override
	public Dimension getPreferredSize () {
		return new Dimension( 150, 35 );
	}
	
	public Feeling getFeeling () {
		return this.feeling;
	}
	
	public void highlight () {
		this.setForeground( new Color( 0x2F3238 ) );
		this.setBackground( new Color( 0xFCD04B ) );
		this.repaint();
	}
	
	public void unhighlight () {
		this.setForeground( Color.WHITE );
		this.setBackground( new Color( 0x2F3238 ) );
		this.repaint();
	}
	
	private void initListener () {
		ClassificationButtonListener classificationButtonListener = new ClassificationButtonListener();
		this.addActionListener( classificationButtonListener );
	}
	
	class ClassificationButtonListener implements ActionListener {
		public void actionPerformed ( ActionEvent e ) {	
			ClassificationButton buttonPressed = ( ClassificationButton ) e.getSource();
			ClassificationsPanel classificationsPanel = ( ClassificationsPanel ) buttonPressed.getParent();
			LearningTweetPanel learningTweetPanel = ( LearningTweetPanel ) classificationsPanel.getParent();
			Tweet currentTweet = learningTweetPanel.getTweet();
			ClassificationButton.this.controller.saveRequest( currentTweet, buttonPressed.getFeeling() );
			classificationsPanel.unhighlightButtons();
			buttonPressed.highlight();
			System.out.println( "ClassificationButtonListener@actionPerformed: " + buttonPressed.getFeeling() );
		}
	}
}
