package Control;

import View.MainFrame;

public class Control {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		TetrisControlManager.createTetrisControlManager();
		TetrisControlManager.getTetrisControlManager().createBlock();
		MainFrame.createMainView(36, 36);
		MainFrame.getTime().start();
	}

}
