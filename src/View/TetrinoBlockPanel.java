package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.border.LineBorder;

import Control.CallBackEvent;
import Control.ImagePrint;
import Serversynchronization.TotalJsonObject;

public class TetrinoBlockPanel extends SendDataPanel {
	/**
	 * 이 Panel은 Tetris의 진행과정을 그대로 보여주는 Panel로
	 * 블럭의 내려가는모습이나, 유동블럭이 고정블럭이 되어 고정되있는 모습 등을 보여준다
	 * 데이터는 SendDataPanel Class를 통하여 전달을 받는다.
	 */
	private static final long serialVersionUID = -5477603803474174214L;
	Image graphics;
	public TetrinoBlockPanel() {
		setLayout(null);
		setLocation(0, 0);
		setPreferredSize(new Dimension(ImagePrint.WIDTH * 10, ImagePrint.HEIGHT * 20));
		setBorder(new LineBorder(Color.WHITE, 2));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(graphics, 0, 0, this);
	}

	@Override
	public void setData(Object obj) {
		TotalJsonObject jsonObj = (TotalJsonObject) obj;

		graphics = (BufferedImage) jsonObj.get(BufferedImage.class.getName());
		repaint();
	}

}
