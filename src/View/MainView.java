package View;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainView {
	MainViewFrame jf;
	private JPanel contentPane;
	public MainView(int width,int height) {
		// TODO Auto-generated constructor stub
		jf=new MainViewFrame(width, height);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		jf.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		TetrisMapPanel panel = new TetrisMapPanel(width,height);
		
		panel.setBounds(5, 5, width*10,height*20-1);
		contentPane.add(panel);
		
		TetrisMapPanel nowmappanel=new TetrisMapPanel(width, height);
		panel.add(nowmappanel);
		
		jf.getContentPane().setBackground(Color.BLACK);
	}
}
