package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.json.simple.JSONObject;

import Control.EventListener;
import Control.ImagePrint;
import Control.MVC_Connect;

@SuppressWarnings("serial")
public class NextBlockPanel extends JPanel implements EventListener {
	Image graphics;

	public NextBlockPanel() {
		MVC_Connect.ControlToView.addListener(this);
		setOpaque(false);
		setPreferredSize(new Dimension(ImagePrint.WIDTH * 5, ImagePrint.HEIGHT * 5));
		setBorder(new LineBorder(Color.WHITE, 2));
	}

	public void setImage(Object g) {
		graphics = (Image) g;
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(graphics, 0, 0, this);
	}

	@Override
	public void onEvent(JSONObject event) {
		System.out.println("NextBlockPanel.onEvent()");
		System.out.println(event.toJSONString());
		JSONObject object = event;
		if (object.get("method") != null) {
			methodCatch(object);
		} else {
			System.out.println("NextBlockPanel.onEvent()");
			System.err.println(object.toJSONString());
		}
	}

	@Override
	public void methodCatch(JSONObject event) {
		switch ((String) event.get("method")) {
		case "paintComponent":
			setImage(event.get("Image"));
			break;

		default:
			break;
		}
	}
}
