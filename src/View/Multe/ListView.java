package View.Multe;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.UserControl;
import Serversynchronization.User;
import Serversynchronization.UsersList;

@SuppressWarnings("serial")
public class ListView extends JPanel {
	String header[] = { "번호", "이름", "ID" };
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
		setBounds(5, 5, 400, 490);
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
		int row = table.getSelectedRow();
		Integer num = new Integer((String) model.getValueAt(row, 0));
		String name = (String) model.getValueAt(row, 1);
		String id = (String) model.getValueAt(row, 2);
		return new User(id, name, num);
	}

	public void listInit() {
		String[] str;

		for (User user : UsersList.getList()) {
			str = new String[3];
			if (UserControl.getUserControl().getUser().getUserNumber() == user.getUserNumber()) {
				continue;
			}
			str[0] = user.getUserNumber().toString();
			str[1] = user.getName();
			str[2] = user.getID();
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
