package View;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import Control.TetrisControlManager;
import Model.Block;
import Model.Space;

public class TetrinoBlockPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private static TetrinoBlockPanel tetrinoblockpanel=null;
	TetrisControlManager manager=TetrisControlManager.createTetrisControlManager();
	BlockView[][] viewmap=new BlockView[TetrisControlManager.getHeight()][TetrisControlManager.getWidth()];
	int width,height;
	private TetrinoBlockPanel(int width,int height) {
		// TODO Auto-generated constructor stub
		this.width=width;
		this.height=height;
		setBounds(0, 0, width*10,height*20-1);
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

		for(int y=0;y<TetrisControlManager.getHeight();y++){
			for(int x=0;x<TetrisControlManager.getWidth();x++){
				Space spc=manager.getRealTimeMap()[y][x];
				if(spc.toString()=="Block"){
					Block bc=(Block)spc;
					Color col=BlockView.SetBlockColor(bc.getType());
					g.setColor(col);
					g.fillRect(x*width-4,y*width-4,width,height);
				}
			}
		}
		synchronized (this) {
			notifyAll();
		}
		
	}
	
}
