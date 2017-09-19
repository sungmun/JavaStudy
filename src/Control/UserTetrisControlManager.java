package Control;

public class UserTetrisControlManager extends TetrisControlManager{
	private static UserTetrisControlManager tetrismanager = null;
	public UserTetrisControlManager() {
		super();
	}
	public static UserTetrisControlManager createTetrisControlManager() {
		if (tetrismanager == null) {
			tetrismanager = new UserTetrisControlManager();
		}
		return tetrismanager;
	}
	public static UserTetrisControlManager getTetrisControlManager() {
		return tetrismanager;
	}
}
