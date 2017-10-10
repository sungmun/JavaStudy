package Control;

import Model.OpponentManager;
import Model.UserManager;
import View.StartFrame;

public class MainThread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserManager.createUserManager();
		OpponentManager.createOpponentManager();
		StartFrame startfr = StartFrame.createStartFrame();
		startfr.setVisible(true);
	}

}
