package Control;

import Model.ModelInstentCreate;
import View.StartFrame;

public class MainThread {

	public static void main(String[] args) {
		
		new ControlInstentCreate();
		new ModelInstentCreate();
		UserControl.users.setPlayer(new User());
		
		StartFrame startfr = StartFrame.createStartFrame();
		startfr.setVisible(true);
	}

}
