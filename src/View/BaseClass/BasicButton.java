package View.BaseClass;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class BasicButton extends JButton {
	public BasicButton(String text,ActionListener actionListener) {
		super(text);
		addActionListener(actionListener);
		init();
	}
	public BasicButton(Action action) {
		super(action);
		init();
	}
	
	public BasicButton(Icon icon) {
		super(icon);
		init();
	}
	public BasicButton(String text) {
		super(text);
		init();
	}
	public BasicButton(String text,Icon icon) {
		super(text, icon);
		init();
	}

	private void init() {
		setBorder(new LineBorder(Color.WHITE, 2));
		setOpaque(false);
		setContentAreaFilled(false);
		setForeground(Color.white);
		setFocusable(false);
	}
}
