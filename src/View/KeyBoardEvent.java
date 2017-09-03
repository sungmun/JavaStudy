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
			manager.TetrinoBlockMove(RIGHT);
			panel.revalidate();
			panel.repaint();
			break;
		case KeyEvent.VK_LEFT:
			manager.TetrinoBlockMove(LEFT);
			panel.revalidate();
			panel.repaint();
			break;
		case KeyEvent.VK_SPACE:
			manager.TetrinoBlockMove(TURN);
			panel.revalidate();
			panel.repaint();
			break;
		}
	}
}
