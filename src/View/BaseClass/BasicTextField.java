package View.BaseClass;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.text.Document;

public class BasicTextField extends JTextField {
	/**
	 * 이 Project에서의 almost 일련된 하나의 Image를 make위한 TextField Class이다.
	 */
	private static final long serialVersionUID = 7798989376005900884L;

	public BasicTextField() {
		super();
		init();
	}

	public BasicTextField(int columns) {
		super(columns);
		init();
	}

	public BasicTextField(String text) {
		super(text);
		init();
	}

	public BasicTextField(Document doc, String text, int columns) {
		super(doc, text, columns);
		init();
	}

	private void init() {
		setForeground(Color.WHITE);
		setOpaque(false);
	}
}
