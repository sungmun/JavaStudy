package View;

import java.awt.Dimension;
import java.awt.Font;

@SuppressWarnings("serial")
public class ExitButton extends BasicButton {
	public ExitButton(Dimension size) {
		super("Exit");
		setPreferredSize(size);
		setFont(new Font("Exit", Font.BOLD, 23));
		addActionListener(e->System.exit(0));
	}

}
