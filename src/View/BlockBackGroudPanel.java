package View;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BlockBackGroudPanel extends JPanel {
	BufferedImage buffer = null;
	Graphics2D graphics;

	private static BlockBackGroudPanel block_back_ground = null;

	int cellx, celly;

	private BlockBackGroudPanel(int width, int height) {
		super();
		cellx = width;
		celly = height;
		setLayout(null);
		setOpaque(false);
	}

	public static BlockBackGroudPanel createBlockBackGroudPanel(int width, int height) {
		if (block_back_ground == null) {
			block_back_ground = new BlockBackGroudPanel(width, height);
		}
		return block_back_ground;
	}

	public static BlockBackGroudPanel getBlock_back_ground() {
		return block_back_ground;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (buffer == null) {
			buffer = new BufferedImage(cellx * 4, celly * 4, BufferedImage.TYPE_INT_ARGB);
			graphics = (Graphics2D) buffer.getGraphics();
			graphics.setColor(Color.WHITE);
			graphics.setStroke(new BasicStroke(3, 0, BasicStroke.JOIN_MITER));
			graphics.draw(new Rectangle2D.Double(0, 0, cellx * 4 - 1, celly * 4 - 1));

			float[] bash = { 5, 5, 5, 5 };
			graphics.setStroke(new BasicStroke(1, 0, BasicStroke.JOIN_MITER, 1.0f, bash, 0));
			for (int i = 1; i < 5; i++) {
				int k = cellx * i;
				graphics.draw(new Line2D.Double(k, 0, k, celly * 20 - 1));
			}
			for (int j = 1; j < 5; j++) {
				int k = celly * j;
				graphics.draw(new Line2D.Double(0, k, cellx * 10 - 1, k));
			}
		}
		g.drawImage(buffer, 0, 0, this);
	}
}
