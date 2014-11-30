package view;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

@SuppressWarnings("serial")
public class TweetRenderer extends JPanel implements ListCellRenderer<TweetPanel> {

	public TweetRenderer () {
		super();
	}

	@Override
	public Component getListCellRendererComponent ( JList<? extends TweetPanel> list, TweetPanel value, int index, boolean isSelected, boolean cellHasFocus ) {
		return ( TweetPanel ) value;
	}
}
