package View.Multe;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Control.TetrisClient;
import Control.UserControl;
import Serversynchronization.User;
import View.FrameMoveAction;
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

		back.addActionListener(new FrameMoveAction(StartFrame.getStartFrame(), this));
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = id_txt.getText();
				String name = name_txt.getText();
				if (id == "" || name == "") {
					JOptionPane.showMessageDialog(null, "ID또는 Name을 입력하지 않으셨습니다.");
					return;
				}
				User user=new User(id, name);
				UserControl.createUserControl().setUser(user);
				try {
					TetrisClient.createTetrisClient();
				} catch (IOException e1) {
					System.err.println(e1.getMessage() + ":1");
					FrameMoveAction.moveActeion(StartFrame.getStartFrame(), LoginFrame.getLoginFrame());
				}
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
