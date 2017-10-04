package Control;

import Model.OpponentControl;
import Model.UserControl;
import View.StartFrame;

public class Control {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserControl.createUserControl();
		OpponentControl.createOpponentControl();
		StartFrame startfr = StartFrame.createStartFrame();
		startfr.setVisible(true);
	}

}
