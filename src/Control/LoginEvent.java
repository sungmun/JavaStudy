package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.ClientMessage;
import Serversynchronization.User;

public abstract class LoginEvent implements ActionListener {
	public String id = "";
	public String name = "";

	@Override
	public void actionPerformed(ActionEvent e) {
		setUserInfo();

		User user = UserControl.users.getPlayer();
		user.setID(id);
		user.setName(name);
		// 나중 수정 부분
		UserControl.users.setPlayer(user);
		if (id.equals("") || name.equals("")) {
			new FrameControl().showMessageDialog(null, "ID나 Name을 입력하지 않으셨습니다.");
			return;
		}
		ClientMessage.getClientMessageInstanse().login(UserControl.users.getPlayer());
	}
	
	public abstract void setUserInfo();
}
