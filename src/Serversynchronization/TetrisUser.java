package Serversynchronization;

import Control.TetrisControlManager;

public class TetrisUser extends User{
	TetrisControlManager manager;
	public TetrisUser(String id, String name, IP ip,TetrisControlManager manager) {
		super(id, name, ip);
		this.manager=manager;
		// TODO Auto-generated constructor stub
	}
	

}
