package View;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Control.TetrisControlManager;
import Model.MoveType;

public class KeyBoardEvent extends KeyAdapter implements KeyListener,MoveType{
	TetrisControlManager manager=TetrisControlManager.createTetrisControlManager();
	TetrinoBlockPanel panel=TetrinoBlockPanel.createTetrinoBlockPanel();
	@Override
	public void keyPressed(KeyEvent e) {
		int key=e.getKeyCode();
		switch(key){
		case KeyEvent.KEY_LOCATION_RIGHT:
			manager.TetrinoBlockMove(RIGHT);
			panel.revalidate();
			panel.repaint();
			break;
		case KeyEvent.KEY_LOCATION_LEFT:
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
