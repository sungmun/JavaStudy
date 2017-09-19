package Control;

import Serversynchronization.IP;
import Serversynchronization.User;

public class UserControl {
	private User user;
	private UserTetrisControlManager manager;

	public UserControl() {
		IP ip = new IP();
		user = new User(ip);
		manager = UserTetrisControlManager.createTetrisControlManager();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserTetrisControlManager getManager() {
		return manager;
	}

	public void setManager(UserTetrisControlManager manager) {
		this.manager = manager;
	}
	
}
