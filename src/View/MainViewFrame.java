package View;

import java.awt.Color;

import javax.swing.JFrame;

public class MainViewFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public MainViewFrame(int width,int height) {
		super("Tetris");
		int cellwidth=width*10;
		int cellheight=height*20;
		setSize(cellwidth+300, cellheight+50);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.BLACK);
		
		setVisible(true);
		
	}
}
