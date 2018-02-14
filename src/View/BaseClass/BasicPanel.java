package View.BaseClass;

import java.awt.LayoutManager;

import javax.swing.JPanel;

public class BasicPanel extends JPanel {
	
	/**
	 * 이 프로젝트에서 사용되는 일련된 하나의 패턴을 유지하기 위한 킇래스로
	 * 이 프로젝트에서 사용되는 모든 Panel은 이 클래스를 사용한다
	 */
	private static final long serialVersionUID = -8048252576275059924L;
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
