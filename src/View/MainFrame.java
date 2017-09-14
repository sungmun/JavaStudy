package View;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.Timer;

import Control.TetrisControlManager;
import Control.TicAction;
import Model.MoveType;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements MoveType {

	private TetrisControlManager manager = TetrisControlManager.createTetrisControlManager();

	int speed = 500;

	static private Timer time;
	static private MainFrame mainviewcopy;

	private MainFrame(int width, int height) {
		super("Tetris");
		int cellwidth = width * 10;
		int cellheight = height * 20;

		setSize(cellwidth + 225, cellheight + 43);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		MainPanel mainpanel = new MainPanel(width, height);

		getContentPane().setBackground(Color.BLACK);

		add(mainpanel);

		time = new Timer(speed, TicAction.ticActionCreate(manager));

		
		blockMoveRePaint();

		addKeyListener(new KeyBoardEvent());

		setVisible(true);
	}

	public static MainFrame createMainView(int width, int height) {
		if (mainviewcopy == null) {
			mainviewcopy = new MainFrame(width, height);
		}
		return mainviewcopy;
	}

	public static MainFrame getMainviewcopy() {
		return mainviewcopy;
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
