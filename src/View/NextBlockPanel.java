package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Control.EventListener;
import Control.ImagePrint;

@SuppressWarnings("serial")
public class NextBlockPanel extends JPanel implements EventListener {
	Image graphics;

	public NextBlockPanel() {

		setOpaque(false);
		setPreferredSize(new Dimension(ImagePrint.width * 5, ImagePrint.height * 5));
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
	public void onEvent(String event) {
		try {
			JSONObject object = (JSONObject) new JSONParser().parse(event);
			if (object.get("method").toString() == "paintComponent") {
				setImage(object.get("Image"));
			} else {
				System.out.println("NextBlockPanel.onEvent()");
				System.err.println(object.toJSONString());
			}
		} catch (ParseException e) {
		}
	}
}
