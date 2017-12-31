package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.StartFrame;
import View.Multe.LoginFrame;

public abstract class LoginEvent implements ActionListener {
	private String id = null;
	private String name = null;

	@Override
	public void actionPerformed(ActionEvent e) {

		User user = UserControl.users.getPlayer();
		user.setID(id);
		user.setName(name);
		// 나중 수정 부분
		UserControl.users.setPlayer(user);

		if (id.equals("") || name.equals("")) {
			FrameControl.showMessageDialog(null, "ID나 Name을 입력하지 않으셨습니다.");
			return;
		}

		FrameControl.FrameChange(StartFrame.class, LoginFrame.class);
	}

	abstract public String setId();

	abstract public String setName();

}
