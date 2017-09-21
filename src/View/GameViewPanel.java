package View;

import javax.swing.JPanel;

import Control.TetrisControlManager;

@SuppressWarnings("serial")
public class GameViewPanel extends JPanel {
	TetrisMapPanel mappanel;


	public GameViewPanel(TetrisControlManager manager) {
		mappanel = new TetrisMapPanel(manager);
		setOpaque(false);

		add(mappanel);
	}

}
