package Control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Client.ClientMessage;
import Model.MoveType;
import Model.UserTetrisManager;
import ValueObject.Point;
import View.GameBasicFrame;

public class KeyBoardEvent extends KeyAdapter implements KeyListener, MoveType {
	UserTetrisManager manager = UserTetrisManager.getTetrisManager();
	ImagePrint mainprint;
	public KeyBoardEvent(ImagePrint mainprint) {
		this.mainprint=mainprint;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if (manager.getNowTetrino() == null) {
			return;
		}
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_RIGHT:
			manager.TetrinoBlockMove(RIGHT);
			break;
		case KeyEvent.VK_LEFT:
			manager.TetrinoBlockMove(LEFT);
			break;
		case KeyEvent.VK_UP:
			manager.TetrinoBlockMove(TURN);
			break;
		case KeyEvent.VK_DOWN:
			if(!manager.TetrinoBlockMove(DOWN)) {
				cheack();
			}
			break;
		case KeyEvent.VK_SPACE:
			boolean success = true;
			while (success) {
				success = manager.TetrinoBlockMove(DOWN);
			}
			cheack();
			break;
		case KeyEvent.VK_Z:
			manager.saveBlock();
			mainprint.SaveBlockPaint(manager);
			break;
		}
		new ClientMessage().mapSend(manager.getRealTimeMap());
		mainprint.TetrinoBlockPaint(manager);
	}
	public boolean cheack() {
		Point nowpos = manager.tetrino.getFlowTetrino();
		if (manager.gameOverCheack(nowpos)) {
			GameBasicFrame.time.stop();
			return false;
		}
		manager.setNowTetrino(null);
		int clearline = manager.lineCheack(nowpos);
		if (clearline > 0) {
			manager.lineClear(clearline, nowpos);
		}
		return true;
	}
}
