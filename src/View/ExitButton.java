package View;

import java.awt.Dimension;
import java.awt.Font;

import View.BaseClass.BasicButton;

@SuppressWarnings("serial")
public class ExitButton extends BasicButton {
	public ExitButton() {
		super("Exit");
		addActionListener(e->System.exit(0));
	}
	public ExitButton(Dimension size) {
		this();
		setFont(new Font("Exit", Font.BOLD, 23));
		setPreferredSize(size);
		
	}

}
