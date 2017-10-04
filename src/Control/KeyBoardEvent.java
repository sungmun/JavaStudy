package Control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Model.MoveType;
import Model.TetrisControlManager;
import Model.ValueObject.Point;

public class KeyBoardEvent extends KeyAdapter implements KeyListener, MoveType {
	TetrisControlManager manager = null;
	public KeyBoardEvent(TetrisControlManager manager) {
		this.manager=manager;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_RIGHT:
			if (manager.getNowTetrino() == null) {
				return;
			}
			manager.TetrinoBlockMove(RIGHT);
			manager.rePaint();
			break;
		case KeyEvent.VK_LEFT:
			if (manager.getNowTetrino() == null) {
				return;
			}
			manager.TetrinoBlockMove(LEFT);
			manager.rePaint();
			break;
		case KeyEvent.VK_UP:
			if (manager.getNowTetrino() == null) {
				return;
			}
			manager.TetrinoBlockMove(TURN);
			manager.rePaint();
			break;
		case KeyEvent.VK_DOWN:
			TicAction.getTic().ticActions();
			break;
		case KeyEvent.VK_SPACE:
			boolean success = true;
			while (success) {
				success = manager.TetrinoBlockMove(DOWN);
			}
			if (!success) {
				Point nowpos = manager.tetrino.getFlowTetrino();
				if (manager.gameOverCheack(nowpos)) {
					manager.getTime().stop();
					return;
				}
				manager.setNowTetrino(null);
				int clearline = manager.lineCheack(nowpos);
				if (clearline > 0) {
					manager.lineClear(clearline, nowpos);
					manager.rePaint();
				}
				manager.createBlock();
				return;
			}
			if (manager.getTime().getDelay() > 1) {
				manager.getTime().setDelay(500 -(int)(Math.pow(1.001, manager.getLevel())));
			}
			manager.rePaint();
			return;
		case KeyEvent.VK_Z: // 현재 테트리스 저장및 불러오기
			manager.saveBlock();
			manager.rePaint();
			break;
		}

	}
}
