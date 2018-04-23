package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.border.LineBorder;

import Control.ImagePrint;
import Serversynchronization.TotalJsonObject;

public class NextBlockPanel extends SendDataPanel {
	/**
	 * 이미지를 ImagePrint클래스를 통해서, 받은 이미지를 출력을 해준다
	 * 이때 전달은 SendDataPanel에서 구현한 onEvent를 통하여 받는다
	 * 이미지는 완전한 JSON형식을 띄기는 힘들어서, TotalJSON을 객체로 전달을 받는다
	 */
	private static final long serialVersionUID = -1072036427180140563L;
	Image graphics;

	public NextBlockPanel() {
		setPreferredSize(new Dimension(ImagePrint.WIDTH * 5, ImagePrint.HEIGHT * 5));
		setBorder(new LineBorder(Color.WHITE, 2));
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
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
