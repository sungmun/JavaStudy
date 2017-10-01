package View.Single;


import javax.swing.Timer;

import Control.TicAction;
import Control.UserTetrisControlManager;
import Model.CellSize;
import Model.MoveType;
import View.GameBasicFrame;
import View.KeyBoardEvent;
import View.MainPanel;

@SuppressWarnings("serial")
public class SingleFrame extends GameBasicFrame implements MoveType, CellSize {

	static private SingleFrame singleviewcopy = null;

	public SingleFrame() {
		super(UserTetrisControlManager.createTetrisControlManager());
		int cellwidth = width * 10;
		int cellheight = height * 20;

		setSize(cellwidth + 225, cellheight + 43);

		MainPanel mainpanel = new MainPanel(manager);
		
		add(mainpanel);
		
		manager.setPanel(mainpanel);

		manager.setTime(new Timer(speed, TicAction.ticActionCreate(manager)));
		addKeyListener(new KeyBoardEvent(manager));
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
