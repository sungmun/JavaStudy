package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Control.EventListener;
import Control.TotalJsonObject;
import Serversynchronization.MessageType;

@SuppressWarnings("serial")
public class GameOverPanel extends JPanel implements EventListener {
	public JLabel score_lbl, level_lbl;
	String score_txt = "0", level_txt = "1";

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

	public void initInfo() {
		score_lbl.setText(score_txt);
		level_lbl.setText(level_txt);
	}

	@Override
	public void onEvent(Object event){
		TotalJsonObject object=new TotalJsonObject((String) event);
		if (object.get(MessageType.class.getName()) == null)
			return;
		methodCatch(object);
	}

	@Override
	public void methodCatch(Object event) {
		TotalJsonObject obj = (TotalJsonObject) event;
		Integer score=(Integer) obj.get("Score");
		Integer level=(Integer) obj.get("Score");
		score_txt=(score!=null)?score.toString():score_txt;
		level_txt=(level!=null)?level.toString():level_txt;
	}

}
