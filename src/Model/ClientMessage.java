package Model;

import Control.EventListener;
import Control.MVC_Connect;
import Control.UserControl;
import Serversynchronization.MessageType;
import Serversynchronization.TotalJsonObject;
import Serversynchronization.User;

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
		if(event==null) return;
		TotalJsonObject obj = (TotalJsonObject) event;
		String messageTypeStr = (String) obj.get(MessageTypeKey);
		MessageType messageType = MessageType.valueOf(messageTypeStr);
		if(client==null) {
			if(messageType==MessageType.LOGIN) {
				login();
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
}
