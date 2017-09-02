package Control;

import View.MainView;

public class Control {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TetrisControlManager.createTetrisControlManager();
		
		new MainView(36, 36);
	}

}
