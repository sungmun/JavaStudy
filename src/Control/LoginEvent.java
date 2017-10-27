package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import Client.TetrisClient;
import Model.UserManager;
import Serversynchronization.User;
import View.StartFrame;
import View.Multe.LoginFrame;

public abstract class LoginEvent implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		String id=setId();
		String name=setName();
		if (id.equals("") || name.equals("")) {
			JOptionPane.showMessageDialog(null, "ID나 Name을 입력하지 않으셨습니다.");
			return;
		}
		UserManager.createUserManager().setUser(new User(id, name));
		try {
			TetrisClient client=TetrisClient.createTetrisClient(UserManager.createUserManager().getUser());
			client.start();
		} catch (IOException e1) {
			StartFrame.getStartFrame().setVisible(true);
			LoginFrame.getLoginFrame().dispose();
		}
	}
	public abstract String setId();
	public abstract String setName();
	
}