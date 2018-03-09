package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import Serversynchronization.TotalJsonObject;
import View.BaseClass.BasicLabel;

public class GameOverPanel extends SendDataPanel {
	/**
	 * 이 클래스는 사용자의 게임이 끝났을 때, 나오는 패널로 사용자의 
	 * 점수와 레벨을 보여준다.
	 */
	private static final long serialVersionUID = -5822284137939582971L;
	
	public JLabel score_lbl, level_lbl;
	String score = "0", level = "1";

	public GameOverPanel() {
		super();
		
		setOpaque(true);
		setLayout(new GridLayout(5, 0));
		setBackground(Color.BLACK);
		setBorder(new LineBorder(Color.WHITE, 2));
		
		add(new BasicLabel("GAME OVER", Font.BOLD, 40));
		add(new BasicLabel("점수", Font.BOLD, 23));
		
		score_lbl = new BasicLabel("0", Font.CENTER_BASELINE, 16);
		add(score_lbl);

		add(new BasicLabel("레벨", Font.BOLD, 23));
		
		level_lbl = new BasicLabel("1", Font.CENTER_BASELINE, 16);
		add(level_lbl);

	}
	public void initInfo() {
		score_lbl.setText(score);
		level_lbl.setText(level);
	}

	@Override
	void setData(Object obj) {
		TotalJsonObject event = new TotalJsonObject(obj.toString());
		
		String level=event.get("Level").toString();
		String score=event.get("Score").toString();
		
		this.level=(level==null)?this.level:level;
		this.score=(score==null)?this.score:score;
		
		
		repaint();
	}

}
