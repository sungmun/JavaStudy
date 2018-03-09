package Control;

import Model.ModelInstentCreate;
import Serversynchronization.User;
import View.StartFrame;

public class MainThread {
public static boolean gameflag=true;
	public static void main(String[] args) {
		
		new ControlInstentCreate();
		new ModelInstentCreate();
		UserControl.users.setPlayer(new User());
		
		StartFrame startfr = new StartFrame();
		startfr.setVisible(true);
	}

}
