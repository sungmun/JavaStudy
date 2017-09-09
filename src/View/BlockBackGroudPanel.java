package View;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class BlockBackGroudPanel extends JPanel {
	BufferedImage buffer = null;
	Graphics2D graphics;

	private static BlockBackGroudPanel next_block_back_ground = null;
	private static BlockBackGroudPanel save_block_back_ground = null;

	int cellx, celly;

	private BlockBackGroudPanel(int width, int height, JPanel panel) {

		cellx = width;
		celly = height;
		setOpaque(false);

		setPreferredSize(new Dimension(width * 5, height * 5));
		setBorder(new LineBorder(Color.WHITE, 2));
		add(panel);
	}

	public static BlockBackGroudPanel createNextBlockBackGroudPanel(int width, int height, JPanel panel) {
		if (next_block_back_ground == null) {
			next_block_back_ground = new BlockBackGroudPanel(width, height, panel);
		}
		return next_block_back_ground;
	}

	public static BlockBackGroudPanel createSaveBlockBackGroudPanel(int width, int height, JPanel panel) {
		if (save_block_back_ground == null) {
			save_block_back_ground = new BlockBackGroudPanel(width, height, panel);
		}
		return save_block_back_ground;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (buffer == null) {
			buffer = new BufferedImage(cellx * 5, celly * 5, BufferedImage.TYPE_INT_ARGB);
			graphics = (Graphics2D) buffer.getGraphics();

			float[] bash = { 5, 5, 5, 5 };
			graphics.setStroke(new BasicStroke(1, 0, BasicStroke.JOIN_MITER, 1.0f, bash, 0));
			for (int i = 1; i < 6; i++) {
				int k = cellx * i;
				graphics.draw(new Line2D.Double(k, 0, k, celly * 20 - 1));
			}
			for (int j = 1; j < 6; j++) {
				int k = celly * j;
				graphics.draw(new Line2D.Double(0, k, cellx * 10 - 1, k));
			}
		}
		g.drawImage(buffer, 0, 0, this);
	}
}
