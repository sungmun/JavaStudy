package Serversynchronization;

import java.util.Vector;

import View.Multe.ListView;

public class UsersList {
	private static Vector<User> list = new Vector<User>();

	private static Object key = new Object();

	public static boolean add(User user) {
		synchronized (key) {
			boolean temp=list.add(user);
			ListView.getListview().listReFresh();
			return temp;
		}

	}

	public static void delete(User user) {
		synchronized (key) {
			list.remove(user);
			ListView.getListview().listReFresh();
		}
	}

	public static Vector<User> getList() {
		synchronized (key) {
			return list;
		}
	}

	public static void setList(User[] list) {
		UsersList.list.clear();
		for (User user : list) {
			UsersList.list.addElement(user);
		}
	}

	public static boolean findList(User user) {
		synchronized (key) {
			return (list.indexOf(user)==-1)?false:true;
		}
	}

}
