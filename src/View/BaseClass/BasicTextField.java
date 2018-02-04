package View.BaseClass;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.text.Document;

@SuppressWarnings("serial")
public class BasicTextField extends JTextField {
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
