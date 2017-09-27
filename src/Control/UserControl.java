package Control;

import Serversynchronization.User;

public class UserControl {
	private UserTetrisControlManager manager;
	private static UserControl usercontrol = null;
	private User user = null;
	
	public UserControl() {
		manager = UserTetrisControlManager.createTetrisControlManager();
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

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user=user;
	}
	public UserTetrisControlManager getManager() {
		return manager;
	}

	public void setManager(UserTetrisControlManager manager) {
		this.manager = manager;
	}

}
