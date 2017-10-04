package View;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Model.CellSize;
import Model.TetrisControlManager;
import Model.ValueObject.Space;

@SuppressWarnings("serial")
public class TetrinoBlockPanel extends JPanel implements CellSize,TetrisBlockColor {

	TetrisControlManager manager = null;
	BufferedImage buffer = null;
	Graphics2D graphics;

	public TetrinoBlockPanel(TetrisControlManager manager) {
		super(true);
		this.manager = manager;
		setLayout(null);
		setLocation(0, 0);
		setSize(width * 10, height * 20);
		setOpaque(false);
		setBorder(new LineBorder(Color.WHITE, 2));

	}

	@Override
	public void update(Graphics g) {
		paintComponent(g);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (buffer == null) {
			buffer = new BufferedImage(width * 10, height * 20 - 1, BufferedImage.TYPE_INT_ARGB);
			graphics = (Graphics2D) buffer.getGraphics();

			float[] bash = { 5, 5, 5, 5 };
			graphics.setStroke(new BasicStroke(1, 0, BasicStroke.JOIN_MITER, 1.0f, bash, 0));
			for (int i = 1; i < 11; i++) {
				int k = width * i;
				graphics.draw(new Line2D.Double(k, 0, k, height * 20 - 1));
			}
			for (int j = 1; j < 22; j++) {
				int k = height * j;
				graphics.draw(new Line2D.Double(0, k, width * 10 - 1, k));
			}
		}
		g.drawImage(buffer, 0, 0, this);

		int indexY = 0;
		int indexX = 0;
		for (Space[] spcs : manager.getRealTimeMap()) {
			indexX = 0;
			for (Space spc : spcs) {
				g.setColor(TETRINO_COLOR[spc.getType()]);
				g.fill3DRect(indexX * width, (indexY - 3) * height, width, height, true);
				indexX++;
			}
			indexY++;
		}
	}
}
