package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class BlockBackGroudPanel extends JPanel {

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
		
	}
}
