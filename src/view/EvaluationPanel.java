package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.AppController;
import feeling.Classifier;

@SuppressWarnings ( "serial" )
public class EvaluationPanel extends AbstractCardPanel {

	public static final String CARD_EVALUATION = "Evaluation Panel";

	public static final Color EVALUATION_COLOR = new Color( 0xF06060 );

	public static final Color EVALUATION_COLOR_BRIGHTER = new Color( 0xF06060 );

	private AppController controller;

	private Classifier classifier;

	private EvaluationTitlePanel evaluationTitlePanel;

	private JLabel evaluationValueLabel;

	private JButton evaluationButton;

	public EvaluationPanel ( AppController controller ) {
		super( controller );
		this.setBackground( EvaluationPanel.EVALUATION_COLOR );
		this.setBorder( new EmptyBorder( 25, 25, 25, 25 ) );

		this.setLayout( new BorderLayout() );

		this.controller = controller;
		this.classifier = this.controller.getCurrentClassifier();

		this.evaluationTitlePanel =
		        new EvaluationTitlePanel( controller, EvaluationPanel.EVALUATION_COLOR );

		this.evaluationValueLabel = new JLabel( "", SwingConstants.CENTER );
		this.evaluationValueLabel.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
		this.evaluationValueLabel.setFont( new Font( "Lucida Sans", Font.PLAIN, 100 ) );

		this.evaluationButton = new JButton( "Evaluer" );
		if ( ! ( this.controller.getCurrentClassifier().isCrossValidable() ) ) {
			this.evaluationButton.setText( "Evaluation impossible sur ce classifieur" );
			this.evaluationButton.setEnabled( false );
		}
		this.evaluationButton.setForeground( Color.WHITE );
		this.evaluationButton.setBackground( new Color( 0x2F3238 ) );
		this.evaluationButton.setBorder( BorderFactory.createMatteBorder( 15, 150, 15, 150,
		        EvaluationPanel.EVALUATION_COLOR ) );
		this.evaluationButton.setFont( new Font( "Lucida Sans", Font.PLAIN, 20 ) );
		this.evaluationButton.setPreferredSize( new Dimension( 150, 100 ) );
		this.evaluationButton.setOpaque( true );

		this.add( this.evaluationTitlePanel, BorderLayout.NORTH );
		this.add( this.evaluationValueLabel, BorderLayout.CENTER );
		this.add( this.evaluationButton, BorderLayout.SOUTH );

		this.initListeners();
	}

	public void update ( Observable o, Object arg ) {
		DecimalFormat df = new DecimalFormat( "###.##" );
		Double value = ( Double ) arg;
		this.evaluationValueLabel.setText( df.format( value ) + " %" );
	}

	public void clear () {
		this.evaluationValueLabel.setText( "" );
	}

	public String toString () {
		return EvaluationPanel.CARD_EVALUATION;
	}

	@Override
	public void resume () {
		this.classifier = this.controller.getCurrentClassifier();
		this.evaluationTitlePanel.resume();
		if ( ! ( this.classifier.isCrossValidable() ) ) {
			this.evaluationButton.setText( "Evaluation impossible sur ce classifieur" );
			this.evaluationButton.setEnabled( false );
		} else {
			this.evaluationButton.setText( "Evaluer" );
			this.evaluationButton.setEnabled( true );
		}
	}

	private void initListeners () {
		EvaluationButtonListener EvaluationButtonListener = new EvaluationButtonListener();
		this.evaluationButton.addActionListener( EvaluationButtonListener );
	}

	class EvaluationButtonListener implements ActionListener {

		public void actionPerformed ( ActionEvent e ) {
			EvaluationPanel.this.controller.evaluateCurrentClassifier();
		}
	}
}
