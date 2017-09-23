package View.Single;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.Timer;

import Control.TicAction;
import Control.UserTetrisControlManager;
import Model.CellSize;
import Model.MoveType;
import View.KeyBoardEvent;
import View.MainPanel;

@SuppressWarnings("serial")
public class SingleFrame extends JFrame implements MoveType, CellSize {

	private UserTetrisControlManager manager = UserTetrisControlManager.createTetrisControlManager();

	int speed = 500;

	static private Timer time;
	static private SingleFrame singleviewcopy = null;
	MainPanel mainpanel;

	public SingleFrame() {
		super("Tetris");
		int cellwidth = width * 10;
		int cellheight = height * 20;

		setSize(cellwidth + 225, cellheight + 43);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		mainpanel = new MainPanel(manager);

		getContentPane().setBackground(Color.BLACK);

		add(mainpanel);

		time = new Timer(speed, TicAction.ticActionCreate(manager));

		blockMoveRePaint();

		addKeyListener(new KeyBoardEvent());

		time.start();
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

	public static Timer getTime() {
		return time;
	}

	public void blockMoveRePaint() {
		// nowmapblockpanel.blockViewCheck();
		mainpanel.repaint();
	}

	public void speedUp() {
		speed += 0.1;
	}
}
