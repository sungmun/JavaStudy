package Model;

import java.awt.image.BufferedImage;
import java.util.UUID;

import javax.swing.JOptionPane;

import Control.FrameControl;
import Control.ImagePrint;
import Control.MVC_Connect;
import Control.MainThread;
import Control.UserControl;
import Control.UserListModel;
import Serversynchronization.MessageType;
import Serversynchronization.TotalJsonObject;
import Serversynchronization.User;
import ValueObject.Space;
import View.LevelPanel;
import View.NextBlockPanel;
import View.SaveBlockPanel;
import View.ScorePanel;
import View.Multe.ListViewFrame;
import View.Multe.LoginFrame;
import View.Multe.MultiFrame;
import View.Multe.PanelForTheOpponent;

public class ServerMessage {
	final static String MessageTypeKey = MessageType.class.getSimpleName();
	final static String SentClass = ServerMessage.class.getName();

	final TetrisClient client = TetrisClient.getTetrisClient();

	public void onEvent(String event) {
		TotalJsonObject parser = new TotalJsonObject(event);
		if (parser.get(MessageTypeKey) == null)
			return;
		eventCatch(parser);
	}

	public void eventCatch(Object msg) {// 메세지 분류 함수
		TotalJsonObject obj = (TotalJsonObject) msg;
		String messageTypeStr = obj.get("MessageType").toString();
		MessageType messageType = MessageType.valueOf(messageTypeStr);
		switch (messageType) {
		case LOGIN:
			logIn((User) TotalJsonObject.GsonConverter(obj.get(User.class.getSimpleName()).toString(), User.class));
			break;
		case USER_SERIAL_NUM:
			setMyLogin(
					(UUID) TotalJsonObject.GsonConverter(obj.get(UUID.class.getSimpleName()).toString(), UUID.class));
			break;
		case USER_LIST_MESSAGE:
			userListMessage((User[]) TotalJsonObject.GsonConverter(obj.get(User[].class.getSimpleName()).toString(),
					User[].class));
			break;
		case BE_CHOSEN:
			beChoice((User) TotalJsonObject.GsonConverter(obj.get(User.class.getSimpleName()).toString(), User.class));
			break;
		case BATTLE_ACCEPT:
			accept(UserControl.users.getPlayer());
			break;
		case BATTLE_DENIAL:
			battleDenial();
			break;
		case BATTLE_END:
		case LOGOUT:
			logOut((User) TotalJsonObject.GsonConverter(obj.get(User.class.getSimpleName()).toString(), User.class));
			break;
		case GAMEOVER_MESSAGE:
			gameOverEvent();
		case RANK:
			rankEvent(obj.toString());
			break;
		case USER_MESSAGE:
			UserMessage(
					(User) TotalJsonObject.GsonConverter(obj.get(User.class.getSimpleName()).toString(), User.class));
			break;
		case MAP_MESSAGE:
			mapMessageSendEvent((Space[][]) TotalJsonObject
					.GsonConverter(obj.get(Space[][].class.getSimpleName()).toString(), Space[][].class));
		case NEXT_BLOCK_MESSAGE:
			nextBlockMessageSendEvent((TetrinoType) TotalJsonObject
					.GsonConverter(obj.get(TetrinoType.class.getSimpleName()).toString(), TetrinoType.class));
		case SAVE_BLOCK_MESSAGE:
			saveBlockMessageSendEvent((TetrinoType) TotalJsonObject
					.GsonConverter(obj.get(TetrinoType.class.getSimpleName()).toString(), TetrinoType.class));
			break;
		case LEVEL_MESSAGE:
			levelEvent(Integer.parseInt(obj.get("Level").toString()));
			break;
		case SCORE_MESSAGE:
			scoreEvent(Integer.parseInt(obj.get("Score").toString()));
			break;
		case USER_SELECTING:
			break;
		case WAITING_ROOM_CONNECT:
			break;
		default:
			break;

		}
	}

	public void battleDenial() {
		JOptionPane.showMessageDialog(null, null, "상대방이 거부하였습니다", JOptionPane.PLAIN_MESSAGE);
	}

	public void setMyLogin(UUID usernum) {

		User user = UserControl.users.getPlayer();
		user.setUuid(usernum);
		MVC_Connect.ModelToControl.callEvent(FrameControl.class, (e) -> {
			((FrameControl) e).showMessageDialog(null, "당신의 고유번호는 " + usernum.toString() + " 입니다");
		});
		TotalJsonObject object = new TotalJsonObject();
		object.addProperty(MessageTypeKey,
				(MainThread.gameflag) ? MessageType.USER_LIST_MESSAGE.toString() : MessageType.RANK.toString());
		ClientMessage.getClientMessageInstanse().client.send(object.toString());
	}

	public void logIn(User user) {
		MVC_Connect.ModelToControl.callEvent(UserListModel.class, (controllerObj) -> {
			((UserListModel) controllerObj).addData(user);
		});
	}

	public void logOut(User user) {
		MVC_Connect.ModelToControl.callEvent(UserListModel.class, (controllerObj) -> {
			((UserListModel) controllerObj).delete(user);
		});
	}

