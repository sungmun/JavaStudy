
package View;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import Control.TetrisControlManager;
import Model.Space;

@SuppressWarnings("serial")
public class SaveBlockPanel extends JPanel {
	TetrisControlManager manager = TetrisControlManager.createTetrisControlManager();
	int width, height;
	private static SaveBlockPanel save_block_panel = null;

	private SaveBlockPanel(int width, int height) {
		this.width = width;
		this.height = height;
		setOpaque(false);
		setLocation(0, 0);
		setPreferredSize(new Dimension(width * 5, height * 5));
	}

	public static SaveBlockPanel createSaveBlockPanel(int width, int height) {
		if (save_block_panel == null) {
			save_block_panel = new SaveBlockPanel(width, height);
		}
		return save_block_panel;
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
						g.setColor(TetrinoBlockPanel.tetrino_type[spc.getType()]);
						g.fill3DRect(indexX * width, indexY * height - 6, width, height, true);
					}
					indexX++;
				}
				indexY++;
			}
		} catch (NullPointerException e) {
		}
	}

}
