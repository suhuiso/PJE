package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AppController;

@SuppressWarnings("serial")
public class ContentPanel extends JPanel implements Observer {

	private FeelingsPanel feelingsPanel;
	private TendenciesPanel tendenciesPanel;
	private LearningPanel learningPanel;
	private EvaluationPanel evaluationPanel;
	private SettingsPanel settingsPanel;
	private AbstractCardPanel currentPanel;
	
	public ContentPanel ( AppController controller ) {
		super();		
		this.setBackground( Color.WHITE );
		this.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );

		this.setLayout( new CardLayout() );
				
		this.feelingsPanel = new FeelingsPanel( controller );
		this.tendenciesPanel = new TendenciesPanel( controller );
		this.learningPanel = new LearningPanel( controller );
		this.evaluationPanel = new EvaluationPanel( controller );
		this.settingsPanel = new SettingsPanel( controller );
		this.currentPanel = this.feelingsPanel;
		
		this.add( this.feelingsPanel, FeelingsPanel.CARD_FEELINGS );
		this.add( this.tendenciesPanel, TendenciesPanel.CARD_TENDENCIES );
		this.add( this.learningPanel, LearningPanel.CARD_LEARNING );
		this.add( this.evaluationPanel, EvaluationPanel.CARD_EVALUATION );
		this.add( this.settingsPanel, SettingsPanel.CARD_SETTINGS );
	}

	public AbstractCardPanel getCurrentPanel () {
		return this.currentPanel;
	}
	
	public void setCurrentPanel ( AbstractCardPanel newPanel ) {
		System.out.println( "setCurrentPanel:" + newPanel.toString() );
		this.getCurrentPanel().clear();
		this.currentPanel = newPanel;
	}
	
	@Override
	public void update ( Observable o, Object arg ) {
		this.getCurrentPanel().update( o, arg );
	}
	
	public AbstractCardPanel getPanelByName ( String cardName ) {	
		if ( cardName.equals( FeelingsPanel.CARD_FEELINGS ) ) {
			return this.feelingsPanel;
		} else if ( cardName.equals( TendenciesPanel.CARD_TENDENCIES ) ) {
			return this.tendenciesPanel;
		} else if ( cardName.equals( LearningPanel.CARD_LEARNING ) ) {
			return this.learningPanel;
		} else if ( cardName.equals( EvaluationPanel.CARD_EVALUATION) ) {
			return this.evaluationPanel;
		} else {
			return this.settingsPanel;
		}
	}
}
