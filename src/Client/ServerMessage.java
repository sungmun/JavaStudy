package Client;

import javax.swing.JOptionPane;

import org.json.simple.JSONObject;

import Control.FrameControl;
import Control.MVC_Connect;
import Control.ServerConnect;
import Control.UserControl;
import Control.UsersList;
import Model.TetrinoType;
import Serversynchronization.MessageType;
import Serversynchronization.User;
import ValueObject.Space;
import View.SaveBlockPanel;
import View.Multe.ListViewFrame;
import View.Multe.LoginFrame;
import View.Multe.MultiFrame;
import javazoom.jl.decoder.Manager;

public class ServerMessage {
	TetrisClient client = TetrisClient.getTetrisClient();

	public static void eventCatch(JSONObject msg) {// 메세지 분류 함수
		switch ((MessageType) msg.get("MessageType")) {
		case LOGIN:
			logIn(msg.get("User"));
		case USER_SERIAL_NUM:
		case WAITING_ROOM_CONNECT:
		case USER_LIST_MESSAGE:
		case USER_SELECTING:
		case BE_CHOSEN:
		case WAR_ACCEPT:
		case WAR_DENIAL:
		case WAR_START:
		case WAR_END:
		case LOGOUT:
		case GAMEOVER_MESSAGE:
		case RANK:

		case USER_MESSAGE:
		case MAP_MESSAGE:
		case NEXT_BLOCK_MESSAGE:
		case SAVE_BLOCK_MESSAGE:
		case LEVEL_MESSAGE:
		case SCORE_MESSAGE:

		}
	}

	public static void logIn(Object msg) {
		User user = (User) msg;

		JSONObject userMessage = new JSONObject();
		userMessage.put("method", "add");
		userMessage.put("User", user);

		ServerConnect.ServerToControl.callEvent(UsersList.class.getClass(), userMessage);
	}

	public static void logOut(Object msg) {
		User user = (User) msg;

		JSONObject userMessage = new JSONObject();
		userMessage.put("method", "delete");
		userMessage.put("User", user);

		ServerConnect.ServerToControl.callEvent(UsersList.class.getClass(), userMessage);
	}

	public static void userListMessage(Object msg) {
		User[] users = (User[]) msg;

		JSONObject userMessage = new JSONObject();
		userMessage.put("method", "setList");
		userMessage.put("User[]", users);
		
		ServerConnect.ServerToControl.callEvent(UsersList.class.getClass(), userMessage);
		
		JSONObject frameMessage=new JSONObject();
		frameMessage.put("method", "FrameChange");
		frameMessage.put("firstFrame", ListViewFrame.class);
		frameMessage.put("secondFrame", LoginFrame.class);
		ServerConnect.ServerToControl.callEvent(FrameControl.class.getClass(), frameMessage);

	}

	public static void UserMessage(Object msg) {// 유저간 대결을 시작할때 유저리스트 창을 배틀 창으로 변경
		User user=(User)msg;
		JSONObject broadcast=new JSONObject();
		broadcast.put("Class",UserControl.class);
		broadcast.put("User", user);
		
		
		JSONObject frameMessage=new JSONObject();
		frameMessage.put("method", "FrameChange");
		frameMessage.put("firstFrame", MultiFrame.class);
		frameMessage.put("secondFrame", ListViewFrame.class);
		ServerConnect.ServerToControl.callEvent(FrameControl.class.getClass(), frameMessage);
	}

	public static void beChoice(Object msg) {
		User user=(User)msg;
		int val = JOptionPane.showOptionDialog(null, user.getName() + "님이 대전을 요청하셨습니다", "대전 요청",
				JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
		if (val == JOptionPane.YES_OPTION) {
			client.send(new Object(MessageType.WAR_ACCEPT, null));
			warAccept(msg);
		} else {
			client.send(new Object(MessageType.WAR_DENIAL, null));
		}
	}

	public static void warAccept(Object msg) {
		System.out.println("TetrisClient.warAccept()");
		client.send(new Object(MessageType.USER_MESSAGE, usermanager.getUser()));
		client.send(new Object(MessageType.WAR_START, null));
	}

	public static void gameOverEvent() {
	}

	public static void rankEvent(Object msg) {

	}

	public static void scoreEvent(Object socketmsg) {
		ServerToControlConnect.connect.callEvent(Manager.class, socketmsg);
	}

	public static void levelEvent(Object socketmsg) {
		OpponentEvent.getOpponentEvent().managerSetLevel(Object.GSONtoObject(socketmsg.getMessage(), int.class));
	}

	public static void saveBlockEvent(Object socketmsg) {
		OpponentEvent.getOpponentEvent()
				.managerSetSaveBlock(Object.GSONtoObject(socketmsg.getMessage(), TetrinoType.class));
	}

	public static void nextBlockEvent(Object socketmsg) {
		OpponentEvent.getOpponentEvent()
				.managerSetNextBlock(Object.GSONtoObject(socketmsg.getMessage(), TetrinoType.class));
	}

	public static void mapEvent(Object socketmsg) {
		OpponentEvent.getOpponentEvent()
				.managerSetRealtimemap(Object.GSONtoObject(socketmsg.getMessage(), Space[][].class));
	}

	public static void userEvent(Object socketmsg) {
		event.UserMessage(socketmsg);
	}

	public static void userSelectingEvent(Object socketmsg) {

	}

	public static void waitingRoomConnectEvent(Object socketmsg) {
	}

	public static void warEndEvent(Object socketmsg) {
	}
}
