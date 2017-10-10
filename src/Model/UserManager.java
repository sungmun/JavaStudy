package Model;

import ValueObject.ManagerBasic;

public class UserManager extends ManagerBasic {
	private static UserManager usercontrol = null;

	public UserManager() {
		super(UserTetrisManager.createTetrisManager());
	}

	public static UserManager createUserManager() {
		if (usercontrol == null) {
			usercontrol = new UserManager();
		}
		return usercontrol;
	}

	public static UserManager getUserManager() {
		return usercontrol;
	}

}
