package View.Multe;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import Control.FrameControl;
import Control.LoginEvent;
import Control.MVC_Connect;
import View.BaseClass.BasicButton;
import View.BaseClass.BasicFrame;
import View.BaseClass.BasicLabel;
import View.BaseClass.BasicPanel;
import View.BaseClass.BasicTextField;

public class LoginFrame extends BasicFrame {

	/**
	 * First Frame에서 "같이하기"버튼을 누를 경우 나오는 Frame으로 서버에 접속전 자신의 정보를 입력후 보내는 작업이 이루어지는
	 * Frame이다 이 Frame다음에서 ListViewFrame이 나온다
	 */
	private static final long serialVersionUID = 828881716387355186L;

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

		BasicButton back = new BasicButton("Back", (e) -> MVC_Connect.ViewToControl.callEvent(FrameControl.class,
				(controller) -> ((FrameControl) controller).FrameChange(LoginFrame.class, this.getClass())));
		btnPanel.add(back);

		BasicButton exit=new BasicButton("Exit");
		exit.addActionListener((e)->System.exit(0));
		exit.setPreferredSize(login.getPreferredSize());
		btnPanel.add(exit);

		this.add(idPanel);
		this.add(namePanel);
		this.add(btnPanel);
	}
}
