package ValueObject;

import Model.TetrisManager;
import Serversynchronization.User;

public class ManagerBasic {
	protected User user;
	protected TetrisManager manager=null;
	protected ManagerBasic(TetrisManager manager) {
		this.manager=manager;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public TetrisManager getManager() {
		return manager;
	}
}
