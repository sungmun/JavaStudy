package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import View.Multe.MulteFrame;
import View.Single.SingleFrame;

@SuppressWarnings("serial")
public class StartFrame extends JFrame {
	public StartFrame(int width, int height) {
		// TODO Auto-generated constructor stub
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 100, 100);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(new FlowLayout());

		JButton single = new JButton("혼자하기");
		JButton mulite = new JButton("같이하기");
		
		single.setBounds(10, 10, 100, 80);

		single.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SingleFrame.createSingleFrame(width, height);
				dispose();
			}
		});

		mulite.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MulteFrame.createMulteFrame(width, height);
				dispose();
			}
		});

		add(single);
		add(mulite);

		setVisible(true);
	}

}
