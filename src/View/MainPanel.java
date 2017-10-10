package View;

import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainPanel extends JPanel {

	private GameViewPanel gamepanel;
	private ETC_Panel etc_panel;

	public MainPanel() {
		super(true);
		etc_panel = new ETC_Panel();
		gamepanel = new GameViewPanel();

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

}
