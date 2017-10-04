package Model;

import View.StartFrame;

public class MainThread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserControl.createUserControl();
		OpponentControl.createOpponentControl();
		StartFrame startfr = StartFrame.createStartFrame();
		startfr.setVisible(true);
	}

}
