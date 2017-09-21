package View.Multe;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.Timer;

import Control.OpponentTetrisControlManager;
import Control.TicAction;
import Control.UserTetrisControlManager;
import Model.CellSize;
import Model.MoveType;
import View.KeyBoardEvent;
import View.MainPanel;

@SuppressWarnings("serial")
public class MulteFrame extends JFrame implements MoveType,CellSize{

	private UserTetrisControlManager usermanager = UserTetrisControlManager.getTetrisControlManager();
	private OpponentTetrisControlManager oppmanager = OpponentTetrisControlManager.getTetrisControlManager();

	int speed = 500;

	static private Timer time;
	static private MulteFrame multeviewcopy=null;

	private MulteFrame() {
		super("Tetris");
		int cellwidth = width * 20;
		int cellheight = height * 20;

		setSize(cellwidth + 450, cellheight + 43);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		MainPanel userpanel = new MainPanel(usermanager);
		MainPanel opponentpanel = new MainPanel(oppmanager);

		getContentPane().setBackground(Color.BLACK);

		add(userpanel);
		add(opponentpanel);

		time = new Timer(speed, TicAction.ticActionCreate(usermanager));

		
		blockMoveRePaint();

		addKeyListener(new KeyBoardEvent());

		setVisible(true);
	}

	public static MulteFrame createMulteFrame() {
		if (multeviewcopy == null) {
			multeviewcopy = new MulteFrame();
		}
		return multeviewcopy;
	}

	public static MulteFrame getMulteFrame() {
		return multeviewcopy;
	}

	public static Timer getTime() {
		return time;
	}

	public void blockMoveRePaint() {
		
		repaint();
	}

	public void speedUp() {
		speed += 0.1;
	}
}
