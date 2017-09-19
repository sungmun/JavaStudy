package View;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Control.Point;
import Control.TicAction;
import Control.UserTetrisControlManager;
import Model.MoveType;

public class KeyBoardEvent extends KeyAdapter implements KeyListener, MoveType {
	UserTetrisControlManager manager = UserTetrisControlManager.createTetrisControlManager();
	TetrinoBlockPanel panel = TetrinoBlockPanel.getTetrinoBlockPanel();

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_RIGHT:
			if (manager.getNowTetrino() == null) {
				return;
			}
			manager.TetrinoBlockMove(RIGHT);
			MainFrame.getMainviewcopy().blockMoveRePaint();
			break;
		case KeyEvent.VK_LEFT:
			if (manager.getNowTetrino() == null) {
				return;
			}
			manager.TetrinoBlockMove(LEFT);
			MainFrame.getMainviewcopy().blockMoveRePaint();
			break;
		case KeyEvent.VK_UP:
			if (manager.getNowTetrino() == null) {
				return;
			}
			manager.TetrinoBlockMove(TURN);
			MainFrame.getMainviewcopy().blockMoveRePaint();
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
					MainFrame.getTime().stop();
					return;
				}
				manager.setNowTetrino(null);
				int clearline = manager.lineCheack(nowpos);
				if (clearline > 0) {
					manager.lineClear(clearline, nowpos);
					ScorePanel.getScorePanel().setScore(manager.getScore());
					LevelPanel.getLevelPanel().setLevel(manager.getLevel());
				}
				manager.createBlock();
				return;
			}
			if (manager.getLevel() < 10) {
				MainFrame.getTime().setDelay(500 - manager.getLevel() * 35);
			}
			MainFrame.getMainviewcopy().blockMoveRePaint();
			return;
		case KeyEvent.VK_Z: // 현재 테트리스 저장및 불러오기
			manager.saveBlock();
			MainFrame.getMainviewcopy().blockMoveRePaint();
			break;
		}

	}
}
