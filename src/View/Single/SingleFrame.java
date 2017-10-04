package View.Single;


import javax.swing.Timer;

import Control.TicAction;
import Model.CellSize;
import Model.MoveType;
import Model.UserTetrisControlManager;
import View.GameBasicFrame;
import View.KeyBoardEvent;
import View.MainPanel;

@SuppressWarnings("serial")
public class SingleFrame extends GameBasicFrame implements MoveType, CellSize {

	static private SingleFrame singleviewcopy = null;

	public SingleFrame() {
		super(UserTetrisControlManager.createTetrisControlManager());
		
		MainPanel mainpanel = new MainPanel(manager);
		
		
		manager.setPanel(mainpanel);
		
		this.add(mainpanel);
		
		this.pack();
		this.setLocationRelativeTo(null);
		
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
