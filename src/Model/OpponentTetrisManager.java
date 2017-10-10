package Model;

import Control.PlayOpponentInformation;

public class OpponentTetrisManager extends TetrisManager {
	private static OpponentTetrisManager tetrismanager = null;

	public OpponentTetrisManager() {
		super();
		info=new PlayOpponentInformation();
	}

	public static OpponentTetrisManager createTetrisManager() {
		if (tetrismanager == null) {
			tetrismanager = new OpponentTetrisManager();
		}
		return tetrismanager;
	}

	public static OpponentTetrisManager getTetrisManager() {
		return tetrismanager;
	}

}
