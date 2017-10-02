package View.Multe;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JPanel;
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

		setLayout(new GridLayout(1, 2));
		
		JPanel user = new JPanel(new BorderLayout(), true);
		JPanel opponent = new JPanel(new BorderLayout(), true);
		
		user.setOpaque(false);
		opponent.setOpaque(false);
		
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

		this.pack();
		this.setLocationRelativeTo(null);
		//pack로 화면의 크기롤 고정 시칸다음 화면의 위치를 정해 주어야 한다

		manager.setTime(new Timer(speed, TicAction.ticActionCreate(manager)));

		addKeyListener(new KeyBoardEvent(manager));
	}

	@Override
	public void update(Graphics g) {
		paintComponents(g);
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
