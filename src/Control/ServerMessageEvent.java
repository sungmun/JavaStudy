package Control;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.google.gson.Gson;

import Client.TetrisClient;
import Model.OpponentManager;
import Model.UserManager;
import Serversynchronization.MessageType;
import Serversynchronization.SocketMessage;
import Serversynchronization.User;
import Serversynchronization.UsersList;
import View.Multe.ListViewFrame;
import View.Multe.LoginFrame;
import View.Multe.MultiFrame;

public class ServerMessageEvent implements MessageType {
	public void logIn(SocketMessage msg) {
		UsersList.add(transObject(msg.getMessage(), User.class));
	}

	public void logOut(SocketMessage msg) {
		UsersList.delete(transObject(msg.getMessage(), User.class));
	}

	public void userListMessage(SocketMessage msg) {
		UsersList.setList(transObject(msg.getMessage(), User[].class));
		JFrame fr = ListViewFrame.createListViewFrame();
		fr.setVisible(true);
		LoginFrame.getLoginFrame().dispose();
	}

	public void UserMessage(SocketMessage msg) {
		OpponentManager opmanager = OpponentManager.createOpponentManager();
		UserManager usermanager = UserManager.createUserManager();
		opmanager.setUser(transObject(msg.getMessage(), User.class));
		JFrame fr1 = MultiFrame.createMulteFrame(usermanager.getUser(), opmanager.getUser());
		JFrame fr2 = ListViewFrame.createListViewFrame();
		if (fr1 != null || fr2 == null) {
			fr1.setVisible(true);
			fr2.dispose();
		}
	}

	public void beChoice(SocketMessage msg) {
		User player = transObject(msg.getMessage(), User.class);
		int val = JOptionPane.showOptionDialog(null, player.getName() + "님이 대전을 요청하셨습니다", "대전 요청",
				JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
		if (val == JOptionPane.YES_OPTION) {
			TetrisClient.getTetrisClient().send(new SocketMessage(WAR_ACCEPT, null));
			warAccept(msg);
		} else {
			TetrisClient.getTetrisClient().send(new SocketMessage(WAR_DENIAL, null));
		}
	}

	public void warAccept(SocketMessage msg) {
		System.out.println("TetrisClient.warAccept()");
		TetrisClient.getTetrisClient().send(new SocketMessage(USER_MESSAGE, UserManager.getUserManager().getUser()));
		TetrisClient.getTetrisClient().send(new SocketMessage(WAR_START, null));
	}

	public <T> T transObject(String msg, Class<T> cla) {
		return new Gson().fromJson(msg, cla);
	}

	public void gameOverEvent() {
		OpponentEvent.getOpponentEvent().gameOver();
	}

	public void rankEvent(SocketMessage msg) {

	}
}
