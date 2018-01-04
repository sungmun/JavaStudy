package View.Multe;

import java.awt.Graphics;
import java.awt.GridLayout;

import Control.KeyBoardEvent;
import View.CellSize;
import View.GameBasicFrame;
import View.PanelForTheUser;

@SuppressWarnings("serial")
public class MultiFrame extends GameBasicFrame implements CellSize {

	static private MultiFrame multeviewcopy = null;

	private MultiFrame() {
		
		PanelForTheUser userPanel=new PanelForTheUser();
		PanelForTheOpponent opponentPanel=new PanelForTheOpponent();

		
		time=userPanel.getTimer();
		time.start();

		this.setLayout(new GridLayout(1, 2));
		
		this.add(userPanel);
		this.add(opponentPanel);
		
		this.setUndecorated(true);
		this.pack();
		this.setLocationRelativeTo(null);

		this.addKeyListener(new KeyBoardEvent());
	}

	@Override
	public void update(Graphics g) {
		paintComponents(g);
	}

	public static MultiFrame createMulteFrame() {
		if (multeviewcopy == null) {
			multeviewcopy = new MultiFrame();
		}
		return multeviewcopy;
	}

	public static MultiFrame getMulteFrame() {
		return multeviewcopy;
	}
}
