package Control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Model.MoveType;
import Model.TetrisManager;

public class KeyBoardEvent extends KeyAdapter implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		CallBackEvent event = null;
		switch (key) {
		case KeyEvent.VK_RIGHT:
			event = (object) -> ((TetrisManager) object).dropCheck(MoveType.RIGHT);
			break;
		case KeyEvent.VK_LEFT:
			event = (object) -> ((TetrisManager) object).dropCheck(MoveType.LEFT);
			break;
		case KeyEvent.VK_UP:
			event = (object) -> ((TetrisManager) object).dropCheck(MoveType.TURN);
			break;
		case KeyEvent.VK_DOWN:
			event = (object) -> ((TetrisManager) object).dropCheck(MoveType.DOWN);
			break;
		case KeyEvent.VK_SPACE:
			event = (object) -> ((TetrisManager) object).dropCheck(MoveType.DROP);
			break;
		case KeyEvent.VK_Z:
			event = (object) -> ((TetrisManager) object).saveBlock();
			break;
		default:
			return;
		}
		MVC_Connect.Model.callEvent(TetrisManager.class, event);
	}

}
