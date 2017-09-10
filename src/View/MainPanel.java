package View;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainPanel extends JPanel {

	private GameViewPanel gamepanel;
	private ETC_Panel etc_panel;

	public MainPanel(int width, int height) {
		etc_panel = new ETC_Panel(width, height);
		gamepanel = new GameViewPanel(width, height);
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setOpaque(false);
		
		etc_panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 20));
		add(gamepanel);
		add(etc_panel);
	}

}
