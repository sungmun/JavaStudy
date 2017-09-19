package Serversynchronization;

public class User {
	private String id;
	private IP ip;
	private String name;
	
	public User(IP ip) {
		this.ip=ip;
	}
	public User(String id, String name) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.name = name;
	}

	public User(String id, String name, IP ip) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.name = name;
		this.ip = ip;
	}

	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}

	public IP getIP() {
		return ip;
	}

	public void setIP(IP ip) {
		this.ip = ip;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
