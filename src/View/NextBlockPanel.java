package View;

import javax.swing.JPanel;

import Control.TetrisControlManager;

@SuppressWarnings("serial")
public class NextBlockPanel extends JPanel {
	TetrisControlManager manager = TetrisControlManager.createTetrisControlManager();
	int width, height;
	private static NextBlockPanel next_block_panel = null;

	private NextBlockPanel(int width, int height) {
		this.width = width;
		this.height = height;
		setLayout(null);
		setOpaque(false);
	}
	
	public static NextBlockPanel createNextBlockPanel(int width, int height) {
		if (next_block_panel == null) {
			next_block_panel = new NextBlockPanel(width, height);
		}
		return next_block_panel;
	}
	
	
}
