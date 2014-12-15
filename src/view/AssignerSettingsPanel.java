package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import controller.AppController;

@SuppressWarnings ( "serial" )
public class AssignerSettingsPanel extends JPanel {

	private AppController controller;

	private JLabel label;

	private JRadioButton[] radios;

	private ButtonGroup buttonGroup;

	public AssignerSettingsPanel ( AppController controller, Color color ) {
		super();
		this.setBackground( color );
		this.setLayout( new GridLayout( 0, 1 ) );

		this.controller = controller;
		this.label = new JLabel( "Classifieurs disponibles :" );
		this.radios = new JRadioButton[ 14 ];

		this.radios[ 0 ] = new JRadioButton( "Dictionnaire" );
		this.radios[ 1 ] = new JRadioButton( "KNN" );
		this.radios[ 2 ] = new JRadioButton( "Bayes : présence, non simplifié, uni-grammes" );
		this.radios[ 3 ] = new JRadioButton( "Bayes : présence, simplifié, uni-grammes" );
		this.radios[ 4 ] = new JRadioButton( "Bayes : présence, non simplifié, bi-grammes" );
		this.radios[ 5 ] = new JRadioButton( "Bayes : présence, simplifié, uni-grammes" );
		this.radios[ 6 ] =
		        new JRadioButton( "Bayes : présence, non simplifié, uni-grammes + bi-grammes" );
		this.radios[ 7 ] =
		        new JRadioButton( "Bayes : présence, simplifié, uni-grammes + bi-grammes" );
		this.radios[ 8 ] = new JRadioButton( "Bayes : fréquence, non simplifié, uni-grammes" );
		this.radios[ 9 ] = new JRadioButton( "Bayes : fréquence, simplifié, uni-grammes" );
		this.radios[ 10 ] = new JRadioButton( "Bayes : fréquence, non simplifié, bi-grammes" );
		this.radios[ 11 ] = new JRadioButton( "Bayes : fréquence, simplifié, uni-grammes" );
		this.radios[ 12 ] =
		        new JRadioButton( "Bayes : fréquence, non simplifié, uni-grammes + bi-grammes" );
		this.radios[ 13 ] =
		        new JRadioButton( "Bayes : fréquence, simplifié, uni-grammes + bi-grammes" );

		this.buttonGroup = new ButtonGroup();

		this.radios[ this.controller.getCurrentClassifierId() ].setSelected( true );;
		
		this.label.setFont( new Font( "Lucida Sans", Font.PLAIN, 18 ) );
		this.add( this.label );

		for ( int i = 0; i < this.radios.length; i++ ) {
			this.radios[ i ].setFont( new Font( "Lucida Sans", Font.PLAIN, 18 ) );
			this.buttonGroup.add( this.radios[ i ] );
			this.add( this.radios[ i ] );
		}

		this.initListeners();
	}

	private void initListeners () {
		for ( int i = 0; i < this.radios.length; i++ ) {
			this.radios[ i ].addActionListener( new RadioButtonListener( i ) );
		}
	}

	@Override
	public Dimension getPreferredSize () {
		return new Dimension( 750, 400 );
	}

	@Override
	public Dimension getMaximumSize () {
		return new Dimension( 750, 400 );
	}

	class RadioButtonListener implements ActionListener {

		private int assignerUsed;

		public RadioButtonListener ( int assignerUsed ) {
			this.assignerUsed = assignerUsed;
		}

		public void actionPerformed ( ActionEvent e ) {
			AssignerSettingsPanel.this.controller.setCurrentClassifierId( this.assignerUsed );
		}
	}
}
