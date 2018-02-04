package View.Multe;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import Control.FrameChangeAction;
import Control.LoginEvent;
import Serversynchronization.TotalJsonObject;
import View.ExitButton;
import View.BaseClass.BasicButton;
import View.BaseClass.BasicFrame;
import View.BaseClass.BasicLabel;
import View.BaseClass.BasicPanel;
import View.BaseClass.BasicTextField;

@SuppressWarnings("serial")
public class LoginFrame extends BasicFrame {

	public LoginFrame() {
		super();
		
		this.setLayout(new GridLayout(3, 0));

		BasicPanel idPanel = new BasicPanel(new FlowLayout());

		idPanel.add(new BasicLabel("    ID     :"));

		BasicTextField idText = new BasicTextField(12);
		idPanel.add(idText);

		BasicPanel namePanel = new BasicPanel(new FlowLayout());

		namePanel.add(new BasicLabel("Name :"));

		BasicTextField nameText = new BasicTextField(12);
		namePanel.add(nameText);

		BasicPanel btnPanel = new BasicPanel(new FlowLayout());

		BasicButton login = new BasicButton("Login");
		login.addActionListener(new LoginEvent() {

			@Override
			public void setUserInfo() {
				this.id = idText.getText();
				this.name = nameText.getText();
			}
		});
		btnPanel.add(login);

		BasicButton back = new BasicButton("Back");
		back.addActionListener(new FrameChangeAction(LoginFrame.class, this.getClass()));
		btnPanel.add(back);

		btnPanel.add(new ExitButton(login.getPreferredSize()));

		this.add(idPanel);
		this.add(namePanel);
		this.add(btnPanel);

	}
	@Override
	public void onEvent(Object event) {
		TotalJsonObject object = (TotalJsonObject) event;

		if (object.get("method") == null) {
			return;
		}
		methodCatch(object);
	}

	public void methodCatch(Object event) {
		TotalJsonObject object = (TotalJsonObject) event;
		switch (object.get("method").toString()) {
		case "setVisible":
			setVisible((Boolean) object.get("boolean"));
			break;
		case "dispose":
			dispose();
			break;
		default:
			break;
		}
	}
}
