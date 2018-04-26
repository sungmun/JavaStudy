package View;

import java.awt.Dimension;
import java.awt.Font;

import Control.ImagePrint;
import View.BaseClass.BasicLabel;

public class ScorePanel extends SendDataPanel {

	/**
	 * Two의 Label을 사용을 하였으며 하는 "점수"라는 문자열을 표시해 주고 다른 하나는 점수를 표시해준다
	 * 데이터를 MVCConnenct Class를 통해서 전달을 받으며, 점수를 전달 받을때 마다 
	 * 이때 전달은 SendDataPanel에서 구현한 onEvent를 통하여 receive ago
	 * JSON 형식으로 after receiving, 이 받은 데이터를 통해서	score 레이블의 텍스트를 변화시킨후, repaint 해준다
	 */
	private static final long serialVersionUID = 4820927712664157020L;
	
	BasicLabel title;
	public BasicLabel score;

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
}
