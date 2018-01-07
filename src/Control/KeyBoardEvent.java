package Control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Model.MoveType;
import Model.TetrisManager;

public class KeyBoardEvent extends KeyAdapter implements KeyListener {
	ImagePrint mainprint;
	@Override
	public void keyPressed(KeyEvent e) {
		TotalJsonObject moveMessage = new TotalJsonObject();
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_RIGHT:
			moveMessage.addProperty("method", "TetrinoBlockMove");
			moveMessage.addProperty("MoveType", MoveType.RIGHT.toString());
			MVC_Connect.ControlToModel.callEvent(TetrisManager.class, moveMessage.toString());
			break;
		case KeyEvent.VK_LEFT:
			moveMessage.addProperty("method", "TetrinoBlockMove");
			moveMessage.addProperty("MoveType", MoveType.LEFT.toString());
			MVC_Connect.ControlToModel.callEvent(TetrisManager.class, moveMessage.toString());
			break;
		case KeyEvent.VK_UP:
			moveMessage.addProperty("method", "TetrinoBlockMove");
			moveMessage.addProperty("MoveType", MoveType.TURN.toString());
			MVC_Connect.ControlToModel.callEvent(TetrisManager.class, moveMessage.toString());
			break;
		case KeyEvent.VK_DOWN:
			moveMessage.addProperty("method", "TetrinoBlockMove");
			moveMessage.addProperty("MoveType", MoveType.DOWN.toString());
			MVC_Connect.ControlToModel.callEvent(TetrisManager.class, moveMessage.toString());
			break;
		case KeyEvent.VK_SPACE:
			moveMessage.addProperty("method", "TetrinoBlockDropMove");
			moveMessage.addProperty("MoveType", MoveType.DROP.toString());
			MVC_Connect.ControlToModel.callEvent(TetrisManager.class, moveMessage.toString());
			break;
		case KeyEvent.VK_Z:
			moveMessage.addProperty("method", "saveBlock");
			MVC_Connect.ControlToModel.callEvent(TetrisManager.class, moveMessage.toString());
			break;
		}
	}

}
