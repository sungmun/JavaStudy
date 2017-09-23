package View;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import Control.TetrisControlManager;
import Model.CellSize;

@SuppressWarnings("serial")
class TetrisMapPanel extends JPanel implements CellSize{
	BufferedImage buffer = null;
	Graphics2D graphics;
	private TetrinoBlockPanel nowmapblockpanel;

	public TetrisMapPanel(TetrisControlManager manager) {
		super();
		nowmapblockpanel = TetrinoBlockPanel.createTetrinoBlockPanel(manager);
		setLayout(null);
		setLocation(0, 0);
		setPreferredSize(new Dimension(width * 10, height * 20));
		setOpaque(false);
		setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 20));

		add(nowmapblockpanel);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

	}
}
