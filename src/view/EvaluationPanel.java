package view;

import java.awt.Color;
import java.util.Observable;

import controller.AppController;

@SuppressWarnings("serial")
public class EvaluationPanel extends AbstractCardPanel {

    public static final String CARD_EVALUATION =  "Evaluation Panel";
    public static final Color EVALUATION_COLOR = new Color( 0xF06060 );
    public static final Color EVALUATION_COLOR_BRIGHTER = new Color( 0xF06060 );
    
	public EvaluationPanel( AppController controller ) {
		super( controller );
		this.setBackground( EvaluationPanel.EVALUATION_COLOR );
	}

	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
	}

	public void clear() {
		// TODO Auto-generated method stub
	}
}
