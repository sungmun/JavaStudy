package View;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Control.TetrisControlManager;
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
		case KeyEvent.VK_SPACE:
			if(TetrisControlManager.getTetrisControlManager().getNowTetrino()==null) {
				return;
			}
			manager.TetrinoBlockMove(TURN);
			MainView.getMainviewcopy().blockMoveRePaint();
			break;
		}
	}
}
