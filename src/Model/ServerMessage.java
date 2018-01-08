package Model;

import javax.swing.JOptionPane;

import Control.FrameControl;
import Control.MVC_Connect;
import Control.UserControl;
import Serversynchronization.MessageType;
import Serversynchronization.PlayerInformation;
import Serversynchronization.TotalJsonObject;
import Serversynchronization.User;
import Serversynchronization.UsersList;
import ValueObject.Space;
import View.Multe.ListViewFrame;
import View.Multe.LoginFrame;
import View.Multe.MultiFrame;

public class ServerMessage {
	TetrisClient client = TetrisClient.getTetrisClient();
	final static String MessageTypeKey = MessageType.class.getSimpleName();
	final static String SentClass = ServerMessage.class.getSimpleName();
	public static void onEvent(String event) {
		TotalJsonObject parser = new TotalJsonObject(event);
		if (parser.get(MessageTypeKey) == null)
			return;
		eventCatch(parser);
	}

	public static void eventCatch(Object msg) {// 메세지 분류 함수
		TotalJsonObject obj = (TotalJsonObject) msg;
		String messageTypeStr = obj.get("MessageType").toString();
		MessageType messageType = MessageType.valueOf(messageTypeStr);
		switch (messageType) {
		case LOGIN:
			logIn(obj.get("User"));
			break;
		case USER_SERIAL_NUM:
			setMyLogin(obj.get(Integer.class.getSimpleName()));
			break;
		case USER_LIST_MESSAGE:
			userListMessage(obj.get(User[].class.getSimpleName()));
			break;
		case BE_CHOSEN:
			beChoice(obj.get(User.class.getSimpleName()));
			break;
		case BATTLE_ACCEPT:
			warAccept();
			break;
		case BATTLE_DENIAL:
			warDenial();
			break;
		case BATTLE_END:
		case LOGOUT:
			logOut(obj.get(User.class.getName()));
			break;
		case GAMEOVER_MESSAGE:
			gameOverEvent();
		case RANK:
			rankEvent(msg);
			break;
		case USER_MESSAGE:
			UserMessage(User.class.getName());
			break;

		case MAP_MESSAGE:
		case NEXT_BLOCK_MESSAGE:
		case SAVE_BLOCK_MESSAGE:
			gameMessage(obj);
			break;
		case LEVEL_MESSAGE:
			levelEvent(msg);
			break;
		case SCORE_MESSAGE:
			scoreEvent(msg);
			break;
		case USER_SELECTING:
			break;
		case WAITING_ROOM_CONNECT:
			break;
		default:
			break;

		}
	}

	private static void warDenial() {
		TotalJsonObject msg = new TotalJsonObject();

		msg.addProperty("method", "showMessageDialog");
		msg.addProperty("title", "null");
		msg.addProperty("content", "상대방이 거부하였습니다");

		MVC_Connect.ModelToControl.callEvent(FrameControl.class, msg.getAsString());
	}

	private static void setMyLogin(Object msg) {
		Integer usernum = Integer.parseInt(msg.toString());
		User user = UserControl.users.getPlayer();
		user.setUserNumber(usernum);
		UserControl.users.setPlayer(user);
	}

	private static void logIn(Object msg) {

		TotalJsonObject userMessage = new TotalJsonObject();
		userMessage.addProperty("method", "add");
		userMessage.addProperty(User.class.getSimpleName(), msg);
		userMessage.addProperty("sentClass", SentClass);
		
		MVC_Connect.ModelToControl.callEvent(UsersList.class, userMessage.getAsString());
	}

	private static void logOut(Object msg) {

		TotalJsonObject userMessage = new TotalJsonObject();
		userMessage.addProperty("method", "delete");
		userMessage.addProperty(User.class.getSimpleName(), msg);
		userMessage.addProperty("sentClass", SentClass);

		MVC_Connect.ModelToControl.callEvent(UsersList.class, userMessage.getAsString());
	}

