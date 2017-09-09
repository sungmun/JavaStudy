package View;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Control.Point;
import Control.TetrisControlManager;
import Control.TicAction;
import Model.MoveType;

public class KeyBoardEvent extends KeyAdapter implements KeyListener, MoveType {
	TetrisControlManager manager = TetrisControlManager.createTetrisControlManager();
	TetrinoBlockPanel panel = TetrinoBlockPanel.getTetrinoBlockPanel();

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_RIGHT:
			if(TetrisControlManager.getTetrisControlManager().getNowTetrino()==null) {
				return;
			}
			manager.TetrinoBlockMove(RIGHT);
			MainView.getMainviewcopy().blockMoveRePaint();
			break;
		case KeyEvent.VK_LEFT:
			if(TetrisControlManager.getTetrisControlManager().getNowTetrino()==null) {
				return;
			}
			manager.TetrinoBlockMove(LEFT);
			MainView.getMainviewcopy().blockMoveRePaint();
			break;
		case KeyEvent.VK_UP:
			if(TetrisControlManager.getTetrisControlManager().getNowTetrino()==null) {
				return;
			}
			manager.TetrinoBlockMove(TURN);
			MainView.getMainviewcopy().blockMoveRePaint();
			break;
		case KeyEvent.VK_DOWN:
			TicAction.getTic().ticActions();
			break;
		case KeyEvent.VK_SPACE:
			boolean success=true;
			while (success) {
				success=manager.TetrinoBlockMove(DOWN);
			}
			if(!success) {
				Point nowpos = manager.tetrino.getFlowTetrino();
				if (manager.gameOverCheack(nowpos)) {
					MainView.getTime().stop();
					return;
				}
				manager.setNowTetrino(null);
				int clearline=manager.lineCheack(nowpos);
				if(clearline>0) {
					manager.lineClear(clearline, nowpos);
				}
				manager.createBlock();
				return;
			}
			MainView.getMainviewcopy().blockMoveRePaint();
			return;
		case KeyEvent.VK_Z: //현재 테트리스 저장및 불러오기
			manager.saveBlock();
			MainView.getMainviewcopy().blockMoveRePaint();
			break;
		}
		
			
	}
}
