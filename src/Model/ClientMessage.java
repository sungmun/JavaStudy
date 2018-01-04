package Model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Control.EventListener;
import Control.MVC_Connect;
import Control.User;
import Control.UserControl;
import Serversynchronization.MessageType;

public class ClientMessage implements EventListener {
	TetrisClient client;
	static ClientMessage message;
	ClientMessage() {
		message=this;
		MVC_Connect.ControlToModel.addListener(this);
	}

	@Override
	public void onEvent(String event)  {
		System.out.println("ClientMessage.onEvent()");
		System.out.println(event);
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(event);
		if (element.getAsJsonObject().get(MessageType.class.getName()) == null)
			return;
		element.getAsJsonObject().remove("sentClass");
		methodCatch(element);
	}

	@Override
	public void methodCatch(Object event) {
		if(event.getClass()==JSONArray.class) {
			JSONArray array=(JSONArray)event;
			client.send(array.toJSONString());
			return;
		}
		JSONObject obj=(JSONObject)event;
		switch ((MessageType) obj.get("MessageType")) {
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
