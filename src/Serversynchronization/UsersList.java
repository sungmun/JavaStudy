package Serversynchronization;

import java.util.Vector;

import View.Multe.ListView;

public class UsersList {
	private static Vector<User> list = new Vector<User>();

	private static Object key = new Object();

	public static boolean add(Object data) {
		boolean temp = list.add((User) data);
		ListView.getListview().listReFresh();
		System.gc();
		return temp;
	}

	public static void delete(Object data) {
		synchronized (key) {
			list.remove(data);
			ListView.getListview().listReFresh();
		}
	}

	public static Vector<User> getList() {
		synchronized (key) {
			return list;
		}
	}

	public static void setList(Object[] list) {
		UsersList.list.clear();
		for (Object user : list) {
			UsersList.list.addElement((User) user);
		}
	}

	public static boolean findList(Object user) {
		synchronized (key) {
			return (list.indexOf(user) == -1) ? false : true;
		}
	}

}
