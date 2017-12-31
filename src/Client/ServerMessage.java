package Client;

import javax.swing.JOptionPane;

import org.json.simple.JSONObject;

import Control.FrameControl;
import Control.ServerConnect;
import Control.User;
import Control.UserControl;
import Control.UsersList;
import Model.TetrinoType;
import Serversynchronization.MessageType;
import Serversynchronization.PlayerInformation;
import View.Multe.ListViewFrame;
import View.Multe.LoginFrame;
import View.Multe.MultiFrame;

public class ServerMessage {
	TetrisClient client = TetrisClient.getTetrisClient();

	public static void eventCatch(JSONObject msg) {// 메세지 분류 함수
		switch ((MessageType) msg.get("MessageType")) {
		case LOGIN:
			logIn(msg.get("User"));
			break;
		case USER_SERIAL_NUM:
			setMyLogin(msg.get("Integer"));
		case USER_LIST_MESSAGE:
			userListMessage(msg.get("User[]"));
		case BE_CHOSEN:
			beChoice(msg.get("User"));
			break;
		case BATTLE_ACCEPT:
			warAccept();
			break;
		case BATTLE_DENIAL:
			warDenial();
			break;
		case BATTLE_END:
		case LOGOUT:
			logOut(msg.get("User"));
			break;
		case GAMEOVER_MESSAGE:
		case RANK:

		case USER_MESSAGE:
			UserMessage(msg.get("User"));
			break;
			
		case MAP_MESSAGE:
		case NEXT_BLOCK_MESSAGE:
		case SAVE_BLOCK_MESSAGE:
			gameMessage(msg);
			break;
		case LEVEL_MESSAGE:
		case SCORE_MESSAGE:
			
		case USER_SELECTING:
			break;
		case WAITING_ROOM_CONNECT:
			break;
		default:
			break;

		}
	}

	private static void warDenial() {
		JSONObject msg = new JSONObject();

		msg.put("method", "showMessageDialog");
		msg.put("title", null);
		msg.put("content", "상대방이 거부하였습니다");
		ServerConnect.ServerToControl.callEvent(FrameControl.class.getClass(), msg);
	}

	private static void setMyLogin(Object msg) {
		Integer usernum = (Integer) msg;
		User user = UserControl.users.getPlayer();
		user.setUserNumber(usernum);
		UserControl.users.setPlayer(user);
	}

	private static void logIn(Object msg) {
		User user = (User) msg;

		JSONObject userMessage = new JSONObject();
		userMessage.put("method", "add");
		userMessage.put("User", user);

		ServerConnect.ServerToControl.callEvent(UsersList.class.getClass(), userMessage);
	}

	private static void logOut(Object msg) {
		User user = (User) msg;

		JSONObject userMessage = new JSONObject();
		userMessage.put("method", "delete");
		userMessage.put("User", user);

		ServerConnect.ServerToControl.callEvent(UsersList.class.getClass(), userMessage);
	}

	private static void userListMessage(Object msg) {
		User[] users = (User[]) msg;

		JSONObject userMessage = new JSONObject();
		userMessage.put("method", "setList");
		userMessage.put("User[]", users);

		ServerConnect.ServerToControl.callEvent(UsersList.class.getClass(), userMessage);

		JSONObject frameMessage = new JSONObject();
		frameMessage.put("method", "FrameChange");
		frameMessage.put("firstFrame", ListViewFrame.class);
		frameMessage.put("secondFrame", LoginFrame.class);
		ServerConnect.ServerToControl.callEvent(FrameControl.class.getClass(), frameMessage);

	}

	private static void UserMessage(Object msg) {// 유저간 대결을 시작할때 유저리스트 창을 배틀 창으로 변경
		User user = (User) msg;
		/*----이때 부터 상대플레이어가 필요하므로 상대플레이어를 유저컨트롤에 상대플레이어 생성----*/
		UserControl.users.setOpplayer(user);
		/*-------------------------------------------------------------------------*/

		JSONObject frameMessage = new JSONObject();
		frameMessage.put("method", "FrameChange");
		frameMessage.put("firstFrame", MultiFrame.class);
		frameMessage.put("secondFrame", ListViewFrame.class);
		ServerConnect.ServerToControl.callEvent(FrameControl.class.getClass(), frameMessage);
	}

	private static void beChoice(Object msg) {
		User user = (User) msg;
		
		JSONObject sendMsg = new JSONObject();
		sendMsg.put("method", "showOptionDialog");
		sendMsg.put("title", "대전 요청");
		sendMsg.put("content", user.getName() + "님이 대전을 요청하셨습니다");
		sendMsg.put("JOptionPaneType", JOptionPane.YES_NO_OPTION);
		sendMsg.put("JOptionPaneStyle", JOptionPane.PLAIN_MESSAGE);

		ServerConnect.ServerToControl.callEvent(FrameControl.class.getClass(), sendMsg);
	}

	public static void warAccept() {
		
	}

	private static void gameOverEvent() {
	}

	private static void rankEvent(Object msg) {

	}

	private static void scoreEvent(Object msg) {
		int score = (int) msg;
		User user = UserControl.users.getOpplayer();
		PlayerInformation info = user.getInfo();
		info.setScore(score);
		user.setInfo(info);
		UserControl.users.setOpplayer(user);
		ServerConnect.ServerToControl.quickCallEvent(ServerConnect.class, MessageType.SCORE_MESSAGE,score);
	}

	private static void levelEvent(Object msg) {
		int level = (int) msg;
		User user = UserControl.users.getOpplayer();
		PlayerInformation info = user.getInfo();
		info.setLevel(level);
		user.setInfo(info);
		UserControl.users.setOpplayer(user);
		ServerConnect.ServerToControl.quickCallEvent(ServerConnect.class, MessageType.LEVEL_MESSAGE,level);
	}

	private static void gameMessage(Object msg) {
		ServerConnect.ServerToControl.quickCallEvent(ServerConnect.class,msg);
	}
}
