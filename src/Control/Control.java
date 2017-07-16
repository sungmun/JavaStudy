package Control;

import View.MainView;

public class Control {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TetrisControlManager manager=TetrisControlManager.createTetrisControlManager();
		
		MainView view=new MainView(36, 36);
		new Tic().run();
	}

}
