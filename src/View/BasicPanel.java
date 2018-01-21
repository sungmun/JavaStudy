package View;

import java.awt.LayoutManager;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BasicPanel extends JPanel {
	public BasicPanel() {
		super();
		init();
	}
	public BasicPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		init();
	}
	public BasicPanel(LayoutManager layout) {
		super(layout);
		init();
	}
	public BasicPanel(LayoutManager layout,boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		init();
	}
	public void init() {
		this.setOpaque(false);
	}
}
