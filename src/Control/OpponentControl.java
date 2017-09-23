package Control;

import Serversynchronization.User;

public class OpponentControl {
	private User user;
	private OpponentTetrisControlManager manager;
	public OpponentControl() {
		manager=OpponentTetrisControlManager.createTetrisControlManager();
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public OpponentTetrisControlManager getManager() {
		return manager;
	}
	public void setManager(OpponentTetrisControlManager manager) {
		this.manager = manager;
	}
	
}
