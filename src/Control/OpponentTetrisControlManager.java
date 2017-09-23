package Control;

public class OpponentTetrisControlManager extends TetrisControlManager {
	private static OpponentTetrisControlManager tetrismanager = null;

	public OpponentTetrisControlManager() {
		super();
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
	public static void setTetrisControlManager(OpponentTetrisControlManager tetrismanager) {
		OpponentTetrisControlManager.tetrismanager=tetrismanager;
	}
}
