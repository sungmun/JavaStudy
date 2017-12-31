package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.json.simple.JSONObject;

import Control.EventListener;
import Control.ImagePrint;
import Control.MVC_Connect;

@SuppressWarnings("serial")
public class TetrinoBlockPanel extends JPanel implements EventListener {

	Image graphics;

	public TetrinoBlockPanel() {
		super(true);
		setLayout(null);
		setLocation(0, 0);
		setPreferredSize(new Dimension(ImagePrint.WIDTH * 10, ImagePrint.HEIGHT * 20));
		setOpaque(false);
		setBorder(new LineBorder(Color.WHITE, 2));

		MVC_Connect.ControlToView.addListener(this);
	}

	public void setImage(Object g) {
		graphics = (BufferedImage) g;
		this.repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(graphics, 0, 0, this);
	}

	@Override
	public void onEvent(JSONObject event) {
		System.out.println("TetrinoBlockPanel.onEvent()");
		System.out.println(event.toJSONString());
		JSONObject object = event;
		if (object.get("method") != null) {
			methodCatch(object);
		}

	}

	@Override
	public void methodCatch(JSONObject event) {
		switch ((String) event.get("method")) {
		case "paintComponent":
			setImage(event.get(BufferedImage.class));
			break;

		default:
			break;
		}
	}
}
