package View;

import java.awt.Graphics;

import javax.swing.JPanel;

import Control.TetrisControlManager;

@SuppressWarnings("serial")
public class GameViewPanel extends JPanel {
	TetrisMapPanel mappanel;

	public GameViewPanel(TetrisControlManager manager) {
		super(true);
		mappanel = new TetrisMapPanel(manager);
		setOpaque(false);
		add(mappanel);
	}
	public void update(Graphics g) {
		paintComponent(g);
	}
	@Override
	public void repaint() {
		super.repaint();
		try {
			mappanel.repaint();
		}catch (NullPointerException e) {}
	}
}
