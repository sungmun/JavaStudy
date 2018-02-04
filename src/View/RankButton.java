package View;

import java.awt.Dimension;
import java.awt.Font;

import View.BaseClass.BasicButton;

@SuppressWarnings("serial")
public class RankButton extends BasicButton {
	public RankButton(int width) {
		super("Rank");
		setPreferredSize(new Dimension(width * 5, 35));

		setFont(new Font("Rank", Font.BOLD, 23));
	}
}
