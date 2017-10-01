package View.Multe;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;

import javax.swing.Timer;

import Control.OpponentControl;
import Control.OpponentTetrisControlManager;
import Control.TicAction;
import Control.UserControl;
import Control.UserTetrisControlManager;
import Model.CellSize;
import Model.MoveType;
import View.GameBasicFrame;
import View.KeyBoardEvent;
import View.MainPanel;

@SuppressWarnings("serial")
public class MultiFrame extends GameBasicFrame implements MoveType, CellSize {

	static private MultiFrame multeviewcopy = null;
	static private OpponentTetrisControlManager oppmanager = null;

	private MultiFrame() {
		super(UserTetrisControlManager.getTetrisControlManager());
		oppmanager = OpponentTetrisControlManager.getTetrisControlManager();
		int cellwidth = width * 20;
		int cellheight = height * 20;

		setSize(cellwidth + 450, cellheight + 43);
		setLayout(new GridLayout(1, 2));

		Panel user = new Panel(new BorderLayout());
		Panel opponent = new Panel(new BorderLayout());

		MainPanel userpanel = new MainPanel(manager);
		user.add(new Label(UserControl.getUserControl().getUser().getName()), BorderLayout.NORTH);
		user.add(userpanel, BorderLayout.CENTER);
		add(user);
		manager.setPanel(userpanel);

		MainPanel opponentpanel = new MainPanel(oppmanager);
		opponent.add(new Label(OpponentControl.getOpponentControl().getUser().getName()), BorderLayout.NORTH);
		opponent.add(opponentpanel, BorderLayout.CENTER);
		add(opponent);
		oppmanager.setPanel(opponentpanel);

		
		manager.setTime(new Timer(speed, TicAction.ticActionCreate(manager)));

		addKeyListener(new KeyBoardEvent(manager));
	}

	public static MultiFrame createMulteFrame() {
		if (multeviewcopy == null) {
			multeviewcopy = new MultiFrame();
		}
		return multeviewcopy;
	}

	public static MultiFrame getMulteFrame() {
		return multeviewcopy;
	}
}
