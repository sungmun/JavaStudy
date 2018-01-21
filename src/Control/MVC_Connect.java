package Control;

import Model.ClientMessage;
import Model.TetrisManager;
import Serversynchronization.MessageType;
import Serversynchronization.TotalJsonObject;
import View.LevelPanel;
import View.ScorePanel;
import View.Multe.MultiFrame;
import View.Single.SingleFrame;

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

		TotalJsonObject object = new TotalJsonObject(event.toString());

		switch (TotalJsonObject.GsonConverter(object.get(MessageType.class.getSimpleName()).toString(),
				MessageType.class)) {
		case LEVEL_MESSAGE:
			MVC_Connect.ControlToView.callEvent(LevelPanel.class, object);
			MVC_Connect.ControlToModel.callEvent(ClientMessage.class, event);
			break;
		case SCORE_MESSAGE:
			MVC_Connect.ControlToView.callEvent(ScorePanel.class, object);
			MVC_Connect.ControlToModel.callEvent(ClientMessage.class, event);
			break;
		case SAVE_BLOCK_MESSAGE:
		case NEXT_BLOCK_MESSAGE:
		case MAP_MESSAGE:
			MVC_Connect.ControlToModel.callEvent(ClientMessage.class, event);
			break;
		case BATTLE_ACCEPT:
		case BATTLE_DENIAL:
			MVC_Connect.ControlToModel.callEvent(ClientMessage.class, event);
			
		default:
		}

	}

	public void sendViewMessage(Object event) {
		String message = (String) event;
		TotalJsonObject object = new TotalJsonObject();
		object.jsonParser(message);

		// switch ((MessageType)
		// object.GsonConverter((String)object.get(MessageType.class.getName()),
		// MessageType.class)) {
		// case LEVEL_MESSAGE:
		// System.out.println("level");
		// MVC_Connect.ControlToView.callEvent(LevelPanel.class, message);
		// break;
		// case SCORE_MESSAGE:
		// System.out.println("score");
		// MVC_Connect.ControlToView.callEvent(ScorePanel.class, message);
		// break;
		// case MAP_MESSAGE:
		// System.out.println("map");
		// MVC_Connect.ControlToView.callEvent(ScorePanel.class, message);
		// break;
		// default:
		// }
	}

	@Override
	public void onEvent(Object event) {
		// System.out.println("MVC_Connect.onEvent()");
		// System.out.println(event);
		TotalJsonObject object = new TotalJsonObject(event.toString());
		if (object.get("method") != null)
			methodCatch(object);

		if (object.get(MessageType.class.getSimpleName()) == null)
			return;
		sendViewMessage(event);
		String type = object.get("sentClass").toString();
		if (type.equals(TetrisManager.class.getName())) {
			sendModelMessage(event);
		}
	}

	public void methodCatch(Object event) {
		TotalJsonObject jsonObject = (TotalJsonObject) event;
		String jsonStr = jsonObject.get("method").toString();
		switch (jsonStr) {
		case "stop":
			MVC_Connect.ControlToView.callEvent(SingleFrame.class, jsonObject.toString());
			MVC_Connect.ControlToView.callEvent(MultiFrame.class, jsonObject.toString());
			break;

		default:
			break;
		}
	}
}
