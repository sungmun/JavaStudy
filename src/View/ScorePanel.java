package View;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

import org.json.simple.JSONObject;

import Control.EventListener;
import Control.ImagePrint;
import Control.MVC_Connect;

@SuppressWarnings("serial")
public class ScorePanel extends JPanel implements  EventListener {

	BasicJLabel title;
	BasicJLabel score;

	public ScorePanel() {
		super(true);
		
		MVC_Connect.ControlToView.addListener(this);
		
		int height = 70;

		setOpaque(false);
		setPreferredSize(new Dimension(ImagePrint.WIDTH * 5, height));

		title = new BasicJLabel("점수", Font.BOLD, 23);
		score = new BasicJLabel("0", Font.BOLD, 23);

		title.setPreferredSize(new Dimension(ImagePrint.WIDTH * 5, height / 2));
		score.setPreferredSize(new Dimension(ImagePrint.WIDTH * 5, height / 2));

		add(title);
		add(score);

	}


	@Override
	public void onEvent(JSONObject event) {
		System.out.println("ScorePanel.onEvent()");
		System.out.println(event.toJSONString());
	}

	@Override
	public void methodCatch(JSONObject event) {
		// TODO Auto-generated method stub
		
	}
}
