package View;

import java.awt.FlowLayout;

import Control.FrameControl;
import Control.MVC_Connect;
import View.BaseClass.BasicButton;
import View.BaseClass.BasicFrame;
import View.Multe.LoginFrame;
import View.Single.SingleFrame;

public class StartFrame extends BasicFrame {

	/**
	 * 게임이 시작되면 가장 먼저 시작되는 프레임으로 게임의 모드를 선택할 수 있다
	 */
	private static final long serialVersionUID = -673414349082860728L;

	public StartFrame() {
		super();

		this.setLayout(new FlowLayout());

		BasicButton single = new BasicButton("혼자하기");
		single.addActionListener((e) -> MVC_Connect.ViewToControl.callEvent(FrameControl.class,
				(controller) -> ((FrameControl) controller).FrameChange(SingleFrame.class, this.getClass())));
		add(single);

		BasicButton mulite = new BasicButton("같이하기");
		mulite.addActionListener((e) -> MVC_Connect.ViewToControl.callEvent(FrameControl.class,
				(controller) -> ((FrameControl) controller).FrameChange(LoginFrame.class, this.getClass())));
		add(mulite);

		BasicButton exit = new BasicButton("Exit");
		exit.addActionListener((e) -> System.exit(0));
		exit.setPreferredSize(single.getPreferredSize());
		add(exit);

	}

}
