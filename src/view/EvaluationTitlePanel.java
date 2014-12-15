package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.AppController;
import feeling.Classifier;

@SuppressWarnings("serial")
public class EvaluationTitlePanel extends JPanel {

	private AppController controller;
	private Classifier classifier;
	private JLabel evaluationLabel;
	private JLabel classifierLabel;
	
	public EvaluationTitlePanel( AppController controller, Color color ) {
		super();
		this.setBackground( color );
		this.setBorder( new EmptyBorder( 50, 0, 0, 0 ) );
		
		this.setLayout( new BorderLayout() );
		
		this.controller = controller;
		this.classifier = this.controller.getCurrentClassifier();
		
		this.evaluationLabel = new JLabel( "Evaluation du taux d'erreur du classifieur :", SwingConstants.CENTER );
		this.evaluationLabel.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
		this.evaluationLabel.setBackground( color );
		this.evaluationLabel.setForeground( new Color( 0x2F3238 ) );
		this.evaluationLabel.setFont( new Font( "Lucida Sans", Font.PLAIN, 20 ) );
		this.evaluationLabel.setOpaque( true );		
		
		this.classifierLabel = new JLabel( this.classifier.toString(), SwingConstants.CENTER );
		this.classifierLabel.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
		this.classifierLabel.setBackground( color );
		this.classifierLabel.setForeground( new Color( 0x2F3238 ) );
		this.classifierLabel.setFont( new Font( "Lucida Sans", Font.BOLD, 20 ) );
		this.classifierLabel.setOpaque( true );	
		
		this.add( this.evaluationLabel, BorderLayout.NORTH );
		this.add( this.classifierLabel, BorderLayout.SOUTH );
	}
	
	public void resume () {
		this.classifier = this.controller.getCurrentClassifier();
		this.classifierLabel.setText( this.classifier.toString() );
	}
}
