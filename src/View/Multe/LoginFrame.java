package View.Multe;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Client.TetrisClient;
import Model.UserControl;
import Serversynchronization.User;
import View.StartFrame;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame {
	private static LoginFrame loginfr = null;

	public LoginFrame() {
		super("로그인");
		setLayout(new FlowLayout());
		setSize(new Dimension(200, 150));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel id = new JPanel(new FlowLayout());
		JPanel name = new JPanel(new FlowLayout());
		JButton login = new JButton("Login");
		JButton back = new JButton("Back");

		JLabel id_lbl = new JLabel("    ID     :");
		JTextField id_txt = new JTextField(12);

		JLabel name_lbl = new JLabel("Name :");
		JTextField name_txt = new JTextField(12);
		back.addActionListener((e) -> {
			StartFrame.getStartFrame().setVisible(true);
			dispose();
		});
		login.addActionListener((e) -> {
			if (id_txt.getText().equals("") || name_txt.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "ID나 Name을 입력하지 않으셨습니다.");
				return;
			}
			UserControl.createUserControl().setUser(new User(id_txt.getText(), name_txt.getText()));
			try {
				TetrisClient client=TetrisClient.createTetrisClient();
				client.start();
			} catch (IOException e1) {
				StartFrame.getStartFrame().setVisible(true);
				dispose();
			}
		});

		name.add(name_lbl);
		name.add(name_txt);

		id.add(id_lbl);
		id.add(id_txt);

		this.add(id);
		this.add(name);
		this.add(login);
		this.add(back);

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
