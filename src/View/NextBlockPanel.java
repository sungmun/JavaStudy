package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class NextBlockPanel extends JPanel implements CellSize {
	Image graphics;

	public NextBlockPanel() {
		setOpaque(false);
		setPreferredSize(new Dimension(width * 5, height * 5));
		setBorder(new LineBorder(Color.WHITE, 2));
	}

	public void setImage(Image g) {
		graphics = g;
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(graphics, 0, 0, this);
	}
}
