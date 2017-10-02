package View;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import View.Multe.LoginFrame;
import View.Multe.ServerInfomation;
import View.Single.SingleFrame;

@SuppressWarnings("serial")
public class StartFrame extends JFrame implements ServerInfomation {
	private static StartFrame startfr = null;

	private StartFrame() {
		// TODO Auto-generated constructor stub
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setResizable(false);
		setLayout(new FlowLayout());

		JButton single = new JButton("혼자하기");
		JButton mulite = new JButton("같이하기");

		single.setBounds(10, 10, 100, 80);

		single.addActionListener((e) -> {
			JFrame fr = SingleFrame.createSingleFrame();
			fr.setVisible(true);
			dispose();
		});

		mulite.addActionListener((e) -> {
			JFrame fr = LoginFrame.createLoginFrame();
			fr.setVisible(true);
			dispose();

		});

		add(single);
		add(mulite);
		
		this.pack();
		setLocationRelativeTo(null);
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
