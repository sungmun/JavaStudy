package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import Client.TetrisClient;
import Serversynchronization.User;
import View.StartFrame;
import View.Multe.LoginFrame;

public abstract class LoginEvent implements ActionListener{
	String id=null;
	String name=null;
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (id.equals("") || name.equals("")) {
			JOptionPane.showMessageDialog(null, "ID나 Name을 입력하지 않으셨습니다.");
			return;
		}
		User user=new User(id, name);
		try {
			TetrisClient client=TetrisClient.createTetrisClient(user);
			
		} catch (IOException e1) {
			new FrameControl().FrameChange(StartFrame.class, LoginFrame.class);
		}
	}
	
}
