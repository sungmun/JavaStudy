package View;

import java.awt.Color;

import javax.swing.JFrame;

import Control.TetrisControlManager;

@SuppressWarnings("serial")
public class GameBasicFrame extends JFrame {
	protected TetrisControlManager manager;
	protected int speed = 500;

	public GameBasicFrame(TetrisControlManager manager) {
		super("Tetris");
		this.manager = manager;

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		this.getContentPane().setBackground(Color.BLACK);
		this.setLocationRelativeTo(null);
	}
	
}
