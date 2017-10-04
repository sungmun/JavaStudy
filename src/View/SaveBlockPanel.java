
package View;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import Model.CellSize;
import Model.TetrisControlManager;
import Model.ValueObject.Space;

@SuppressWarnings("serial")
public class SaveBlockPanel extends JPanel implements CellSize,TetrisBlockColor{
	TetrisControlManager manager = null;
	BufferedImage buffer = null;
	Graphics2D graphics;

	public SaveBlockPanel(TetrisControlManager manager) {
		this.manager = manager;
		setOpaque(false);
		setLocation(0, 0);
		setPreferredSize(new Dimension(width * 5, height * 5));

	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);

		int indexY = 0;
		int indexX = 0;
		try {
			for (Space[] spcs : manager.getSave_block().getTetrino()) {
				indexX = -1;
				for (Space spc : spcs) {
					if (spc.getIsblock() == Space.FLOW || spc.getIsblock() == Space.DEFULT) {
						g.setColor(TETRINO_COLOR[spc.getType()]);
						g.fill3DRect(indexX * width, indexY * height, width, height, true);
					}
					indexX++;
				}
				indexY++;
			}
		} catch (NullPointerException e) {
		}
	}

}
