package Model;

import Control.PlayOpponentInformation;

public class OpponentTetrisControlManager extends TetrisControlManager {
	private static OpponentTetrisControlManager tetrismanager = null;

	public OpponentTetrisControlManager() {
		super();
		info=new PlayOpponentInformation();
	}

	public static OpponentTetrisControlManager createTetrisControlManager() {
		if (tetrismanager == null) {
			tetrismanager = new OpponentTetrisControlManager();
		}
		return tetrismanager;
	}

	public static OpponentTetrisControlManager getTetrisControlManager() {
		return tetrismanager;
	}

}
