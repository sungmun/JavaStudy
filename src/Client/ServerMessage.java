package Client;

import javax.swing.JOptionPane;

import Control.FrameControl;
import Control.ServerToControlConnect;
import Model.OpponentManager;
import Model.TetrinoType;
import Model.UserManager;
import Serversynchronization.MessageType;
import Serversynchronization.SocketMessage;
import Serversynchronization.User;
import Serversynchronization.UsersList;
import ValueObject.Space;
import View.Multe.ListViewFrame;
import View.Multe.LoginFrame;
import View.Multe.MultiFrame;
import javazoom.jl.decoder.Manager;

public class ServerMessage {
	TetrisClient client = TetrisClient.getTetrisClient();

	public void getEvent(SocketMessage msg) {//메세지 분류 함수
		
	}
	
	public void logIn(SocketMessage msg) {
		User user = SocketMessage.GSONtoObject(msg.getMessage(), User.class);
		UsersList.add(user);
	}

	public void logOut(SocketMessage msg) {
		User user = SocketMessage.GSONtoObject(msg.getMessage(), User.class);
		UsersList.delete(user);
	}

	public void userListMessage(SocketMessage msg) {
		User[] list = SocketMessage.GSONtoObject(msg.getMessage(), User[].class);
		UsersList.setList(list);
		FrameControl.FrameChange(ListViewFrame.createListViewFrame(), LoginFrame.getLoginFrame());
	}

	public void UserMessage(SocketMessage msg) {// 유저간 대결을 시작할때 유저리스트 창을 배틀 창으로 변경
		ServerToControlConnect.connect.callEvent(caller, event);
		opmanager.setUser(SocketMessage.GSONtoObject(msg.getMessage(), User.class));
		FrameControl.FrameChange(MultiFrame.createMulteFrame(usermanager.getUser(), opmanager.getUser()),
				ListViewFrame.createListViewFrame());
	}

	public void beChoice(SocketMessage msg) {
		User player = SocketMessage.GSONtoObject(msg.getMessage(), User.class);
		int val = JOptionPane.showOptionDialog(null, player.getName() + "님이 대전을 요청하셨습니다", "대전 요청",
				JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
		if (val == JOptionPane.YES_OPTION) {
			client.send(new SocketMessage(MessageType.WAR_ACCEPT, null));
			warAccept(msg);
		} else {
			client.send(new SocketMessage(MessageType.WAR_DENIAL, null));
		}
	}

	public void warAccept(SocketMessage msg) {
		System.out.println("TetrisClient.warAccept()");
		client.send(new SocketMessage(MessageType.USER_MESSAGE, usermanager.getUser()));
		client.send(new SocketMessage(MessageType.WAR_START, null));
	}

	public void gameOverEvent() {
	}

	public void rankEvent(SocketMessage msg) {

	}

	public void scoreEvent(SocketMessage socketmsg) {
		ServerToControlConnect.connect.callEvent(Manager.class, socketmsg);
	}

	public void levelEvent(SocketMessage socketmsg) {
		OpponentEvent.getOpponentEvent().managerSetLevel(SocketMessage.GSONtoObject(socketmsg.getMessage(), int.class));
	}

	public void saveBlockEvent(SocketMessage socketmsg) {
		OpponentEvent.getOpponentEvent()
				.managerSetSaveBlock(SocketMessage.GSONtoObject(socketmsg.getMessage(), TetrinoType.class));
	}

	public void nextBlockEvent(SocketMessage socketmsg) {
		OpponentEvent.getOpponentEvent()
				.managerSetNextBlock(SocketMessage.GSONtoObject(socketmsg.getMessage(), TetrinoType.class));
	}

	public void mapEvent(SocketMessage socketmsg) {
		OpponentEvent.getOpponentEvent()
				.managerSetRealtimemap(SocketMessage.GSONtoObject(socketmsg.getMessage(), Space[][].class));
	}

	public void userEvent(SocketMessage socketmsg) {
		event.UserMessage(socketmsg);
	}

	public void userSelectingEvent(SocketMessage socketmsg) {

	}

	public void waitingRoomConnectEvent(SocketMessage socketmsg) {
	}

	public void warEndEvent(SocketMessage socketmsg) {
	}
}
