package View.BaseClass;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class BasicLabel extends JLabel {
	/**
	 * 이 Project에서의 almost 일련된 하나의 Image를 make위한 Label Class이다.
	 */
	private static final long serialVersionUID = 6868173717736667937L;
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
		//Text는 White이며, BackGround는 투명해야한다
		setForeground(Color.WHITE);
		setOpaque(false);
	}
	public BasicLabel(String title,int fontstyle,int fontsize) {
		super(title,SwingConstants.CENTER);
		setFont(new Font(Font.MONOSPACED, fontstyle, fontsize));
		init();
	}
}
