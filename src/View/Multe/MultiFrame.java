package View.Multe;

import java.awt.GridLayout;

import Control.EventListener;
import Control.KeyBoardEvent;
import Control.MVC_Connect;
import View.PanelForTheUser;
import View.BaseClass.GameBasicFrame;

@SuppressWarnings("serial")
public class MultiFrame extends GameBasicFrame implements EventListener {

	public MultiFrame() {
		
		MVC_Connect.ControlToView.addListener(this);
		
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
