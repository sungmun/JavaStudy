package View.Multe;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.Timer;

import Control.TicAction;
import Control.UserTetrisControlManager;
import Model.MoveType;
import View.KeyBoardEvent;
import View.MainPanel;

@SuppressWarnings("serial")
public class MulteFrame extends JFrame implements MoveType {

	private UserTetrisControlManager manager = UserTetrisControlManager.getTetrisControlManager();

	int speed = 500;

	static private Timer time;
	static private MulteFrame multeviewcopy=null;

	private MulteFrame(int width, int height) {
		super("Tetris");
		int cellwidth = width * 20;
		int cellheight = height * 20;

		setSize(cellwidth + 450, cellheight + 43);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		MainPanel userpanel = new MainPanel(width, height);
		MainPanel opponentpanel = new MainPanel(width, height);

		getContentPane().setBackground(Color.BLACK);

		add(userpanel);
		add(opponentpanel);

		time = new Timer(speed, TicAction.ticActionCreate(manager));

		
		blockMoveRePaint();

		addKeyListener(new KeyBoardEvent());

		setVisible(true);
	}

	public static MulteFrame createMulteFrame(int width, int height) {
		if (multeviewcopy == null) {
			multeviewcopy = new MulteFrame(width, height);
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
		// nowmapblockpanel.blockViewCheck();
		invalidate();
		repaint();
	}

	public void speedUp() {
		speed += 0.1;
	}
}
