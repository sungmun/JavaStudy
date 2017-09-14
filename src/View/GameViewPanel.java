package View;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameViewPanel extends JPanel{
	TetrisMapPanel mappanel;
	
	int width,height;
	public GameViewPanel(int width, int height) {
		mappanel=new TetrisMapPanel(width, height);
		this.width=width;
		this.height=height;
		setOpaque(false);
		
		add(mappanel);
	}

}
