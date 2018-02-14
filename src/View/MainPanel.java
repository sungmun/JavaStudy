package View;

import javax.swing.BoxLayout;

import View.BaseClass.BasicPanel;

public class MainPanel extends BasicPanel {

	/**
	 * 이 Panel은 TetrinoBlockPanel과 ETC_Panel을 포함하는 Panel이다
	 */
	private static final long serialVersionUID = -2770576741986280991L;
	private ETC_Panel etc_panel;

	public MainPanel() {
		super(true);
		etc_panel = new ETC_Panel();

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		add(new TetrinoBlockPanel());
		add(etc_panel);
	}
}
