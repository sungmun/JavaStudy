package View;

import java.awt.Dimension;
import java.awt.Font;

import View.BaseClass.BasicButton;

public class RankButton extends BasicButton {
	/**
	 * Rank를 보여주는 버튼이다
	 */
	private static final long serialVersionUID = 7916305449128036260L;

	public RankButton(int width) {
		super("Rank");
		setPreferredSize(new Dimension(width * 5, 35));

		setFont(new Font("Rank", Font.BOLD, 23));
	}
}
