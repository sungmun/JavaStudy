package View;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class GameViewPanel extends JPanel{
	TetrisMapPanel mappanel;
	
	int width,height;
	public GameViewPanel(int width, int height) {
		mappanel=new TetrisMapPanel(width, height);
		this.width=width;
		this.height=height;
		setOpaque(false);
		setBorder(new LineBorder(Color.WHITE,2));
		add(mappanel);
	}

}
