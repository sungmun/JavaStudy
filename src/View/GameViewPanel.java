package View;

import View.BaseClass.BasicPanel;

public class GameViewPanel extends BasicPanel {

	/**
	 * 이 Panel은 Tetris의 진행 과정을 보여주는 패널을 포함한 패널이다
	 * 그림을 바로그리기에는 LayoutManager의 문제가 있어서 만들었다  
	 */
	private static final long serialVersionUID = 7533150295448706989L;

	public GameViewPanel() {
		super(true);
		add(new TetrinoBlockPanel());
	}
}
