package View;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;

import Control.TetrisControlManager;
import Model.MoveType;

public class KeyBoardEvent extends KeyAdapter implements KeyListener,MoveType{
	TetrisControlManager manager=TetrisControlManager.createTetrisControlManager();
}
