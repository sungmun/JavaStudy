package Model;

import Control.EventListener;
import Control.MVC_Connect;
import Control.TotalJsonObject;
import Control.User;
import Control.UserControl;
import Serversynchronization.MessageType;

public class ClientMessage implements EventListener {
	TetrisClient client;
	static ClientMessage message;

	ClientMessage() {
		message = this;
		MVC_Connect.ControlToModel.addListener(this);
	}

	@Override
	public void onEvent(Object event) {
		System.out.println("ClientMessage.onEvent()");
		System.out.println(event);
		TotalJsonObject parser = new TotalJsonObject((String) event);
		if (parser.get(MessageType.class.getName()) == null)
			return;
		parser.removeValue("sentClass");
		methodCatch(parser);
	}

	@Override
	public void methodCatch(Object event) {
		TotalJsonObject obj = (TotalJsonObject) event;
		String messageTypeStr=(String) obj.get("MessageType");
		MessageType messageType=MessageType.valueOf(messageTypeStr);
		switch (messageType) {
		case LOGIN:
			login(obj.get("User"));
			break;
		case USER_SELECTING:
			UserSelecting(obj);
		case BATTLE_ACCEPT:
			battleAccept();
		case LOGOUT:
			logout(obj);
		case GAMEOVER_MESSAGE:
		case RANK:

		case USER_MESSAGE:
		case MAP_MESSAGE:
		case NEXT_BLOCK_MESSAGE:
		case SAVE_BLOCK_MESSAGE:
		case LEVEL_MESSAGE:
		case SCORE_MESSAGE:
		default:
			break;
		}
	}

	private void login(Object obj) {
		User user = (User) obj;

		client = TetrisClient.createTetrisClient();

		TotalJsonObject message = new TotalJsonObject();
		message.addProperty("MessageType", MessageType.LOGIN.toString());
		message.addProperty("User", message.GsonConverter(user));
		
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
		message.addProperty(MessageType.class.getName(), MessageType.BATTLE_ACCEPT.toString());
		client.send(message.toString());

		message = new TotalJsonObject();
		message.addProperty(MessageType.class.getName(), MessageType.USER_MESSAGE.toString());
		message.addProperty(UserControl.users.getPlayer().getClass().getName(), message.GsonConverter(UserControl.users.getPlayer()));
		client.send(message.toString());

		message = new TotalJsonObject();
		message.addProperty(MessageType.class.getName(), MessageType.BATTLE_START.toString());
		client.send(message.toString());
	}
}
