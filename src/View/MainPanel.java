package View;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainPanel extends JPanel {

	private ETC_Panel etc_panel;

	public MainPanel() {
		super(true);
		etc_panel = new ETC_Panel();

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setOpaque(false);

		add(new TetrinoBlockPanel());
		add(etc_panel);
	}
}
