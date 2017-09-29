package Control;

import View.StartFrame;

public class Control {
	UserControl user = UserControl.createUserControl();
	OpponentControl opponent = OpponentControl.createOpponentControl();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		StartFrame startfr = StartFrame.createStartFrame();
		startfr.setVisible(true);
	}

}
