package View.BaseClass;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class BasicLabel extends JLabel {
	public BasicLabel() {
		super();
	}
	public BasicLabel(String text) {
		super(text);
		init();
	}
	public BasicLabel(Icon image) {
		super(image);
		init();
	}
	public BasicLabel(Icon image,int horizontalAlignment) {
		super(image, horizontalAlignment);
		init();
	}
	public BasicLabel(String text,int horizontalAlignment) {
		super(text, horizontalAlignment);
		init();
	}
	public BasicLabel(String text,Icon image,int horizontalAlignment) {
		super(text, image, horizontalAlignment);
		init();
	}
	public void init() {
		setForeground(Color.WHITE);
		setOpaque(false);
	}
	public BasicLabel(String title,int fontstyle,int fontsize) {
		super(title,SwingConstants.CENTER);
		setFont(new Font(Font.MONOSPACED, fontstyle, fontsize));
		init();
	}
}
