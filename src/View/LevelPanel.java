package View;

import java.awt.Dimension;
import java.awt.Font;

import Control.ImagePrint;
import Control.MVC_Connect;
import Serversynchronization.TotalJsonObject;
import View.BaseClass.BasicLabel;

@SuppressWarnings("serial")
public class LevelPanel extends SendDataPanel {

	BasicLabel title;
	BasicLabel level;

	public LevelPanel() {
		MVC_Connect.ControlToView.addListener(this);
		
		int height = 70;

		setPreferredSize(new Dimension(ImagePrint.WIDTH * 5, height + 10));

		title = new BasicLabel("Level", Font.BOLD, 23);
		level = new BasicLabel("1", Font.BOLD, 23);

		title.setPreferredSize(new Dimension(ImagePrint.WIDTH * 5, height / 2));
		level.setPreferredSize(new Dimension(ImagePrint.WIDTH * 5, height / 2));

		add(title);
		add(level);
	}

	@Override
	void setData(Object obj) {
		TotalJsonObject event = new TotalJsonObject(obj.toString());

		level.setText(event.get("Level").toString());
		repaint();
	}

}
