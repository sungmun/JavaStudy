package Control;

import org.json.simple.JSONObject;

public class ServerConnect implements EventListener {
	public static EventHandler ServerToControl = new EventHandler();
	public static EventHandler ControlToServer = new EventHandler();

	public ServerConnect() {
		ServerConnect.ServerToControl.addListener(this);
	}

	@Override
	public void onEvent(JSONObject event) {
		System.out.println("ServerConnect.onEvent()");
		System.out.println(event.toJSONString());
	}

	@Override
	public void methodCatch(JSONObject event) {
		// TODO Auto-generated method stub

	}

}
