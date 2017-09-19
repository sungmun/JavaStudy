package Control;

import View.MainFrame;

public class Control {
	UserControl user=new UserControl();
	OpponentControl opponent=new OpponentControl(); 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		MainFrame.createMainView(36, 36);
		MainFrame.getTime().start();
	}

}
