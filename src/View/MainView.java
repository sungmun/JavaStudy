package View;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Control.TetrisControlManager;
import Control.Tic;
import Model.MoveType;

@SuppressWarnings("serial")
public class MainView extends JFrame implements MoveType {

	TetrisMapPanel mappanel;
	TetrinoBlockPanel nowmapblockpanel;
	TetrisControlManager manager = TetrisControlManager.createTetrisControlManager();

	double speed = 0.1;

	static private Tic time;
	static private MainView mainviewcopy;

	private MainView(int width, int height) {
		super("Tetris");
		int cellwidth = width * 10;
		int cellheight = height * 20;
		
		setSize(cellwidth + 300, cellheight + 50);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo( null );
		
		JPanel mainpanel = new JPanel();
		mappanel = new TetrisMapPanel(width, height);
		nowmapblockpanel = TetrinoBlockPanel.createTetrinoBlockPanel(width, height);

		mappanel.setLayout(null);
		nowmapblockpanel.setLayout(null);
		mainpanel.setLayout(null);

		mappanel.setBounds(6, 6, cellwidth, cellheight);
		nowmapblockpanel.setBounds(1, 1, cellwidth - 2, cellheight - 3);
		mainpanel.setBounds(20, 20, cellwidth + 260, cellheight + 10);

		nowmapblockpanel.setBorder(new TitledBorder(new LineBorder(Color.RED)));

		mainpanel.setOpaque(false);
		nowmapblockpanel.setOpaque(false);
		mappanel.setOpaque(false);

		getContentPane().setBackground(Color.BLACK);

		mappanel.add(nowmapblockpanel);
		mainpanel.add(mappanel);
		add(mainpanel);

		time = new Tic((int) speed, manager);

		blockMoveRePaint();

		addKeyListener(new KeyBoardEvent());

		setVisible(true);
	}

	public static MainView createMainView(int width, int height) {
		if (mainviewcopy == null) {
			mainviewcopy = new MainView(width, height);
		}
		return mainviewcopy;
	}

	public static MainView getMainviewcopy() {
		return mainviewcopy;
	}

	public static Timer getTime() {
		return time;
	}

	public void blockMoveRePaint() {
		nowmapblockpanel.blockViewCheck();
		invalidate();
		repaint();
	}

	public TetrisMapPanel getTetrisMapPanel() {
		return mappanel;
	}

	public TetrinoBlockPanel getTetrinoBlockPanel() {
		return nowmapblockpanel;
	}

	public void speedUp() {
		speed += 0.1;
	}
}
