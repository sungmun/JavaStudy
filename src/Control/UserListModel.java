package Control;

import java.util.UUID;

import javax.swing.table.DefaultTableModel;

import Serversynchronization.TotalJsonObject;
import Serversynchronization.User;
import Serversynchronization.UsersList;

@SuppressWarnings("serial")
public class UserListModel extends DefaultTableModel implements EventListener {
	TotalJsonObject json;
	static int selectedRow = -1;
	static UserListModel model = null;

	public UserListModel(String[] header, int date) {
		super(header, date);
		MVC_Connect.ModelToControl.addListener(this);
		MVC_Connect.ViewToControl.addListener(this);
		UserListModel.model = this;
	}

	// User정보 전달 함수
	static User getData() {
		if (selectedRow == -1 || UserListModel.model == null)
			return null;
		UUID num = UUID.fromString(UserListModel.model.getValueAt(selectedRow, 0).toString());
		String name = UserListModel.model.getValueAt(selectedRow, 1).toString();
		String id = UserListModel.model.getValueAt(selectedRow, 2).toString();
		return new User(id, name, num);
	}

	// 초기화 함수
	public void initList() {
		String[] str;

		this.setRowCount(0);
		for (User user : UsersList.list.values()) {
			str = new String[3];
			if (user.equals(UserControl.users.getPlayer())) {
				continue;
			}
			str[0] = user.getUuid().toString();
			str[1] = user.getName();
			str[2] = user.getID();
			addRow(str);
		}
	}

	public void setUsersList(final User[] users) {
		if (users == null) {
			initList();
			return;
		}

		UsersList.list.clear();
		for (User user : users) {
			addData(user);
		}
	}

	// User추가 함수
	public void addData(User user) {
		if (UsersList.list.containsKey((user).getUuid())) {
			return;
		}
		UsersList.list.put(user.getUuid(), user);

		String[] str = new String[3];
		str[0] = user.getUuid().toString();
		str[1] = user.getName();
		str[2] = user.getID();
		addRow(str);
	}

	public void delete(Object data) {
		UsersList.list.remove(data);
		initList();
	}
	@Override
	public void onEvent(CallBackEvent event) {
		event.callBackEvent(this);
	}
//
//	@Override
//	public void onEvent(Object event) {
//		json = new TotalJsonObject(event.toString());
//		String source = null;
//		switch (json.get("method").toString()) {
//		case "addData":
//			source = json.get("User").toString();
//			addData(TotalJsonObject.GsonConverter(source, User.class));
//			break;
//		case "setUsersList":
//			setUsersList(
//					TotalJsonObject.GsonConverter(json.get(User[].class.getSimpleName()).toString(), User[].class));
//			break;
//		case "getData":
//			getData();
//			break;
//		case "delete":
//			source = json.get("User").toString();
//			delete(TotalJsonObject.GsonConverter(source, User.class));
//			break;
//		default:
//			break;
//		}
//	}

}