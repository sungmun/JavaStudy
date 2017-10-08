package View;

import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameViewPanel extends JPanel {
	TetrisMapPanel mappanel;

	public GameViewPanel() {
		super(true);
		mappanel = new TetrisMapPanel();
		setOpaque(false);
		add(mappanel);
	}

	@Override
	public void update(Graphics g) {
		paintComponent(g);
	}

	@Override
	public void repaint() {
		super.repaint();
		try {
			mappanel.repaint();
		} catch (NullPointerException e) {
		}
	}
}
