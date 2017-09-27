package View.Multe;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Control.UserControl;
import Serversynchronization.User;
import Serversynchronization.UsersList;

@SuppressWarnings("serial")
public class ListView extends JPanel {
	String header[] = { "번호", "이름", "ID", "IP" };
	String data[][];

	DefaultTableModel model;

	JTable table;
	JScrollPane listJs;

	private static ListView listview = null;

	private ListView() {
		model = new DefaultTableModel(header, 0);
		table = new JTable(model);
		setLayout(new BorderLayout());
		listJs = new JScrollPane(table);
		add(listJs, BorderLayout.CENTER);
		setBounds(5, 5, 200, 200);
		listReFresh();
	}

	public static ListView createListView() {
		if (listview == null) {
			listview = new ListView();
		}
		return listview;
	}

	public static ListView getListview() {
		return listview;
	}

	public User getUser() {
		int row = table.getSelectedColumn();
		String num = (String) model.getValueAt(row, 0);
		String id = (String) model.getValueAt(row, 2);
		return new User(num, id);
	}

	public void listInit() {
		Vector<String> str = new Vector<>();

		for (User user : UsersList.getList()) {
			if(UserControl.getUserControl().getUser().getUserNumber()==user.getUserNumber()) {
				continue;
			}
			str.add(user.getUserNumber().toString());
			str.add(user.getName());
			str.add(user.getID());
			model.addRow(str);
		}
	}

	public void listReFresh() {
		model.setRowCount(0);
		this.listInit();
	}

	public void listAdd(User user) {
		Vector<String> str = new Vector<>();

		str.add(user.getUserNumber().toString());
		str.add(user.getName());
		str.add(user.getID());
		model.addRow(str);
	}

}
