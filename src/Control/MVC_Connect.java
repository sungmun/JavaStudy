package Control;

import java.lang.reflect.Field;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import Model.ClientMessage;
import Model.TetrisManager;
import Serversynchronization.MessageType;
import View.LevelPanel;
import View.ScorePanel;

public class MVC_Connect implements EventListener {
	public static EventHandler ModelToControl = new EventHandler();
	public static EventHandler ControlToModel = new EventHandler();
	public static EventHandler ControlToView = new EventHandler();
	public static EventHandler ViewToControl = new EventHandler();

	public MVC_Connect() {
		ModelToControl.addListener(this);
		ViewToControl.addListener(this);
	}

	public void sendModelMessage(Object event) {
		try {
			Gson gson = new Gson();
			JsonElement element = (JsonElement) event;

			String type = element.getAsJsonObject().get(MessageType.class.getName()).getAsString();
			Class typeClass = Class.forName(type);
			switch ((MessageType) gson.fromJson(type, typeClass)) {
			case LEVEL_MESSAGE:
			case SCORE_MESSAGE:
			case MAP_MESSAGE:
			case SAVE_BLOCK_MESSAGE:
			case NEXT_BLOCK_MESSAGE:
				MVC_Connect.ControlToModel.callEvent(ClientMessage.class, gson.toJson(event));
				break;
			default:
			}
		} catch (JsonSyntaxException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void sendViewMessage(Object event) {

		try {
			Gson gson = new Gson();
			JsonElement element = (JsonElement) event;

			String type = element.getAsJsonObject().get(MessageType.class.getName()).getAsString();
			Class typeClass = Class.forName(type);
			switch ((MessageType) gson.fromJson(type, typeClass)) {
			case LEVEL_MESSAGE:
				System.out.println("level");
				MVC_Connect.ControlToView.callEvent(LevelPanel.class, gson.toJson(event));
				break;
			case SCORE_MESSAGE:
				System.out.println("score");
				MVC_Connect.ControlToView.callEvent(ScorePanel.class, gson.toJson(event));
				break;
			case MAP_MESSAGE:
				System.out.println("map");
				MVC_Connect.ControlToView.callEvent(ScorePanel.class, gson.toJson(event));
				break;
			default:
			}
		} catch (JsonSyntaxException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onEvent(String event) {
		System.out.println("MVC_Connect.onEvent()");
		System.out.println(event);
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(event);
		if (element.getAsJsonObject().get(MessageType.class.getName()) == null)
			return;
		sendViewMessage(element);
		String type = element.getAsJsonObject().get("sentClass").getAsString();
		if (type.equals(TetrisManager.class.getName())) {
			sendModelMessage(element);
		}
	}

	@Override
	public void methodCatch(Object event) {
		// TODO Auto-generated method stub

	}
}
