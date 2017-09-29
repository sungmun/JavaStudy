package View;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.Timer;

import Control.TetrisControlManager;

@SuppressWarnings("serial")
public class GameBasicFrame extends JFrame {
	protected TetrisControlManager manager;
	protected int speed = 500;
	protected Timer time;

	public GameBasicFrame(TetrisControlManager manager) {
		super("Tetris");
		this.manager = manager;
		manager.setFrame(this);
		manager.setTime(time);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		getContentPane().setBackground(Color.BLACK);
	}
	
}
