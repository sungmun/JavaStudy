package Control;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import Model.BlockType;
import Model.CreateBlock;
import Model.TetrinoType;
import ValueObject.Space;

public class ImagePrint implements EventListener {

	public static final int WIDTH = 36;
	public static final int HEIGHT = 36;

	public static HashMap<TetrinoType, BufferedImage> tetrinoImg = new HashMap<>();

	BufferedImage background = null;

	public ImagePrint() {
		MVC_Connect.Controller.addListener(this);
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

	public BufferedImage tetrinoBlockPaint(Space[][] realtimeMap) {
		if (realtimeMap == null) {
			return null;
		}

		BufferedImage buffer = new BufferedImage(WIDTH * 10, HEIGHT * 20 - 1, BufferedImage.TYPE_INT_ARGB);
		Graphics g = buffer.createGraphics();
		g.drawImage(tetrinoMapBackgroundPaint(), 0, 0, null);

		int indexY = 0;
		for (Space[] spcs : realtimeMap) {
			int indexX = 0;
			for (Space spc : spcs) {
				if (spc != null && (spc.getIsblock() == BlockType.FLOW || spc.getIsblock() == BlockType.FIXED)) {
					g.setColor(TetrisBlockColor.getColor(spc.getType()));
					g.fill3DRect(indexX * WIDTH, (indexY - 3) * HEIGHT, WIDTH, HEIGHT, true);
				}
				indexX++;
			}
			indexY++;
		}
		
		return buffer;
	}

	public BufferedImage nextBlockPaint(TetrinoType nextBlock) {
		BufferedImage buffer = tetrinoImg.get(nextBlock);

		if (buffer == null) {
			buffer = blockPaint(new CreateBlock().tetrinoChoiceCreate(nextBlock).getArea());
			tetrinoImg.put(nextBlock, buffer);
		}

		return buffer;

	}

	private BufferedImage blockPaint(Space[][] spaces) {
		BufferedImage buffer = new BufferedImage(WIDTH * 4, HEIGHT * 4 - 1, BufferedImage.TYPE_INT_ARGB);
		Graphics g = buffer.createGraphics();
		int y = 0;
		for (Space[] spcs : spaces) {
			int x = -1;
			for (Space space : spcs) {
				x++;
				if (x == 0 || space == null)
					continue;

				if (space.getIsblock() == BlockType.FLOW) {
					g.setColor(TetrisBlockColor.getColor(space.getType()));
					g.fill3DRect(x * WIDTH - WIDTH, y * HEIGHT, WIDTH, HEIGHT, true);
				}

			}
			y++;
		}
		return buffer;
	}

	public BufferedImage saveBlockPaint(TetrinoType saveBlock) {

		BufferedImage buffer = tetrinoImg.get(saveBlock);

		if (buffer == null) {
			buffer = blockPaint(new CreateBlock().tetrinoChoiceCreate(saveBlock).getArea());
			tetrinoImg.put(saveBlock, buffer);
		}
		return buffer;
	}

	@Override
	public void onEvent(CallBackEvent event) {
		event.callBackEvent(this);
	}

}
