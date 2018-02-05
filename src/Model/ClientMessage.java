package Model;

import javax.swing.JOptionPane;

import Control.EventListener;
import Control.FrameControl;
import Control.MVC_Connect;
import Control.UserControl;
import Serversynchronization.MessageType;
import Serversynchronization.TotalJsonObject;
import Serversynchronization.User;
import View.Multe.LoginFrame;

public class ClientMessage implements EventListener {
	TetrisClient client;
	static ClientMessage message;
	final String MessageTypeKey = MessageType.class.getSimpleName();

	ClientMessage() {
		message = this;
		MVC_Connect.ControlToModel.addListener(this);
	}

	@Override
	public void onEvent(Object event) {
		TotalJsonObject parser = new TotalJsonObject((String) event);

		if (parser.get(MessageTypeKey) == null)
			return;
		methodCatch(parser);
		parser.removeValue("sentClass");
	}

	public void methodCatch(Object event) {
		if (event == null)
			return;
		TotalJsonObject obj = (TotalJsonObject) event;
		String messageTypeStr = (String) obj.get(MessageTypeKey);
		MessageType messageType = MessageType.valueOf(messageTypeStr);
		if (client == null) {
			if (messageType == MessageType.LOGIN) {
				login();
			}else if(messageType == MessageType.GAMEOVER_MESSAGE) {
				gameOver();
			}
			return;
		}

		switch (messageType) {
		case USER_SELECTING:
			UserSelecting(obj);
			break;
		case BATTLE_ACCEPT:
			battleAccept();
			break;
		case LOGOUT:
			logout(obj);
			break;
		case GAMEOVER_MESSAGE:
			gameOver();
			break;
		case RANK:

		case USER_MESSAGE:
		case MAP_MESSAGE:
		case NEXT_BLOCK_MESSAGE:
		case SAVE_BLOCK_MESSAGE:
		case LEVEL_MESSAGE:
		case SCORE_MESSAGE:
			client.send(obj.toString());
		default:
			break;
		}
	}

	private void login() {
		client = TetrisClient.createTetrisClient();

		TotalJsonObject message = new TotalJsonObject();
		message.addProperty(MessageTypeKey, MessageType.LOGIN.toString());
		message.addProperty(UserControl.users.getPlayer().getClass().getSimpleName(),
				TotalJsonObject.GsonConverter(UserControl.users.getPlayer()));

		client.send(message.toString());

	}

	private void UserSelecting(Object obj) {
		TotalJsonObject msg = (TotalJsonObject) obj;
		client.send(msg.toString());
	}

	private void logout(Object obj) {
		TotalJsonObject msg = (TotalJsonObject) obj;
		client.send(msg.toString());
	}

	private void battleAccept() {

		TotalJsonObject message = new TotalJsonObject();
		message.addProperty(MessageTypeKey, MessageType.BATTLE_ACCEPT.toString());
		message.addProperty(User.class.getSimpleName(), TotalJsonObject.GsonConverter(UserControl.users.getPlayer()));
		client.send(message.toString());

		message = new TotalJsonObject();
		message.addProperty(MessageTypeKey, MessageType.BATTLE_START.toString());
		client.send(message.toString());
	}

	private void gameOver() {
		String userStr = TotalJsonObject.GsonConverter(UserControl.users.getPlayer());
		TotalJsonObject jsonObject = new TotalJsonObject();

		jsonObject.addStringProperty(MessageTypeKey, MessageType.GAMEOVER_MESSAGE);

		if (client == null) {
			int value = JOptionPane.showOptionDialog(null, "기록을 남기시겠습니까?", null, JOptionPane.YES_NO_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, null, null);
			if(value==JOptionPane.OK_OPTION) {
				TotalJsonObject frameMessage = new TotalJsonObject();
				frameMessage.addProperty("method", "FrameOpen");
				frameMessage.addProperty("openFrame", LoginFrame.class.getName());
				MVC_Connect.ModelToControl.callEvent(FrameControl.class, frameMessage.toString());
			}
		}
	}
}
