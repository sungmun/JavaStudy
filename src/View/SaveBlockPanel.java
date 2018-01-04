
package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.border.LineBorder;

import org.json.simple.JSONObject;

import Control.ImagePrint;

@SuppressWarnings("serial")
public class SaveBlockPanel extends SendDataPanel {
	Image graphics;
	public SaveBlockPanel() {
		setOpaque(false);
		setLocation(0, 0);
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
	void setData(Object obj) {
		// TODO Auto-generated method stub
		JSONObject event=(JSONObject) obj;
		graphics=(BufferedImage)event.get(BufferedImage.class);
		repaint();
	}

}
