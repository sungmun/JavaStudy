package Control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.json.simple.JSONObject;

import Client.ClientMessage;
import Model.MoveType;
import Model.TetrisManager;
import Model.UserTetrisManager;
import ValueObject.Point;
import View.GameBasicFrame;

public class KeyBoardEvent extends KeyAdapter implements KeyListener {
	ImagePrint mainprint;
	public KeyBoardEvent(ImagePrint mainprint) {
		this.mainprint=mainprint;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		JSONObject moveMessage = new JSONObject();
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_RIGHT:
			moveMessage.put("method", "TetrinoBlockMove");
			moveMessage.put("MoveType", MoveType.RIGHT);
			MVC_Connect.ControlToModel.callEvent(TetrisManager.class.getClass(), moveMessage);
			break;
		case KeyEvent.VK_LEFT:
			moveMessage.put("method", "TetrinoBlockMove");
			moveMessage.put("MoveType", MoveType.LEFT);
			MVC_Connect.ControlToModel.callEvent(TetrisManager.class.getClass(), moveMessage);
			break;
		case KeyEvent.VK_UP:
			moveMessage.put("method", "TetrinoBlockMove");
			moveMessage.put("MoveType", MoveType.TURN);
			MVC_Connect.ControlToModel.callEvent(TetrisManager.class.getClass(), moveMessage);
			break;
		case KeyEvent.VK_DOWN:
			moveMessage.put("method", "TetrinoBlockMove");
			moveMessage.put("MoveType", MoveType.DOWN);
			MVC_Connect.ControlToModel.callEvent(TetrisManager.class.getClass(), moveMessage);
//			if(!manager.TetrinoBlockMove(DOWN)) {
//				cheack();
//			}
			break;
		case KeyEvent.VK_SPACE:
			moveMessage.put("method", "TetrinoBlockDropMove");
			moveMessage.put("MoveType", MoveType.DROP);
			MVC_Connect.ControlToModel.callEvent(TetrisManager.class.getClass(), moveMessage);
//			boolean success = true;
//			while (success) {
//				success = manager.TetrinoBlockMove(DOWN);
//			}
//			cheack();
			break;
		case KeyEvent.VK_Z:
			moveMessage.put("method", "saveBlock");
			MVC_Connect.ControlToModel.callEvent(TetrisManager.class.getClass(), moveMessage);
			break;
		}
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