	private static void userListMessage(Object msg) {

		TotalJsonObject userMessage = new TotalJsonObject();
		userMessage.addProperty("method", "setList");
		userMessage.addProperty(User[].class.getSimpleName(), msg);
		userMessage.addProperty("sentClass", SentClass);

		MVC_Connect.ModelToControl.callEvent(UsersList.class, userMessage.getAsString());

		TotalJsonObject frameMessage = new TotalJsonObject();
		frameMessage.addProperty("method", "FrameChange");
		frameMessage.addProperty("firstFrame", ListViewFrame.class.getName());
		frameMessage.addProperty("secondFrame", LoginFrame.class.getName());
		
		MVC_Connect.ModelToControl.callEvent(FrameControl.class, frameMessage.getAsString());

	}

	private static void UserMessage(Object msg) {// 유저간 대결을 시작할때 유저리스트 창을 배틀 창으로 변경
		User user = TotalJsonObject.GsonConverter(msg.toString(), User.class);
		/*----이때 부터 상대플레이어가 필요하므로 상대플레이어를 유저컨트롤에 상대플레이어 생성----*/
		UserControl.users.setOpplayer(user);
		/*-------------------------------------------------------------------------*/

		TotalJsonObject frameMessage = new TotalJsonObject();
		frameMessage.addProperty("method", "FrameChange");
		frameMessage.addProperty("firstFrame", MultiFrame.class.getName());
		frameMessage.addProperty("secondFrame", ListViewFrame.class.getName());
		frameMessage.addProperty("sentClass", SentClass);
		
		MVC_Connect.ModelToControl.callEvent(FrameControl.class, frameMessage.getAsString());
	}

	private static void beChoice(Object msg) {
		User user = TotalJsonObject.GsonConverter(msg.toString(), User.class);

		TotalJsonObject sendMsg = new TotalJsonObject();
		sendMsg.addProperty("method", "showOptionDialog");
		sendMsg.addProperty("title", "대전 요청");
		sendMsg.addProperty("content", user.getName() + "님이 대전을 요청하셨습니다");
		sendMsg.addProperty("JOptionPaneType", JOptionPane.YES_NO_OPTION);
		sendMsg.addProperty("JOptionPaneStyle", JOptionPane.PLAIN_MESSAGE);
		sendMsg.addProperty("sentClass", SentClass);

		MVC_Connect.ModelToControl.callEvent(FrameControl.class, sendMsg.getAsString());
	}

	public static void warAccept() {

	}

	private static void gameOverEvent() {
	}

	private static void rankEvent(Object msg) {

	}

	private static void scoreEvent(Object msg) {
		
		int score = Integer.parseInt(msg.toString());
		User user = UserControl.users.getOpplayer();
		PlayerInformation info = user.getInfo();
		info.setScore(score);
		user.setInfo(info);
		UserControl.users.setOpplayer(user);
		
		TotalJsonObject userMessage = new TotalJsonObject();
		userMessage.addProperty(MessageTypeKey, MessageType.SCORE_MESSAGE);
		userMessage.addProperty("Score", score);
		userMessage.addProperty("sentClass", SentClass);
		
		MVC_Connect.ModelToControl.callEvent(MVC_Connect.class, userMessage);
	}

	private static void levelEvent(Object msg) {
		int level = (int) msg;
		User user = UserControl.users.getOpplayer();
		PlayerInformation info = user.getInfo();
		info.setLevel(level);
		user.setInfo(info);
		UserControl.users.setOpplayer(user);
		
		TotalJsonObject userMessage = new TotalJsonObject();
		userMessage.addProperty(MessageTypeKey, MessageType.LEVEL_MESSAGE);
		userMessage.addProperty("Level", level);
		userMessage.addProperty("sentClass", SentClass);
		
		MVC_Connect.ModelToControl.callEvent(MVC_Connect.class, userMessage);
	}

	private static void gameMessage(Object msg) {
		TotalJsonObject userMessage = new TotalJsonObject();
		userMessage.addProperty(MessageTypeKey, MessageType.MAP_MESSAGE);
		userMessage.addProperty("sentClass", SentClass);
		userMessage.addProperty(Space[][].class.getSimpleName()	, msg);
		MVC_Connect.ModelToControl.quickCallEvent(MVC_Connect.class, userMessage);
	}
}
