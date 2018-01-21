package View.Single;

import Control.KeyBoardEvent;
import View.GameBasicFrame;
import View.PanelForTheUser;

@SuppressWarnings("serial")
public class SingleFrame extends GameBasicFrame {

	public SingleFrame() {

		PanelForTheUser userPanel=new PanelForTheUser();
		time=userPanel.getTimer();

		this.add(userPanel);

		this.setUndecorated(true);
		this.pack();
		this.setLocationRelativeTo(null);

		time.start();
		this.addKeyListener(new KeyBoardEvent());
	}

}
