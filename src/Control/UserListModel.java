package Control;
import javax.swing.table.DefaultTableModel;

import Serversynchronization.User;
import Serversynchronization.UsersList;

@SuppressWarnings("serial")
public class UserListModel extends DefaultTableModel {
	public UserListModel(String[] header, int date) {
		super(header, date);

	}

	public User getData(int row) {
		Integer num = new Integer((String) getValueAt(row, 0));
		String name = (String) getValueAt(row, 1);
		String id = (String) getValueAt(row, 2);
		return new User(id, name, num);
	}

	public void initList() {
		String[] str;

		for (User user : UsersList.getList()) {
			str = new String[3];
			if(UserControl.users.getPlayer().getUserNumber()==user.getUserNumber()) {
				continue;
			}
			str[0] = user.getUserNumber().toString();
			str[1] = user.getName();
			str[2] = user.getID();
			addRow(str);
		}
	}

	public void adddata(Object data) {
		if (data instanceof User) {
			String[] str = new String[3];
			User user = (User) data;

			str[0] = user.getUserNumber().toString();
			str[1] = user.getName();
			str[2] = user.getID();
			addRow(str);
		}
	}
}