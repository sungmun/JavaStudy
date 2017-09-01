package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import Control.Point;
import Control.TetrisControlManager;
import Model.Block;
import Model.Space;

public class TetrinoBlockPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private static TetrinoBlockPanel tetrinoblockpanel=null;
	TetrisControlManager manager=TetrisControlManager.createTetrisControlManager();
	int width,height;
	BufferedImage buffer=null;
	private TetrinoBlockPanel(int width,int height) {
		// TODO Auto-generated constructor stub
		this.width=width;
		this.height=height;
		
	}
	
	public static TetrinoBlockPanel createTetrinoBlockPanel(){
		if(tetrinoblockpanel==null){
			tetrinoblockpanel= new TetrinoBlockPanel(36,36);
		}
		return tetrinoblockpanel;
	}
	public static TetrinoBlockPanel createTetrinoBlockPanel(int width,int height){
		if(tetrinoblockpanel==null){
			tetrinoblockpanel= new TetrinoBlockPanel(width,height);
		}
		tetrinoblockpanel.addKeyListener(new KeyBoardEvent());
		return tetrinoblockpanel;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(buffer==null){
			buffer=new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			Graphics graphics=(Graphics2D)buffer.getGraphics();
			for(int y=3;y<TetrisControlManager.getHeight();y++){
				for(int x=0;x<TetrisControlManager.getWidth();x++){
					Space spc=manager.getRealTimeMap()[y][x];
					if(spc.toString()=="Block"){
						Block bc=(Block)spc;
						Color col=BlockView.SetBlockColor(bc.getType());
						graphics.setColor(Color.white);
						graphics.drawRect(x*width-3,y*height-3,width+1,height+1);
						graphics.setColor(col);
						graphics.fill3DRect(x*width-4,y*height-4,width,height, true);
					}
				}
			}
		}else{
			buffer=new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			Graphics graphics=(Graphics2D)buffer.getGraphics();
			Point pos=manager.getNowTetrino().getFlowTetrino();
			Rectangle rec=manager.getNowTetrino().getActivespace();
			int x=pos.getX()+(int)rec.getX()-3;
			int y=pos.getY()+(int)rec.getY()-1;
			//x의 값이 범위를 초과한다
			g.clearRect(x*this.width, y*this.height, (int)rec.getWidth()*width, (int)rec.getHeight()*height);
			try{
				for(;y<(int)rec.getHeight()+pos.getY();y++){
					for(;x<(int)rec.getWidth()+pos.getX();x++){
						
							Space spc=manager.getRealTimeMap()[y][x];
							if(spc.getIsblock()==Space.FLOW||spc.getIsblock()==Space.FIXED){
								Block bc=(Block)spc;
								Color col=BlockView.SetBlockColor(bc.getType());
								graphics.setColor(Color.white);
								graphics.drawRect(x*width-3,y*height-3,width+1,height+1);
								graphics.setColor(col);
								graphics.fill3DRect(x*width-4,y*height-4,width,height, true);
							}
					}
				}
			}catch (ArrayIndexOutOfBoundsException e) {
				/*
				System.out.println("x: "+x+"\t (int)rec.getWidth():  "+(int)rec.getWidth()+"\t pos.getX():  "+pos.getX());
				System.out.println("y: "+y+"\t (int)rec.getHeight(): "+(int)rec.getHeight()+"\t pos.getY(): "+pos.getY());
				System.out.println("max x: "+(int)rec.getWidth()+pos.getX());
				System.out.println("max y: "+(int)rec.getHeight()+pos.getY());
				for (Space spcs[] : manager.getRealTimeMap()) {
					for (Space spc : spcs) {
						System.out.print(spc.getIsblock());
					}
					System.out.println();
				}
				System.out.println();
				System.out.println();
			*/	
			}
		}
		g.drawImage(buffer, 0, 0, this);
	}
}
