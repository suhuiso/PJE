package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.AppController;

@SuppressWarnings("serial")
public class CountTweetsPanel extends JPanel {

	private AppController controller;
	private JLabel label;
	private JComboBox<Integer> selectField;
	private final static Integer[] COUNT_AVAILABLES = { 10, 25, 50, 100 };
	
	public CountTweetsPanel( AppController controller, Color color ) {
		super();
		this.setBackground( color );
		
		this.setLayout( new BorderLayout() );
		
		this.controller = controller;
		this.label = new JLabel( "Nombre de tweets par recherche :" );
		this.selectField = new JComboBox<Integer>( CountTweetsPanel.COUNT_AVAILABLES );
		this.selectField.setSelectedIndex( 0 );
		
		this.label.setFont( new Font( "Lucida Sans", Font.PLAIN, 18 ) );
		this.selectField.setFont( new Font( "Lucida Sans", Font.PLAIN, 18 ) );

		this.add( this.label, BorderLayout.WEST );
		this.add( this.selectField, BorderLayout.EAST );
		
		this.initListeners();
	}
	
	private void initListeners () {
		this.selectField.addActionListener( new SelectFieldListener() );
	}
	
	@Override
	public Dimension getPreferredSize () {
		return new Dimension( 400, 50 );
	}

	@Override
	public Dimension getMaximumSize () {
		return new Dimension( 400, 80 );
	}
	
	class SelectFieldListener implements ActionListener {
		public void actionPerformed ( ActionEvent e ) {
			JComboBox<Integer> selectField = ( JComboBox<Integer> ) e.getSource();
			Integer countTweet = ( Integer ) selectField.getSelectedItem();
			CountTweetsPanel.this.controller.setCountTweets( countTweet );
		}
	}
}
