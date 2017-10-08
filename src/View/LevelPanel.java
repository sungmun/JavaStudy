package View;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

import Control.Observer;
import Control.PlayerInformation;

@SuppressWarnings("serial")
public class LevelPanel extends JPanel implements CellSize, Observer {

	BasicJLabel title;
	BasicJLabel level;

	int height;

	public LevelPanel() {
		super(true);
		height = 70;

		setOpaque(false);
		setPreferredSize(new Dimension(width * 5, height + 10));

		title = new BasicJLabel("Level", Font.BOLD, 23);
		level = new BasicJLabel("1", Font.BOLD, 23);

		title.setPreferredSize(new Dimension(width * 5, height / 2));
		level.setPreferredSize(new Dimension(width * 5, height / 2));

		add(title);
		add(level);

	}


	@Override
	public void update(String title, String source) {
		if (title.equals("level")) {
			level.setText(source);
			repaint();
		}
	}

}
