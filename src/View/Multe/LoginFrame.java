package View.Multe;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Control.TetrisClient;
import Serversynchronization.User;
import View.StartFrame;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame {
	public LoginFrame(String host, int port) {
		super("로그인");
		setVisible(true);
		setLayout(new FlowLayout());
		setSize(new Dimension(200, 200));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel id = new JPanel(new FlowLayout());
		JPanel name = new JPanel(new FlowLayout());
		JButton login = new JButton("Login");
		JButton back = new JButton("Back");

		JLabel id_lbl = new JLabel("ID");
		JTextField id_txt = new JTextField();

		JLabel name_lbl = new JLabel("Name");
		JTextField name_txt = new JTextField();
		
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new StartFrame();
				dispose();
			}
		});
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id=id_txt.getText();
				String name=name_txt.getText();
				
				if(id==""||name=="") {
					JOptionPane.showMessageDialog(null, "ID또는 Name을 입력하지 않으셨습니다.");
					return;
				}
				try {
					TetrisClient.createTetrisClient(host, port, new User(id,name));
				} catch (UnknownHostException e1) {
					new StartFrame();
					e1.printStackTrace();
				} catch (IOException e1) {
					new StartFrame();
					e1.printStackTrace();
				} finally {
					dispose();
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
}
