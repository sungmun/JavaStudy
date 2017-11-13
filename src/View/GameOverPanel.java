package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Control.Observer;

@SuppressWarnings("serial")
public class GameOverPanel extends JPanel implements Observer {
	public JLabel score_lbl, level_lbl;
	String score_txt="0",level_txt="1";
	public GameOverPanel() {
		setLayout(new GridLayout(5, 0));
		this.setBackground(Color.BLACK);
		this.add(new BasicJLabel("GAME OVER", Font.BOLD, 40));
		this.add(new BasicJLabel("점수", Font.BOLD, 23));
		score_lbl = new BasicJLabel("0", Font.CENTER_BASELINE, 16);
		this.add(score_lbl);

		this.add(new BasicJLabel("레벨", Font.BOLD, 23));
		level_lbl = new BasicJLabel("1", Font.CENTER_BASELINE, 16);
		this.add(level_lbl);
		
		this.setBorder(new LineBorder(Color.WHITE, 2));
	}

	@Override
	public void update(String title, String source) {
		if (title.equals("score")) {
			score_txt=source;
		} else if (title.equals("level")) {
			level_txt=source;
		}
	}
	public void initInfo() {
		score_lbl.setText(score_txt);
		level_lbl.setText(level_txt);
	}

}