	public void userListMessage(User[] users) {
		MVC_Connect.ModelToControl.callEvent(UserListModel.class, (controllerObj) -> {
			((UserListModel) controllerObj).setUsersList(users);
		});

		MVC_Connect.ModelToControl.callEvent(FrameControl.class, (e) -> {
			((FrameControl) e).FrameChange(ListViewFrame.class, LoginFrame.class);
		});
	}

	public void UserMessage(User user) {// 유저간 대결을 시작할때 유저리스트 창을 배틀 창으로 변경
		/*----이때 부터 상대플레이어가 필요하므로 상대플레이어를 유저컨트롤에 상대플레이어 생성----*/
		UserControl.users.setOpplayer(user);
		/*-------------------------------------------------------------------------*/
		MVC_Connect.ModelToControl.callEvent(FrameControl.class, (e) -> {
			((FrameControl) e).FrameChange(MultiFrame.class, ListViewFrame.class);
		});
	}

	public void beChoice(User user) {
		int value = JOptionPane.showOptionDialog(null, user.getName() + "님이 대전을 요청하셨습니다", "대전요청",
				JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

		TotalJsonObject jsonObject = new TotalJsonObject();
		if (value == JOptionPane.YES_OPTION) {
			UserControl.users.setOpplayer(user);

			jsonObject.addProperty(MessageTypeKey, MessageType.BATTLE_ACCEPT.toString());
			ClientMessage.getClientMessageInstanse().battleAccept(user);

			accept(UserControl.users.getPlayer());
		} else {
			jsonObject.addProperty(MessageTypeKey, MessageType.BATTLE_DENIAL.toString());
			ClientMessage.getClientMessageInstanse().client.send(jsonObject.toString());
		}

	}

	public void accept(User user) {
		TotalJsonObject jsonObject = new TotalJsonObject();
		jsonObject.addProperty(MessageTypeKey, MessageType.USER_MESSAGE.toString());
		jsonObject.addProperty(User.class.getSimpleName(), TotalJsonObject.GsonConverter(user));
		ClientMessage.getClientMessageInstanse().client.send(jsonObject.toString());
		MVC_Connect.ModelToControl.callEvent(FrameControl.class, (e) -> {
			((FrameControl) e).FrameChange(MultiFrame.class, ListViewFrame.class);
		});
	}

	private void gameOverEvent() {

	}

	public void rankEvent(Object msg) {
		TotalJsonObject jsonObject = new TotalJsonObject(msg.toString());
		String rank=jsonObject.get(int.class.getSimpleName()).toString();
		String messageStr= jsonObject.get("RankingType").toString()+"\n 순위 : "+rank;
		MVC_Connect.ModelToControl.callEvent(FrameControl.class, (e)->((FrameControl)e).showMessageDialog(null, messageStr));
	}

	public void scoreEvent(int score) {
		User user = UserControl.users.getOpplayer();
		user.setScore(score);
		UserControl.users.setOpplayer(user);

		MVC_Connect.ModelToControl.callEvent(MVC_Connect.class, (controllerObj) -> {
			MVC_Connect.ControlToView.callEvent(ScorePanel.class, (viewObj) -> {
				ScorePanel panel = (ScorePanel) viewObj;
				if (panel.originClass == PanelForTheOpponent.class) {
					panel.setData(Integer.toString(score));
				}
			});
		});
	}

	public void levelEvent(int level) {
		User user = UserControl.users.getOpplayer();
		user.setLevel(level);
		UserControl.users.setOpplayer(user);

		MVC_Connect.ModelToControl.callEvent(MVC_Connect.class, (controllerObj) -> {
			MVC_Connect.ControlToView.callEvent(LevelPanel.class, (viewObj) -> {
				LevelPanel panel = (LevelPanel) viewObj;
				if (panel.originClass == PanelForTheOpponent.class) {
					panel.setData(Integer.toString(level));
				}
			});
		});
	}

	public void saveBlockMessageSendEvent(TetrinoType saveBlock) {
		MVC_Connect.ModelToControl.callEvent(ImagePrint.class, (controllerObj) -> {
			BufferedImage image = ((ImagePrint) controllerObj).saveBlockPaint(saveBlock);
			MVC_Connect.ControlToView.callEvent(SaveBlockPanel.class, (viewObj) -> {
				SaveBlockPanel panel = ((SaveBlockPanel) viewObj);
				if (panel.originClass == PanelForTheOpponent.class) {
					panel.setData(image);
				}
			});
		});
	}

	public void nextBlockMessageSendEvent(TetrinoType nextBlock) {
		MVC_Connect.ModelToControl.callEvent(ImagePrint.class, (controllerObj) -> {
			BufferedImage image = ((ImagePrint) controllerObj).nextBlockPaint(nextBlock);
			MVC_Connect.ControlToView.callEvent(NextBlockPanel.class, (viewObj) -> {
				NextBlockPanel panel = ((NextBlockPanel) viewObj);
				if (panel.originClass == PanelForTheOpponent.class) {
					panel.setData(image);
				}
			});
		});
	}

	public void mapMessageSendEvent(Space[][] realTimeMap) {
		TotalJsonObject mapMessage = new TotalJsonObject();
		mapMessage.addProperty(realTimeMap.getClass().getSimpleName(), TotalJsonObject.GsonConverter(realTimeMap));
		mapMessage.addProperty(MessageType.class.getSimpleName(), MessageType.MAP_MESSAGE.toString());
		client.send(mapMessage.toString());
	}
}
