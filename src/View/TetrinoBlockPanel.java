package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import Control.Point;
import Control.TetrisControlManager;
import Model.Block;
import Model.Map;
import Model.Space;

public class TetrinoBlockPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static TetrinoBlockPanel tetrinoblockpanel = null;
	TetrisControlManager manager = TetrisControlManager.createTetrisControlManager();
	int width, height;
	BufferedImage buffer = null;
	public static BlockView[][] squareList = new BlockView[Map.getMapHeight() - 2][Map.getMapWidth()];
	Color[] tetrino_type= {
			new Color(255, 0, 0, 0),
			new Color(255, 255, 0),
			new Color(135, 206, 235),
			new Color(0, 128, 0),
			new Color(255, 0, 0),
			new Color(248, 155, 0),
			new Color(0, 153, 255),
			new Color(102, 0, 153),
		 };
	
	private TetrinoBlockPanel(int width, int height) {
		this.width = width;
		this.height = height;
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

	/**
	 * 
	 *//*
	public void blockViewCheck() {
		for (int y = 2; y < Map.getMapHeight(); y++) {
			for (int x = 0; x < Map.getMapWidth(); x++) {
				Space spc = manager.getRealTimeMap()[y][x];
				Color col = new Color(255, 0, 0, 0);
				if (y < 2 || spc.getIsblock() == Space.ETC) {
					break;
				}
				if (spc.getIsblock() != Space.SPACE) {
					col = BlockColor.SetBlockColor(((Block) spc).getType());
				}
				squareList[y-2][x].BlockChange(col);
			}
		}
	}*/

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
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
		}/*
		for (BlockView[] blockViews : squareList) {
			for (BlockView bV : blockViews) {
				int width = bV.getDpWidth();
				int height = bV.getDpHeight();
				int x = (bV.pos.getX()) * width;
				int y = (bV.pos.getY()-1) * height;
				g.setColor(bV.getCol());
				g.fill3DRect(x, y, width, height, true);
			}
		}*/
	}
}
