package Model;

import Model.ValueObject.ControlBasic;

public class UserControl extends ControlBasic {
	private static UserControl usercontrol = null;

	public UserControl() {
		super(UserTetrisControlManager.createTetrisControlManager());
	}

	public static UserControl createUserControl() {
		if (usercontrol == null) {
			usercontrol = new UserControl();
		}
		return usercontrol;
	}

	public static UserControl getUserControl() {
		return usercontrol;
	}

}
