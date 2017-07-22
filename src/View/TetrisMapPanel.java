package View;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

class TetrisMapPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	int cellx,celly;
	public TetrisMapPanel(int width,int height) {
		super();
		cellx=width;
		celly=height;
		setBounds(5, 5, width*10,height*20-1);
	}
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2=(Graphics2D)g;
		g2.setColor(Color.WHITE);
		g2.setStroke(new BasicStroke(3,0,BasicStroke.JOIN_MITER));
		g2.draw(new Rectangle2D.Double(0,0,cellx*10-1,celly*20-1));
		
		float[] bash={5,5,5,5};
		g2.setStroke(new BasicStroke(1,0,BasicStroke.JOIN_MITER,1.0f,bash,0));
		for(int i=1;i<11;i++){
			int k=cellx*i;
			g2.draw(new Line2D.Double(k,0,k,celly*20-1));	
		}
		for(int j=1;j<22;j++){
			int k=celly*j;
			g2.draw(new Line2D.Double(0,k,cellx*10-1,k));
		}
	}
}
