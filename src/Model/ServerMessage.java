package Model;

import javax.swing.JOptionPane;

import Control.FrameControl;
import Control.ImagePrint;
import Control.MVC_Connect;
import Control.UserControl;
import Control.UserListModel;
import Serversynchronization.MessageType;
import Serversynchronization.PlayerInformation;
import Serversynchronization.TotalJsonObject;
import Serversynchronization.User;
import ValueObject.Space;
import View.Multe.ListViewFrame;
import View.Multe.LoginFrame;
import View.Multe.MultiFrame;

public class ServerMessage {
	final static String MessageTypeKey = MessageType.class.getSimpleName();
	final static String SentClass = ServerMessage.class.getName();

	final TetrisClient client = TetrisClient.getTetrisClient();

	public static void onEvent(String event) {
		TotalJsonObject parser = new TotalJsonObject(event);
		System.out.println(parser.get(MessageTypeKey));
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
			logIn(obj.get(User.class.getSimpleName()));
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
			accept();
			break;
		case BATTLE_DENIAL:
			battleDenial();
			break;
		case BATTLE_END:
		case LOGOUT:
			logOut(obj.get(User.class.getSimpleName()));
			break;
		case GAMEOVER_MESSAGE:
			gameOverEvent();
		case RANK:
			rankEvent(msg);
			break;
		case USER_MESSAGE:
			UserMessage(obj.get(User.class.getSimpleName()));
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

	private static void battleDenial() {
		JOptionPane.showMessageDialog(null, null, "상대방이 거부하였습니다", JOptionPane.PLAIN_MESSAGE);
	}

	private static void setMyLogin(Object msg) {
		Integer usernum = Integer.parseInt(msg.toString());
		User user = UserControl.users.getPlayer();
		user.setUserNumber(usernum);
		UserControl.users.setPlayer(user);
	}

	private static void logIn(Object msg) {

		TotalJsonObject userMessage = new TotalJsonObject();
		userMessage.addProperty("method", "addData");
		userMessage.addProperty(User.class.getSimpleName(), msg);

		sendMessage(UserListModel.class, userMessage);
	}

	private static void logOut(Object msg) {

		TotalJsonObject userMessage = new TotalJsonObject();
		userMessage.addProperty("method", "delete");
		userMessage.addProperty(User.class.getSimpleName(), msg);

		sendMessage(UserListModel.class, userMessage);
	}

	private static void userListMessage(Object msg) {

		frameChange(ListViewFrame.class, LoginFrame.class);

		TotalJsonObject userMessage = new TotalJsonObject();
		userMessage.addProperty("method", "setUsersList");
		userMessage.addProperty(User[].class.getSimpleName(), msg);

		sendMessage(UserListModel.class, userMessage);
	}

	private static void UserMessage(Object msg) {// 유저간 대결을 시작할때 유저리스트 창을 배틀 창으로 변경
		User user = TotalJsonObject.GsonConverter(msg.toString(), User.class);
		/*----이때 부터 상대플레이어가 필요하므로 상대플레이어를 유저컨트롤에 상대플레이어 생성----*/
		UserControl.users.setOpplayer(user);
		/*-------------------------------------------------------------------------*/
		frameChange(MultiFrame.class, ListViewFrame.class);

	}

	private static void frameChange(final Class<?> firstFrame, final Class<?> secondFrame) {
		TotalJsonObject frameMessage = new TotalJsonObject();
		frameMessage.addProperty("method", "FrameChange");
		frameMessage.addProperty("firstFrame", firstFrame.getName());
		frameMessage.addProperty("secondFrame", secondFrame.getName());

		sendMessage(FrameControl.class, frameMessage);
	}

	private static void beChoice(Object msg) {
		User user = TotalJsonObject.GsonConverter(msg.toString(), User.class);

		int value = JOptionPane.showOptionDialog(null, user.getName() + "님이 대전을 요청하셨습니다", "대전요청",
				JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

		TotalJsonObject jsonObject = new TotalJsonObject();
		if (value == JOptionPane.YES_OPTION) {
			UserControl.users.setOpplayer(user);
			
			jsonObject.addProperty(MessageTypeKey, MessageType.BATTLE_ACCEPT.toString());
			ClientMessage.message.onEvent(jsonObject.toString());

			accept();
		} else {
			jsonObject.addProperty(MessageTypeKey, MessageType.BATTLE_DENIAL.toString());
			ClientMessage.message.onEvent(jsonObject.toString());
		}

	}

	private static void accept() {
		TotalJsonObject jsonObject = new TotalJsonObject();
		jsonObject.addProperty(MessageTypeKey, MessageType.USER_MESSAGE.toString());
		jsonObject.addProperty(User.class.getSimpleName(),
				TotalJsonObject.GsonConverter(UserControl.users.getPlayer()));
		ClientMessage.message.onEvent(jsonObject.toString());

		frameChange(MultiFrame.class, ListViewFrame.class);

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
		userMessage.addProperty(MessageTypeKey, MessageType.SCORE_MESSAGE.toString());
		userMessage.addProperty("Score", score);

		sendMessage(MVC_Connect.class, userMessage);
	}

	private static void levelEvent(Object msg) {
		int level = (int) msg;
		User user = UserControl.users.getOpplayer();
		PlayerInformation info = user.getInfo();
		info.setLevel(level);
		user.setInfo(info);
		UserControl.users.setOpplayer(user);

		TotalJsonObject userMessage = new TotalJsonObject();
		userMessage.addProperty(MessageTypeKey, MessageType.LEVEL_MESSAGE.toString());
		userMessage.addProperty("Level", level);

		sendMessage(MVC_Connect.class, userMessage);
	}

	private static void gameMessage(Object msg) {
		TotalJsonObject userMessage = new TotalJsonObject(msg.toString());
		sendMessage(ImagePrint.class, userMessage);
	}

	private static void sendMessage(final Class<?> sendclass, TotalJsonObject obj) {
		obj.addProperty("sentClass", SentClass);
		MVC_Connect.ModelToControl.callEvent(sendclass, obj.toString());
	}
}
