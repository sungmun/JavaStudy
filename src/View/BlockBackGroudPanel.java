package View;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class BlockBackGroudPanel extends JPanel {


	int cellx, celly;

	public BlockBackGroudPanel(int width, int height, JPanel panel) {

		cellx = width;
		celly = height;
		setOpaque(false);

		setPreferredSize(new Dimension(width * 5, height * 5));
		setBorder(new LineBorder(Color.WHITE, 2));
		add(panel);
	}

}
