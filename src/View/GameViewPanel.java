package View;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameViewPanel extends JPanel {

	public GameViewPanel() {
		super(true);
		setOpaque(false);
		add(new TetrinoBlockPanel());
	}
}
