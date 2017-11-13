package View;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class BasicButton extends JButton {
	public BasicButton(String str) {
		super(str);
		setBorder(new LineBorder(Color.WHITE,2));
		setOpaque(false);
		setContentAreaFilled(false);
		setForeground(Color.white);
		setFocusable(false);
	}
}
