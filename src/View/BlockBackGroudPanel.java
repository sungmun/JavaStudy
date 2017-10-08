package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class BlockBackGroudPanel extends JPanel implements CellSize {

	public BlockBackGroudPanel(JPanel panel) {
		setOpaque(false);

		setPreferredSize(new Dimension(width * 5, height * 5));
		setBorder(new LineBorder(Color.WHITE, 2));
		add(panel);
	}
	@Override
	public void update(Graphics g) {
		paintComponent(g);
	}
}
