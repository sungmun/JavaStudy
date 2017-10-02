package View;

import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import Control.TetrisControlManager;

@SuppressWarnings("serial")
public class MainPanel extends JPanel {

	private GameViewPanel gamepanel;
	private ETC_Panel etc_panel;

	public MainPanel(TetrisControlManager manager) {
		super(true);
		etc_panel = new ETC_Panel(manager);
		gamepanel = new GameViewPanel(manager);

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setOpaque(false);

		etc_panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(gamepanel);
		add(etc_panel);
	}
	@Override
	public void update(Graphics g) {
		paintComponent(g);
	}
	@Override
	public void repaint() {
		super.repaint();
		try {
			etc_panel.repaint();
			gamepanel.repaint();
		} catch (NullPointerException e) {}
	}

}
