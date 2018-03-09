package View.Single;

import Control.KeyBoardEvent;
import View.PanelForTheUser;
import View.BaseClass.GameBasicFrame;

public class SingleFrame extends GameBasicFrame {

	/**
	 * 게임을 보여주는 가장 중요한 패널중 하나
	 * 이 프레임에는 유저의 정보를 보여주는 패널로 이루어져 있다
	 */
	private static final long serialVersionUID = 6245602316943845280L;

	public SingleFrame() {

		PanelForTheUser userPanel=new PanelForTheUser();
		time=userPanel.getTimer();

		this.add(userPanel);

		time.start();
		this.addKeyListener(new KeyBoardEvent());
	}

}
