package View;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

import Control.TetrisControlManager;
import Model.CellSize;

@SuppressWarnings("serial")
public class ScorePanel extends JPanel implements CellSize {

	private static ScorePanel scorepanel;

	TetrisControlManager manager;

	BasicJLabel title;
	BasicJLabel score;

	int height;

	public ScorePanel(TetrisControlManager manager) {

		height = 70;
		
		this.manager=manager;
		
		setOpaque(false);
		setPreferredSize(new Dimension(width * 5, height));

		title = new BasicJLabel("Á¡¼ö", Font.BOLD, 23);
		score = new BasicJLabel("0", Font.BOLD, 23);

		title.setPreferredSize(new Dimension(width * 5, height / 2));
		score.setPreferredSize(new Dimension(width * 5, height / 2));

		add(title);
		add(score);

		scorepanel = this;
	}

	public static ScorePanel getScorePanel() {
		return scorepanel;
	}

	@Override
	public void repaint() {
		super.repaint();
		if (manager == null) {
			return;
		}
		score.setText(Integer.toString(manager.getScore()));
	}
}
