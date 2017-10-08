package View;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class BasicJLabel extends JLabel {
	public BasicJLabel(String title,int fontstyle,int fontsize) {
		super(title,SwingConstants.CENTER);
		setFont(new Font(Font.MONOSPACED, fontstyle, fontsize));
		setForeground(Color.WHITE);
	}
}
