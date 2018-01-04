package View.Single;

import Control.KeyBoardEvent;
import View.CellSize;
import View.GameBasicFrame;
import View.PanelForTheUser;

@SuppressWarnings("serial")
public class SingleFrame extends GameBasicFrame implements CellSize {

	static private SingleFrame singleviewcopy = null;

	public SingleFrame() {

		PanelForTheUser userPanel=new PanelForTheUser();
		time=userPanel.getTimer();
		time.start();

		this.add(userPanel);

		this.setUndecorated(true);
		this.pack();
		this.setLocationRelativeTo(null);

		this.addKeyListener(new KeyBoardEvent());
	}

	public static SingleFrame createSingleFrame() {
		if (singleviewcopy == null) {
			singleviewcopy = new SingleFrame();
		}
		return singleviewcopy;
	}

	public static SingleFrame getMainviewcopy() {
		return singleviewcopy;
	}
}
