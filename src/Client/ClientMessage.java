package Client;

import org.json.simple.JSONObject;

import Control.EventListener;
import Control.ServerConnect;
import Control.User;
import Control.UserControl;
import Serversynchronization.MessageType;

public class ClientMessage implements EventListener {
	TetrisClient client;

	public ClientMessage() {
		ServerConnect.ControlToServer.addListener(this);
	}

	@Override
	public void onEvent(JSONObject event) {
		System.out.println("ClientMessage.onEvent()");
		System.out.println(event.toJSONString());
		methodCatch(event);
	}

	@Override
	public void methodCatch(JSONObject event) {
		switch ((MessageType) event.get("MessageType")) {
		case LOGIN:
			login(event.get("User"));
			break;
		case USER_SELECTING:
			UserSelecting(event);
		case BATTLE_ACCEPT:
			battleAccept();
		case LOGOUT:
			logout(event);
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

	private void login(Object obj) {
		User user = (User) obj;

		client = TetrisClient.createTetrisClient();

		JSONObject message = new JSONObject();
		message.put("MessageType", MessageType.LOGIN);
		message.put("User", user);

		client.send(message.toJSONString());

	}

	private void UserSelecting(Object obj) {
		JSONObject msg = (JSONObject) obj;
		client.send(msg.toJSONString());
	}

	private void logout(Object obj) {
		JSONObject msg = (JSONObject) obj;
		client.send(msg.toJSONString());
	}

	private void battleAccept() {

		JSONObject message = new JSONObject();
		message.put(MessageType.class.getName(), MessageType.BATTLE_ACCEPT);
		client.send(message.toJSONString());

		message = new JSONObject();
		message.put(MessageType.class.getName(), MessageType.USER_MESSAGE);
		message.put(UserControl.users.getPlayer().getClass(), UserControl.users.getPlayer());
		client.send(message.toJSONString());

		message = new JSONObject();
		message.put(MessageType.class.getName(), MessageType.BATTLE_START);
		client.send(message.toJSONString());
	}
}
