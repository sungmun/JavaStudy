package Control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.json.simple.JSONObject;

import Model.MoveType;
import Model.TetrisManager;

public class KeyBoardEvent extends KeyAdapter implements KeyListener {
	ImagePrint mainprint;
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
			break;
		case KeyEvent.VK_SPACE:
			moveMessage.put("method", "TetrinoBlockDropMove");
			moveMessage.put("MoveType", MoveType.DROP);
			MVC_Connect.ControlToModel.callEvent(TetrisManager.class.getClass(), moveMessage);
			break;
		case KeyEvent.VK_Z:
			moveMessage.put("method", "saveBlock");
			MVC_Connect.ControlToModel.callEvent(TetrisManager.class.getClass(), moveMessage);
			break;
		}
	}

}
