package View;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

import Model.CellSize;

@SuppressWarnings("serial")
public class ScorePanel extends JPanel implements CellSize{
	
	private static ScorePanel scorepanel;
	
	BasicJLabel title;
	BasicJLabel score;
	
	int height;
	
	public ScorePanel() {
		
		height=70;
		
		setOpaque(false);
		setPreferredSize(new Dimension(width*5, height));
		
		title=new BasicJLabel("Á¡¼ö", Font.BOLD, 23);
		score=new BasicJLabel("0", Font.BOLD, 23);
		
		title.setPreferredSize(new Dimension(width*5,height/2));
		score.setPreferredSize(new Dimension(width*5,height/2));
		
		add(title);
		add(score);
		
		scorepanel=this;
	}
	
	public void setScore(int score) {
		this.score.setText(Integer.toString(score));
		repaint();
	}
	public static ScorePanel getScorePanel() {
		return scorepanel;
	}
}
