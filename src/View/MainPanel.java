package View;

import javax.swing.BoxLayout;

import View.BaseClass.BasicPanel;

@SuppressWarnings("serial")
public class MainPanel extends BasicPanel {

	private ETC_Panel etc_panel;

	public MainPanel() {
		super(true);
		etc_panel = new ETC_Panel();

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		add(new TetrinoBlockPanel());
		add(etc_panel);
	}
}
