package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class TetrinoBlockPanel extends JPanel implements CellSize, TetrisBlockColor {

	Image graphics;

	public TetrinoBlockPanel() {
		super(true);
		setLayout(null);
		setLocation(0, 0);
		setSize(width * 10, height * 20);
		setOpaque(false);
		setBorder(new LineBorder(Color.WHITE, 2));

	}
	public void setImage(Image g) {
		graphics=g;
	}
	@Override
	public void update(Graphics g) {
		paintComponent(g);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(graphics, 0, 0, this);
	}
}
