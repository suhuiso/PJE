package view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AppController;

@SuppressWarnings("serial")
public class LearningPanel extends JPanel {

    public static final String CARD_LEARNING =  "Learning Panel";

	public LearningPanel( AppController controller ) {
		super();		
		this.setBackground( new Color( 0xFCD04B ) );
		this.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
	}
}
