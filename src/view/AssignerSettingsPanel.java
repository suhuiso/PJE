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

@SuppressWarnings("serial")
public class AssignerSettingsPanel extends JPanel {

	private AppController controller;
	private JLabel label;
	private JRadioButton radio1;
	private JRadioButton radio2;
	private JRadioButton radio3;
	private JRadioButton radio4;
	private JRadioButton radio5;
	private ButtonGroup buttonGroup;
	
	public AssignerSettingsPanel ( AppController controller, Color color ) {
		super();
		this.setBackground( color );
		this.setLayout( new GridLayout( 0, 1 ) );
		
		this.controller = controller;
		this.label = new JLabel( "Classifieurs disponibles :" );
		this.radio1 = new JRadioButton( "Dictionnaire" );
		this.radio2 = new JRadioButton( "KNN" );
		this.radio3 = new JRadioButton( "Bayes par présence" );
		this.radio4 = new JRadioButton( "Bayes par fréquence" );
		this.radio5 = new JRadioButton( "Bayes par fréquence uni-gramme" );
		this.buttonGroup = new ButtonGroup();

		this.buttonGroup.add( this.radio1 );
		this.buttonGroup.add( this.radio2 );
		this.buttonGroup.add( this.radio3 );
		this.buttonGroup.add( this.radio4 );
		this.buttonGroup.add( this.radio5 );

		this.label.setFont( new Font( "Lucida Sans", Font.PLAIN, 18 ) );
		this.radio1.setFont( new Font( "Lucida Sans", Font.PLAIN, 18 ) );
		this.radio2.setFont( new Font( "Lucida Sans", Font.PLAIN, 18 ) );
		this.radio3.setFont( new Font( "Lucida Sans", Font.PLAIN, 18 ) );
		this.radio4.setFont( new Font( "Lucida Sans", Font.PLAIN, 18 ) );
		this.radio5.setFont( new Font( "Lucida Sans", Font.PLAIN, 18 ) );

		this.add( this.label );
		this.add( this.radio1 );
		this.add( this.radio2 );
		this.add( this.radio3 );
		this.add( this.radio4 );
		this.add( this.radio5 );
				
		//int currentAssigner = this.controller.getCurrentClassifier();
		
		/*if ( currentAssigner == 0 ) {
			this.prefs.putInt( "CURRENT_ASSIGNER", 1 );
			this.radio1.setSelected( true );
		} else {
			// TODO select le classifieur courant
		}*/
		
		this.initListeners();
	}
	
	private void initListeners () {
		this.radio1.addActionListener( new RadioButtonListener( 0 ) );
		this.radio2.addActionListener( new RadioButtonListener( 1 ) );
		this.radio3.addActionListener( new RadioButtonListener( 2 ) );
		this.radio4.addActionListener( new RadioButtonListener( 3 ) );
		this.radio5.addActionListener( new RadioButtonListener( 4 ) );
	}
	
	@Override
	public Dimension getPreferredSize () {
		return new Dimension( 750, 200 );
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
