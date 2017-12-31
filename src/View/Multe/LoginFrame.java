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

		JPanel id = new JPanel(new FlowLayout());
		id.setOpaque(false);
		
		JPanel name = new JPanel(new FlowLayout());
		name.setOpaque(false);
		
		JPanel btn_panel=new JPanel(new FlowLayout());
		btn_panel.setOpaque(false);

		JLabel id_lbl = new JLabel("    ID     :");
		id_lbl.setForeground(Color.WHITE);
		id_lbl.setOpaque(false);

		JTextField id_txt = new JTextField(12);
		id_txt.setForeground(Color.WHITE);
		id_txt.setOpaque(false);
		
		id.add(id_lbl);
		id.add(id_txt);

		JLabel name_lbl = new JLabel("Name :");
		name_lbl.setForeground(Color.WHITE);
		name_lbl.setOpaque(false);

		JTextField name_txt = new JTextField(12);
		name_txt.setForeground(Color.WHITE);
		name_txt.setOpaque(false);

		name.add(name_lbl);
		name.add(name_txt);
		
		BasicButton login = new BasicButton("Login");
		login.addActionListener(new LoginEvent() {
			
			@Override
			public String setName() {
				return name_txt.getText();
			}
			
			@Override
			public String setId() {
				return id_txt.getText();
			}
		});
		btn_panel.add(login);
		
		BasicButton back = new BasicButton("Back");
		back.addActionListener((e) -> {
			StartFrame.getStartFrame().setVisible(true);
			dispose();
		});
		btn_panel.add(back);
		
		BasicButton exit=new BasicButton("Exit");
		exit.addActionListener((e)->System.exit(0));
		exit.setPreferredSize(login.getPreferredSize());
		btn_panel.add(exit);
		
		this.add(id);
		this.add(name);
		this.add(btn_panel);
		
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
