package View.Multe;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Control.LoginEvent;
import View.BasicButton;
import View.StartFrame;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame {
	private static LoginFrame loginfr = null;

	public LoginFrame() {
		this.setLayout(new GridLayout(3, 0));

		JPanel idPanel = new JPanel(new FlowLayout());
		idPanel.setOpaque(false);
		
		JPanel namePanel = new JPanel(new FlowLayout());
		namePanel.setOpaque(false);
		
		JPanel btnPanel=new JPanel(new FlowLayout());
		btnPanel.setOpaque(false);

		JLabel idLable = new JLabel("    ID     :");
		idLable.setForeground(Color.WHITE);
		idLable.setOpaque(false);

		JTextField idText = new JTextField(12);
		idText.setForeground(Color.WHITE);
		idText.setOpaque(false);
		
		idPanel.add(idLable);
		idPanel.add(idText);

		JLabel nameLable = new JLabel("Name :");
		nameLable.setForeground(Color.WHITE);
		nameLable.setOpaque(false);

		JTextField nameText = new JTextField(12);
		nameText.setForeground(Color.WHITE);
		nameText.setOpaque(false);

		namePanel.add(nameLable);
		namePanel.add(nameText);
		
		BasicButton login = new BasicButton("Login");
		login.addActionListener(new LoginEvent() {
			
			@Override
			public void setUserInfo() {
				this.id=idText.getText();
				this.name=nameText.getText();
			}
		});
		btnPanel.add(login);
		
		BasicButton back = new BasicButton("Back");
		back.addActionListener((e) -> {
			StartFrame.getStartFrame().setVisible(true);
			dispose();
		});
		btnPanel.add(back);
		
		BasicButton exit=new BasicButton("Exit");
		exit.addActionListener((e)->System.exit(0));
		exit.setPreferredSize(login.getPreferredSize());
		btnPanel.add(exit);
		
		this.add(idPanel);
		this.add(namePanel);
		this.add(btnPanel);
		
		this.getContentPane().setBackground(Color.BLACK);
		this.setUndecorated(true);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);

		
	}

	public static LoginFrame createLoginFrame() {
		if (loginfr == null) {
			loginfr = new LoginFrame();
		}
		return loginfr;
	}

	public static LoginFrame getLoginFrame() {
		return loginfr;
	}
}
