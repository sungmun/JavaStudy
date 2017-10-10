package View.Multe;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Control.UserListModel;
import Serversynchronization.User;

@SuppressWarnings("serial")
public class ListView extends JPanel {
	String header[] = { "번호", "이름", "ID" };
	String data[][];

	UserListModel model;

	JTable table;
	JScrollPane listJs;

	private static ListView listview = null;

	private ListView() {
		model = new UserListModel(header, 0);
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

	public Object getData() {
		return model.getData(table.getSelectedRow());
	}

	private void listInit() {
		model.initList();
	}

	public void listReFresh() {
		model.setRowCount(0);
		this.listInit();
	}

	public void listAdd(User user) {
		
	}

}
