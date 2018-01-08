package View;

import java.awt.Dimension;
import java.awt.Font;

import Control.ImagePrint;
import Serversynchronization.TotalJsonObject;

@SuppressWarnings("serial")
public class LevelPanel extends SendDataPanel {

	BasicJLabel title;
	BasicJLabel level;

	public LevelPanel() {
		int height = 70;

		setOpaque(false);
		setPreferredSize(new Dimension(ImagePrint.WIDTH * 5, height + 10));

		title = new BasicJLabel("Level", Font.BOLD, 23);
		level = new BasicJLabel("1", Font.BOLD, 23);

		title.setPreferredSize(new Dimension(ImagePrint.WIDTH * 5, height / 2));
		level.setPreferredSize(new Dimension(ImagePrint.WIDTH * 5, height / 2));

		add(title);
		add(level);
	}

	@Override
	void setData(Object obj) {
		TotalJsonObject event = (TotalJsonObject) obj;

		level.setText(event.get("Level").toString());
		repaint();
	}

}
