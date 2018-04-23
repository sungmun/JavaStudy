package Control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Model.MoveType;
import Model.TetrisManager;
import Serversynchronization.TotalJsonObject;

public class KeyBoardEvent extends KeyAdapter implements KeyListener {
	ImagePrint mainprint;

	@Override
	public void keyPressed(KeyEvent e) {
		TotalJsonObject moveMessage = new TotalJsonObject();
		int key = e.getKeyCode();
		CallBackEvent event = null;
		switch (key) {
		case KeyEvent.VK_RIGHT:
			event = (object) -> ((TetrisManager) object).TetrinoBlockMove(MoveType.RIGHT);
			break;
		case KeyEvent.VK_LEFT:
			event = (object) -> ((TetrisManager) object).TetrinoBlockMove(MoveType.LEFT);
			break;
		case KeyEvent.VK_UP:
			event = (object) -> ((TetrisManager) object).TetrinoBlockMove(MoveType.TURN);
			break;
		case KeyEvent.VK_DOWN:
			event = (object) -> ((TetrisManager) object).TetrinoBlockMove(MoveType.DOWN);
			break;
		case KeyEvent.VK_SPACE:
			event = (object) -> ((TetrisManager) object).TetrinoBlockMove(MoveType.DROP);
			break;
		case KeyEvent.VK_Z:
			event = (object) -> ((TetrisManager) object).saveBlock();
			break;
		default:
			return;
		}
		MVC_Connect.ControlToModel.callEvent(TetrisManager.class, event);
	}

}
