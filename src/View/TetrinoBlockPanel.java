package View;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Control.UserTetrisControlManager;
import Model.Space;


@SuppressWarnings("serial")
public class TetrinoBlockPanel extends JPanel {

	private static TetrinoBlockPanel tetrinoblockpanel = null;
	UserTetrisControlManager manager = UserTetrisControlManager.getTetrisControlManager();
	int width, height;
	BufferedImage buffer = null;
	Graphics2D graphics;
	static Color[] tetrino_type= {
			new Color(255, 0, 0, 0), 	//투명 디폴트 값
			new Color(135, 206, 235),	//하늘색	- I
			new Color(0, 153, 255),		//파랑	- J
			new Color(248, 155, 0),		//주황	- L
			new Color(255, 255, 0),		//노랑색	- O
			new Color(0, 128, 0),		//초록색	- S	
			new Color(102, 0, 153),		//보라	- T
			new Color(255, 0, 0),		//빨강	- Z
		 };
	
	private TetrinoBlockPanel(int width, int height) {
		this.width = width;
		this.height = height;
		setLayout(null);
		setLocation(0, 0);
		setSize(width*10, height*20);
		setOpaque(false);
		setBorder(new LineBorder(Color.WHITE,2)); 
	}

	public static TetrinoBlockPanel getTetrinoBlockPanel() {
		return tetrinoblockpanel;
	}

	public static TetrinoBlockPanel createTetrinoBlockPanel(int width, int height) {
		if (tetrinoblockpanel == null) {
			tetrinoblockpanel = new TetrinoBlockPanel(width, height);
		}
		tetrinoblockpanel.addKeyListener(new KeyBoardEvent());
		return tetrinoblockpanel;
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(buffer==null){
			buffer=new BufferedImage(width*10, height*20-1, BufferedImage.TYPE_INT_ARGB);
			graphics=(Graphics2D)buffer.getGraphics();
			
			float[] bash={5,5,5,5};
			graphics.setStroke(new BasicStroke(1,0,BasicStroke.JOIN_MITER,1.0f,bash,0));
			for(int i=1;i<11;i++){
				int k=width*i;
				graphics.draw(new Line2D.Double(k,0,k,height*20-1));	
			}
			for(int j=1;j<22;j++){
				int k=height*j;
				graphics.draw(new Line2D.Double(0,k,width*10-1,k));
			}
		}
		g.drawImage(buffer, 0, 0, this);
		
		int indexY=0;
		int indexX=0;
		for (Space[] spcs : manager.getRealTimeMap()) {
			indexX=0;
			for (Space spc : spcs) {
				g.setColor(tetrino_type[spc.getType()]);
				g.fill3DRect(indexX*width, (indexY-3)*height, width, height,true);
				indexX++;
			}
			indexY++;
		}
	}
}
