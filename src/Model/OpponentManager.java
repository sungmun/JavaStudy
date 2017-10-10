package Model;

import ValueObject.ManagerBasic;

public class OpponentManager extends ManagerBasic {

	private static OpponentManager control = null;

	private OpponentManager() {
		super(OpponentTetrisManager.createTetrisManager());
	}

	public static OpponentManager createOpponentManager() {
		if (control == null) {
			control = new OpponentManager();
		}
		return control;
	}

	public static OpponentManager getOpponentManager() {
		return control;
	}

}
