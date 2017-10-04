package Model.ValueObject;

import Model.TetrisControlManager;
import Serversynchronization.User;

public class ControlBasic {
	protected User user;
	protected TetrisControlManager manager=null;
	protected ControlBasic(TetrisControlManager manager) {
		this.manager=manager;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public TetrisControlManager getManager() {
		return manager;
	}
}
