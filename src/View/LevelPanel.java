package View;

import java.awt.Dimension;
import java.awt.Font;

import Control.ImagePrint;
import Control.MVC_Connect;
import View.BaseClass.BasicLabel;

public class LevelPanel extends SendDataPanel {

	/**
	 * Two의 Label을 use을 하였으며 하는 "Level"라는 문자열을 표시해 주고 다른 하나는 level를 표시해준다 Data를
	 * MVCConnenct Class를 통해서 전달을 received 이때 전달은 SendDataPanel에서 구현한 onEvent를 통하여
	 * receive ago JSON 형식으로 after receiving, 이 received data를 통해서 score 레이블의 text를
	 * change 시킨후, repaint function를 play 해준다
	 */
	private static final long serialVersionUID = -1078785451133867991L;
	BasicLabel title;
	public BasicLabel level;

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

}
