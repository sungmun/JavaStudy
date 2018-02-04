package View;

import View.BaseClass.BasicPanel;

@SuppressWarnings("serial")
public class GameViewPanel extends BasicPanel {

	public GameViewPanel() {
		super(true);
		add(new TetrinoBlockPanel());
	}
}
