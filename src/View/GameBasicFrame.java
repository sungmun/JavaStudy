package View;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class GameBasicFrame extends JFrame {
	protected int speed = 500;
	public static Timer time;
	public GameBasicFrame( ) {
		super("Tetris");

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		this.getContentPane().setBackground(Color.BLACK);
		this.setLocationRelativeTo(null);
	}
	
}
