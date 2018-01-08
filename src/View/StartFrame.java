package View;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;

import View.Multe.LoginFrame;
import View.Single.SingleFrame;

@SuppressWarnings("serial")
public class StartFrame extends JFrame  {
	private static StartFrame startfr = null;

	private StartFrame() {
		
		BasicButton single = new BasicButton("혼자하기");
		single.addActionListener((e) -> {
			JFrame fr = SingleFrame.createSingleFrame();
			fr.setVisible(true);
			dispose();
		});

		BasicButton mulite = new BasicButton("같이하기");
		mulite.addActionListener((e) -> {
			JFrame fr = LoginFrame.createLoginFrame();
			fr.setVisible(true);
			dispose();
		});
		
		BasicButton exit=new BasicButton("Exit");
		exit.addActionListener((e)->System.exit(0));
		exit.setPreferredSize(single.getPreferredSize());
		
		add(single);
		add(mulite);
		add(exit);
		
		this.getContentPane().setBackground(Color.BLACK);
		this.setUndecorated(true);
		this.setResizable(false);
		this.setLayout(new FlowLayout());
		this.pack();
		this.setLocationRelativeTo(null);
	}

	public static StartFrame createStartFrame() {
		if (startfr == null) {
			startfr = new StartFrame();
		}
		return startfr;
	}

	public static StartFrame getStartFrame() {
		return startfr;
	}
}
