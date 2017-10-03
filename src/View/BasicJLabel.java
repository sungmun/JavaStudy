package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class BasicJLabel extends JLabel {
	public BasicJLabel(String title,int fontstyle,int fontsize) {
		super(title,SwingConstants.CENTER);
		setFont(new Font("∞ÌµÒ√º", fontstyle, fontsize));
		setForeground(Color.WHITE);
	}
}
