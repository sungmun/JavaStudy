package Control;

import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.JPanel;

import Model.BlockType;
import Model.CreateBlock;
import Model.TetrisManager;
import ValueObject.Space;
import View.CellSize;
import View.NextBlockPanel;
import View.SaveBlockPanel;
import View.TetrinoBlockPanel;
import View.TetrisBlockColor;

public class ImagePrint implements TetrisBlockColor, CellSize, BlockType {
	@SuppressWarnings("rawtypes")
	private HashMap<Class, Component> contmap = new HashMap<>();
	public static HashMap<Integer, BufferedImage> tetrinoimg = new HashMap<>();
	BufferedImage background = null;

	public ImagePrint(JPanel panel) {
		setContainer(panel);
	}

	public BufferedImage paint(int x, int y) {
		BufferedImage buffer = new BufferedImage(width * x, height * y - 1, BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics = (Graphics2D) buffer.getGraphics();

		float[] bash = { 5, 5, 5, 5 };
		graphics.setStroke(new BasicStroke(1, 0, BasicStroke.JOIN_MITER, 1.0f, bash, 0));
		for (int i = 1; i < x + 1; i++) {
			int k = width * i;
			graphics.draw(new Line2D.Double(k, 0, k, height * y - 1));
		}
		for (int j = 1; j < y + 2; j++) {
			int k = height * j;
			graphics.draw(new Line2D.Double(0, k, width * x - 1, k));
		}
		return buffer;
	}

	public void setContainer(Component con) {
		for (Component cont : ((Container) con).getComponents()) {
			getContmap().put(cont.getClass(), cont);
			setContainer(cont);
		}
	}

	public BufferedImage TetrinoBlockBackPaint(TetrisManager manager) {
		if (background == null) {
			background = paint(10, 20);
		}
		return background;
	}

	public void TetrinoBlockPaint(TetrisManager manager) {
		TetrinoBlockPanel panel = (TetrinoBlockPanel) getContmap().get(TetrinoBlockPanel.class);

		BufferedImage buffer = new BufferedImage(width * 10, height * 20 - 1, BufferedImage.TYPE_INT_ARGB);
		Graphics g = buffer.createGraphics();
		g.drawImage(TetrinoBlockBackPaint(manager), 0, 0, null);
		int indexY = 0;
		for (Space[] spcs : manager.getRealTimeMap()) {
			int indexX = 0;
			for (Space spc : spcs) {
				if (spc.getIsblock() == FLOW || spc.getIsblock() == FIXED) {
					g.setColor(TETRINO_COLOR[spc.getType()]);
					g.fill3DRect(indexX * width, (indexY - 3) * height, width, height, true);
				}
				indexX++;
			}
			indexY++;
		}
		panel.setImage(buffer);
		panel.repaint();
	}

	public void NextBlockPaint(TetrisManager manager) {
		NextBlockPanel panel = (NextBlockPanel) getContmap().get(NextBlockPanel.class);
		BufferedImage buffer = tetrinoimg.get(new Integer(manager.getNext_block()));
		if (buffer == null) {
			buffer = blockPaint(CreateBlock.tetrinoChoiceCreate(manager.getNext_block()).getArea());
			tetrinoimg.put(manager.getNext_block(), buffer);
		}
		panel.setImage(buffer);
		panel.repaint();

	}

	public BufferedImage blockPaint(Space[][] spaces) {
		BufferedImage buffer = new BufferedImage(width * 4, height * 4 - 1, BufferedImage.TYPE_INT_ARGB);
		Graphics g = buffer.createGraphics();
		int y = 0;
		for (Space[] spcs : spaces) {
			int x = -1;
			for (Space space : spcs) {
				x++;
				if (x == 0)
					continue;

				if (space.getIsblock() == BlockType.FLOW) {
					g.setColor(TETRINO_COLOR[space.getType()]);
					g.fill3DRect(x * width - width, y * height, width, height, true);
				}

			}
			y++;
		}
		return buffer;
	}

	public void SaveBlockPaint(TetrisManager manager) {
		SaveBlockPanel panel = (SaveBlockPanel) getContmap().get(SaveBlockPanel.class);
		BufferedImage buffer = tetrinoimg.get(new Integer(manager.getSave_block()));
		if (buffer == null) {
			buffer = blockPaint(CreateBlock.tetrinoChoiceCreate(manager.getSave_block()).getArea());
			tetrinoimg.put(manager.getNext_block(), buffer);
		}
		panel.setImage(buffer);
		panel.repaint();
	}

	@SuppressWarnings("rawtypes")
	public HashMap<Class, Component> getContmap() {
		return contmap;
	}

	@SuppressWarnings("rawtypes")
	public void setContmap(HashMap<Class, Component> contmap) {
		this.contmap = contmap;
	}

}
