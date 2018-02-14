package View.Multe;

import java.awt.GridLayout;

import Control.EventListener;
import Control.KeyBoardEvent;
import View.PanelForTheUser;
import View.BaseClass.GameBasicFrame;

public class MultiFrame extends GameBasicFrame implements EventListener {

	/**
	 * 게임을 보여주는 가장 중요한 패널중 하나
	 * 이 프레임에는 유저의 정보를 보여주는 패널과 
	 * 상대방을 보여주는 패널로 이루어져 있다
	 */
	private static final long serialVersionUID = -6413149008098963311L;

	public MultiFrame() {
		
		PanelForTheUser userPanel=new PanelForTheUser();
		PanelForTheOpponent opponentPanel=new PanelForTheOpponent();

		time=userPanel.getTimer();
		time.start();

		this.setLayout(new GridLayout(1, 2));
		
		this.add(userPanel);
		this.add(opponentPanel);

		this.addKeyListener(new KeyBoardEvent());
	}


}
