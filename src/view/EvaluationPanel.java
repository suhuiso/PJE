package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.AppController;

@SuppressWarnings("serial")
public class EvaluationPanel extends AbstractCardPanel {

    public static final String CARD_EVALUATION =  "Evaluation Panel";
    public static final Color EVALUATION_COLOR = new Color( 0xF06060 );
    public static final Color EVALUATION_COLOR_BRIGHTER = new Color( 0xF06060 );
    
    private AppController controller;
    private EvaluationTitlePanel evaluationTitlePanel;
    private JLabel evaluationValueLabel;
    private JButton evaluationButton;
    
	public EvaluationPanel( AppController controller ) {
		super( controller );
		this.setBackground( EvaluationPanel.EVALUATION_COLOR );
		this.setBorder( new EmptyBorder( 25, 25, 25, 25 ) );
		
		this.setLayout( new BorderLayout() );
		
		this.controller = controller;
		
		this.evaluationTitlePanel = new EvaluationTitlePanel( controller, EvaluationPanel.EVALUATION_COLOR );
		
		this.evaluationValueLabel = new JLabel( "53%", SwingConstants.CENTER );
		this.evaluationValueLabel.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
		this.evaluationValueLabel.setFont( new Font( "Lucida Sans", Font.PLAIN, 100 ) );
		
		this.evaluationButton = new JButton( "Evaluer" );
		this.evaluationButton.setForeground( Color.WHITE );
		this.evaluationButton.setBackground( new Color( 0x2F3238 ) );
		this.evaluationButton.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
		this.evaluationButton.setFont( new Font( "Lucida Sans", Font.PLAIN, 20 ) );
		this.evaluationButton.setPreferredSize( new Dimension( 150, 100 ) );
		this.evaluationButton.setOpaque( true );
		
		this.add( this.evaluationTitlePanel, BorderLayout.NORTH );
		this.add( this.evaluationValueLabel, BorderLayout.CENTER );
		this.add( this.evaluationButton, BorderLayout.SOUTH );
	}

	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
	}

	public void clear() {
		// TODO Auto-generated method stub
	}
	
	public String toString () {
		return EvaluationPanel.CARD_EVALUATION;
	}

	@Override
	public void resume() {
		// TODO
	}
}
