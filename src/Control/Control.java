package Control;

import View.MainView;

public class Control {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		TetrisControlManager.createTetrisControlManager();
		TetrisControlManager.getTetrisControlManager().createBlock();
		MainView.createMainView(36, 36);
		MainView.getTime().start();
	}

}
