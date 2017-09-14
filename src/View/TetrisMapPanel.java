package View;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

class TetrisMapPanel extends JPanel{
	BufferedImage buffer=null;
	Graphics2D graphics;
	private TetrinoBlockPanel nowmapblockpanel;
	
	private static final long serialVersionUID = 1L;
	int cellx,celly;
	public TetrisMapPanel(int width,int height) {
		super();
		nowmapblockpanel = TetrinoBlockPanel.createTetrinoBlockPanel(width, height);
		cellx=width;
		celly=height;
		setLayout(null);
		setLocation(0, 0);
		setPreferredSize(new Dimension(width*10, height*20));
		setOpaque(false);
		setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 20));
		
		add(nowmapblockpanel);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
	}
}
