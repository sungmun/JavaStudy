package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import Control.TetrisControlManager;
import Model.Space;

@SuppressWarnings("serial")
public class TetrinoBlockPanel extends JPanel {

	private static TetrinoBlockPanel tetrinoblockpanel = null;
	TetrisControlManager manager = TetrisControlManager.createTetrisControlManager();
	int width, height;
	BufferedImage buffer = null;
	static Color[] tetrino_type= {
			new Color(255, 0, 0, 0), 	//���� ����Ʈ ��
			new Color(135, 206, 235),	//�ϴû�	- I
			new Color(0, 153, 255),		//�Ķ�	- J
			new Color(248, 155, 0),		//��Ȳ	- L
			new Color(255, 255, 0),		//�����	- O
			new Color(0, 128, 0),		//�ʷϻ�	- S	
			new Color(102, 0, 153),		//����	- T
			new Color(255, 0, 0),		//����	- Z
		 };
	
	private TetrinoBlockPanel(int width, int height) {
		this.width = width;
		this.height = height;
		setLayout(null);
		setLocation(0, 0);
		setSize(width*10, height*20);
		setOpaque(false);
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
