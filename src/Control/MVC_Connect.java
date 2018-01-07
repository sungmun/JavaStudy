package Control;

import Model.ClientMessage;
import Model.TetrisManager;
import Serversynchronization.MessageType;

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

		String message = (String) event;
		TotalJsonObject object = new TotalJsonObject();
		object.jsonParser(message);

		switch ((MessageType) object.GsonConverter((String)object.get(MessageType.class.getName()), MessageType.class)) {
		case LEVEL_MESSAGE:
		case SCORE_MESSAGE:
		case MAP_MESSAGE:
		case SAVE_BLOCK_MESSAGE:
		case NEXT_BLOCK_MESSAGE:
			MVC_Connect.ControlToModel.callEvent(ClientMessage.class, message);
			break;
		default:
		}

	}

	public void sendViewMessage(Object event) {
		String message = (String) event;
		TotalJsonObject object = new TotalJsonObject();
		object.jsonParser(message);

//		switch ((MessageType) object.GsonConverter((String)object.get(MessageType.class.getName()), MessageType.class)) {
//		case LEVEL_MESSAGE:
//			System.out.println("level");
//			MVC_Connect.ControlToView.callEvent(LevelPanel.class, message);
//			break;
//		case SCORE_MESSAGE:
//			System.out.println("score");
//			MVC_Connect.ControlToView.callEvent(ScorePanel.class, message);
//			break;
//		case MAP_MESSAGE:
//			System.out.println("map");
//			MVC_Connect.ControlToView.callEvent(ScorePanel.class, message);
//			break;
//		default:
//		}
	}

	@Override
	public void onEvent(Object event) {
//		System.out.println("MVC_Connect.onEvent()");
//		System.out.println(event);
		TotalJsonObject object = new TotalJsonObject((String) event);
		if (object.get(MessageType.class.getName()) == null)
			return;
		sendViewMessage(event);
		String type = object.get("sentClass").toString();
		if (type.equals(TetrisManager.class.getName())) {
			sendModelMessage(event);
		}
	}

	@Override
	public void methodCatch(Object event) {
		// TODO Auto-generated method stub

	}
}
