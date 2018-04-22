package Model;

import javax.swing.JOptionPane;

import Control.FrameControl;
import Control.MVC_Connect;
import Serversynchronization.MessageType;
import Serversynchronization.TotalJsonObject;
import Serversynchronization.User;
import ValueObject.Space;
import View.Multe.LoginFrame;

public class ClientMessage {
	TetrisClient client;
	private static ClientMessage clientMsgInstanse;
	final String MessageTypeKey = MessageType.class.getSimpleName();

	private ClientMessage() {	}

	public static ClientMessage getClientMessageInstanse() {
		if (clientMsgInstanse == null) {
			clientMsgInstanse = new ClientMessage();
		}
		return clientMsgInstanse;
	}

	public void levelMessageSendEvent(int level) {
		TotalJsonObject levelMessage = new TotalJsonObject();
		levelMessage.addProperty("Level", level);
		levelMessage.addProperty(MessageType.class.getSimpleName(), MessageType.LEVEL_MESSAGE.toString());
		client.send(levelMessage.toString());
	}

	public void scoreMessageSendEvent(int score) {
		TotalJsonObject scoreMessage = new TotalJsonObject();
		scoreMessage.addProperty("Level", score);
		scoreMessage.addProperty(MessageType.class.getSimpleName(), MessageType.SCORE_MESSAGE.toString());
		client.send(scoreMessage.toString());
	}

	public void saveBlockMessageSendEvent(TetrinoType saveBlock) {
		TotalJsonObject blockMessage = new TotalJsonObject();
		blockMessage.addProperty(TetrinoType.class.getSimpleName(), saveBlock.name());
		blockMessage.addProperty(MessageType.class.getSimpleName(), MessageType.SAVE_BLOCK_MESSAGE.toString());
		client.send(blockMessage.toString());
	}

	public void nextBlockMessageSendEvent(TetrinoType nextBlock) {
		TotalJsonObject blockMessage = new TotalJsonObject();
		blockMessage.addProperty(TetrinoType.class.getSimpleName(), nextBlock.name());
		blockMessage.addProperty(MessageType.class.getSimpleName(), MessageType.NEXT_BLOCK_MESSAGE.toString());
		client.send(blockMessage.toString());
	}

	public void mapMessageSendEvent(Space[][] realTimeMap) {
		TotalJsonObject mapMessage = new TotalJsonObject();
		mapMessage.addProperty(realTimeMap.getClass().getSimpleName(), TotalJsonObject.GsonConverter(realTimeMap));
		mapMessage.addProperty(MessageType.class.getSimpleName(), MessageType.MAP_MESSAGE.toString());
		client.send(mapMessage.toString());
	}

	public void login(User user) {
		client = TetrisClient.createTetrisClient();

		TotalJsonObject message = new TotalJsonObject();
		message.addProperty(MessageTypeKey, MessageType.LOGIN.toString());
		message.addProperty(user.getClass().getSimpleName(), TotalJsonObject.GsonConverter(user));

		client.send(message.toString());
	}

	public void UserSelecting(Object obj) {
		client.send(obj.toString());
	}

	public void logout(Object obj) {
		client.send(obj.toString());
	}

	public void battleAccept(User user) {

		TotalJsonObject message = new TotalJsonObject();
		message.addProperty(MessageTypeKey, MessageType.BATTLE_ACCEPT.toString());
		message.addProperty(user.getClass().getSimpleName(), TotalJsonObject.GsonConverter(user));
		client.send(message.toString());

		message = new TotalJsonObject();
		message.addProperty(MessageTypeKey, MessageType.BATTLE_START.toString());
		client.send(message.toString());
	}

	public void gameOverEvent(User user) {

		if (client == null || user == null) {
			MVC_Connect.ModelToControl.callEvent(FrameControl.class, (controllerObj) -> {
				int value = ((FrameControl) controllerObj).showOptionDialog(null, "남기시겠습니까", JOptionPane.YES_NO_OPTION,
						JOptionPane.PLAIN_MESSAGE);
				if (value == JOptionPane.OK_OPTION) {
					((FrameControl) controllerObj).FrameOpen(LoginFrame.class);
				}
			});
		} else {
			TotalJsonObject jsonObject = new TotalJsonObject();
			jsonObject.addStringProperty(MessageTypeKey, MessageType.GAMEOVER_MESSAGE);
			jsonObject.addStringProperty(User.class.getName(), TotalJsonObject.GsonConverter(user));
			client.send(jsonObject.toString());
		}
	}
	// @Override
	// public void onEvent(Object event) {
	// TotalJsonObject parser = new TotalJsonObject((String) event);
	//
	// if (parser.get(MessageTypeKey) == null)
	// return;
	// methodCatch(parser);
	// parser.removeValue("sentClass");
	// }
	//
	// public void methodCatch(Object event) {
	// if (event == null)
	// return;
	// TotalJsonObject obj = (TotalJsonObject) event;
	// String messageTypeStr = (String) obj.get(MessageTypeKey);
	// MessageType messageType = MessageType.valueOf(messageTypeStr);
	// if (client == null) {
	// if (messageType == MessageType.LOGIN) {
	// login();
	// } else if (messageType == MessageType.GAMEOVER_MESSAGE) {
	// gameOver();
	// }
	// return;
	// }
	//
	// switch (messageType) {
	// case USER_SELECTING:
	// UserSelecting(obj);
	// break;
	// case BATTLE_ACCEPT:
	// battleAccept();
	// break;
	// case LOGOUT:
	// logout(obj);
	// break;
	// case RANK:
	//
	// case USER_MESSAGE:
	// case MAP_MESSAGE:
	//
	// case NEXT_BLOCK_MESSAGE:
	// case SAVE_BLOCK_MESSAGE:
	// case LEVEL_MESSAGE:
	// case SCORE_MESSAGE:
	// client.send(obj.toString());
	// default:
	// break;
	// }
	// }

}
