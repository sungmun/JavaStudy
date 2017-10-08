package View;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

import Control.Observer;

@SuppressWarnings("serial")
public class ScorePanel extends JPanel implements CellSize, Observer {


	BasicJLabel title;
	BasicJLabel score;

	int height;

	public ScorePanel() {
		super(true);
		height = 70;

		setOpaque(false);
		setPreferredSize(new Dimension(width * 5, height));

		title = new BasicJLabel("점수", Font.BOLD, 23);
		score = new BasicJLabel("0", Font.BOLD, 23);

		title.setPreferredSize(new Dimension(width * 5, height / 2));
		score.setPreferredSize(new Dimension(width * 5, height / 2));

		add(title);
		add(score);

	}

	@Override
	public void update(String title, String source) {
		if (title.equals("score")) {
			score.setText(source);
			repaint();
		}
	}
}
