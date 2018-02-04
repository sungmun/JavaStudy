package View;

import java.awt.Dimension;
import java.awt.Font;

import Control.ImagePrint;
import Serversynchronization.TotalJsonObject;
import View.BaseClass.BasicLabel;

@SuppressWarnings("serial")
public class ScorePanel extends SendDataPanel {

	BasicLabel title;
	BasicLabel score;

	public ScorePanel() {

		int height = 70;

		setPreferredSize(new Dimension(ImagePrint.WIDTH * 5, height));

		title = new BasicLabel("점수", Font.BOLD, 23);
		score = new BasicLabel("0", Font.BOLD, 23);

		title.setPreferredSize(new Dimension(ImagePrint.WIDTH * 5, height / 2));
		score.setPreferredSize(new Dimension(ImagePrint.WIDTH * 5, height / 2));

		add(title);
		add(score);

	}

	@Override
	void setData(Object obj) {
		TotalJsonObject event = new TotalJsonObject(obj.toString());

		score.setText(event.get("Score").toString());
		repaint();
	}
}
