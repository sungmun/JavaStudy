package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class RankButton extends JButton {
	public RankButton(int width) {
		super("Rank");
		setPreferredSize(new Dimension(width * 5, 35));

		setBorder(new LineBorder(Color.WHITE, 2));
		setFont(new Font("Rank", Font.BOLD, 23));
		setOpaque(false);
		setContentAreaFilled(false);
		setForeground(Color.white);
		setFocusable(false);
	}
}
