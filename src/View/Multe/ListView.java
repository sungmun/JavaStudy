package View.Multe;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Serversynchronization.User;
import Serversynchronization.UsersList;

@SuppressWarnings("serial")
public class ListView extends JPanel {
	String header[] = { "번호", "이름", "ID", "IP" };
	String data[][];

	DefaultTableModel model;

	JTable table;
	JScrollPane listJs;

	public ListView() {
		model = new DefaultTableModel(header, 0);
		table = new JTable(model);
		setLayout(new BorderLayout());
		listJs = new JScrollPane(table);
		add(listJs, BorderLayout.CENTER);
		setBounds(5, 5, 200, 200);
	}

	public User getUser() {
		int row = table.getSelectedColumn();
		String num = (String) model.getValueAt(row, 0);
		String id = (String) model.getValueAt(row, 2);
		return new User(num, id);
	}

	public void listInit() {
		Vector<User> users = UsersList.getList();
		Vector<String> str = new Vector<>();

		for (User user : users) {
			str.add(user.getUserNumber());
			str.add(user.getName());
			str.add(user.getID());
			str.add(user.getSocket().getInetAddress().toString());
		}
	}
	public void listReFresh(User user) {
		model.setRowCount(0);
		this.listInit();
	}
	public void listAdd(User user) {
		Vector<String> str = new Vector<>();

		str.add(user.getUserNumber());
		str.add(user.getName());
		str.add(user.getID());
		str.add(user.getSocket().getInetAddress().toString());
	}

}
