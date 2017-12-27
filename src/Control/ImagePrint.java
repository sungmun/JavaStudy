package Control;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Model.BlockType;
import Model.CreateBlock;
import Model.TetrinoType;
import ValueObject.Space;
import View.NextBlockPanel;
import View.SaveBlockPanel;
import View.TetrinoBlockPanel;
import View.TetrisBlockColor;

public class ImagePrint implements BlockType, EventListener {

	public static final int WIDTH = 36;
	public static final int HEIGHT = 36;

	public static HashMap<TetrinoType, BufferedImage> tetrinoImg = new HashMap<>();

	BufferedImage background = null;

	public ImagePrint() {
		MVC_Connect.ModelToControl.addListener(this);
		MVC_Connect.ViewToControl.addListener(this);
	}

	public BufferedImage backgroundImage(int x, int y) {
		BufferedImage buffer = new BufferedImage(WIDTH * x, HEIGHT * y - 1, BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics = (Graphics2D) buffer.getGraphics();

		float[] bash = { 5, 5, 5, 5 };
		graphics.setStroke(new BasicStroke(1, 0, BasicStroke.JOIN_MITER, 1.0f, bash, 0));
		for (int i = 1; i < x + 1; i++) {
			int k = WIDTH * i;
			graphics.draw(new Line2D.Double(k, 0, k, HEIGHT * y - 1));
		}
		for (int j = 1; j < y + 2; j++) {
			int k = HEIGHT * j;
			graphics.draw(new Line2D.Double(0, k, WIDTH * x - 1, k));
		}

		return buffer;
	}

	public BufferedImage tetrinoMapBackgroundPaint() {
		if (background == null) {
			background = backgroundImage(10, 20);
		}
		return background;
	}

	private void tetrinoBlockPaint(Object object) {

		Space[][] realtimeMap=(Space[][])object;
		
		BufferedImage buffer = new BufferedImage(WIDTH * 10, HEIGHT * 20 - 1, BufferedImage.TYPE_INT_ARGB);
		Graphics g = buffer.createGraphics();
		g.drawImage(tetrinoMapBackgroundPaint(), 0, 0, null);

		int indexY = 0;
		for (Space[] spcs : realtimeMap) {
			int indexX = 0;
			for (Space spc : spcs) {
				try {
					if (spc.getIsblock() == FLOW || spc.getIsblock() == FIXED) {
						g.setColor(TetrisBlockColor.getColor(spc.getType()));
						g.fill3DRect(indexX * WIDTH, (indexY - 3) * HEIGHT, WIDTH, HEIGHT, true);
					}
				} catch (NullPointerException e) {
				}
				indexX++;
			}
			indexY++;
		}
		
		JSONObject imageMessage=new JSONObject();
		imageMessage.put("method", "paintComponent");
		imageMessage.put("Image", buffer);
		MVC_Connect.ControlToView.callEvent(TetrinoBlockPanel.class.getClass(), imageMessage);
	}

	private void nextBlockPaint(Object object) {
		
		TetrinoType nextBlock=(TetrinoType)object;
		
		BufferedImage buffer = tetrinoImg.get(nextBlock);
		
		if (buffer == null) {
			buffer = blockPaint(new CreateBlock().tetrinoChoiceCreate(nextBlock).getArea());
			tetrinoImg.put(nextBlock, buffer);
		}
		
		JSONObject imageMessage=new JSONObject();
		imageMessage.put("method", "paintComponent");
		imageMessage.put("Image", buffer);
		MVC_Connect.ControlToView.callEvent(NextBlockPanel.class.getClass(), imageMessage);
		
	}

	private BufferedImage blockPaint(Space[][] spaces) {
		BufferedImage buffer = new BufferedImage(WIDTH * 4, HEIGHT * 4 - 1, BufferedImage.TYPE_INT_ARGB);
		Graphics g = buffer.createGraphics();
		int y = 0;
		for (Space[] spcs : spaces) {
			int x = -1;
			for (Space space : spcs) {
				x++;
				if (x == 0)
					continue;

				try {
					if (space.getIsblock() == BlockType.FLOW) {
						g.setColor(TetrisBlockColor.getColor(space.getType()));
						g.fill3DRect(x * WIDTH - WIDTH, y * HEIGHT, WIDTH, HEIGHT, true);
					}
				} catch (NullPointerException e) {
				}

			}
			y++;
		}
		return buffer;
	}

	private void saveBlockPaint(Object object) {

		TetrinoType saveBlock=(TetrinoType)object;
		
		BufferedImage buffer = tetrinoImg.get(saveBlock);
		
		if (buffer == null) {
			buffer = blockPaint(new CreateBlock().tetrinoChoiceCreate(saveBlock).getArea());
			tetrinoImg.put(saveBlock, buffer);
		}
		
		JSONObject imageMessage=new JSONObject();
		imageMessage.put("method", "paintComponent");
		imageMessage.put("Image", buffer);
		MVC_Connect.ControlToView.callEvent(SaveBlockPanel.class.getClass(), imageMessage);
		
	}

	@Override
	public void onEvent(String event) {
		try {
			JSONObject object = (JSONObject) new JSONParser().parse(event);
			if (object.get("method").toString() != null) {
				methodCatch(object);
			} else {
				System.out.println("ImagePrint.onEvent()");
				System.err.println(object.toJSONString());
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void methodCatch(JSONObject event) {
		switch (event.get("method").toString()) {
		case "saveBlockPaint":
			saveBlockPaint(event.get("TetrinoType"));
		case "nextBlockPaint":
			nextBlockPaint(event.get("TetrinoType"));
		case "tetrinoBlockPaint":
			tetrinoBlockPaint(event.get("Space[][]"));
		}
	}

}
