package View;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainView {
	MainViewFrame jf;
	TetrisMapPanel mappanel;
	TetrinoBlockPanel nowmappanel;
	
	public MainView(int width,int height) {
		// TODO Auto-generated constructor stub
		jf=new MainViewFrame(width, height);
		
		Container contentPane = jf.getContentPane();
		contentPane.setLayout(null);
		
		mappanel = new TetrisMapPanel(width,height);
		nowmappanel=TetrinoBlockPanel.createTetrinoBlockPanel(width, height);
		
		contentPane.add(mappanel);

		mappanel.add(nowmappanel);
		
		jf.getContentPane().setBackground(Color.BLACK);
	}
	public TetrisMapPanel getTetrisMapPanel(){
		return mappanel;
	}
	public TetrinoBlockPanel getTetrinoBlockPanel(){
		return nowmappanel;
	}
}
