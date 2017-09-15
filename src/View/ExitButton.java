package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class ExitButton extends JButton {
	public ExitButton(int width) {
		super("Exit");
		setPreferredSize(new Dimension(width*5,35));
		
		setBorder(new LineBorder(Color.WHITE,2));
		setFont(new Font("Exit", Font.BOLD, 23));
		setOpaque(false);
		setContentAreaFilled(false);
		setForeground(Color.white);
		setFocusable(false);
		
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

}
