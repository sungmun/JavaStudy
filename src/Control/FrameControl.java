package Control;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Model.ClientMessage;
import Serversynchronization.MessageType;

public class FrameControl implements EventListener {
	public FrameControl() {
		MVC_Connect.ModelToControl.addListener(this);
		MVC_Connect.ViewToControl.addListener(this);
	}

	static void FrameChange(JsonObject obj) {
		FrameChange(obj.get("firstFrame"), obj.get("secondFrame"));
	}

	static void FrameChange(Object fropen, Object frclose) {

		JsonObject open = new JsonObject();
		open.addProperty("method", "setVisible");
		open.addProperty("boolean", true);
		MVC_Connect.ControlToView.callEvent(fropen.getClass(), new Gson().toJson(open));

		JsonObject close = new JsonObject();
		close.addProperty("method", "dispose");
		MVC_Connect.ControlToView.callEvent(frclose.getClass(), new Gson().toJson(close));
	}

	static void showOptionDialog(JsonObject obj) {
		int op = showOptionDialog(obj.get("title"), obj.get("content"), obj.get("JOptionPaneType"),
				obj.get("JOptionPaneStyle"));
		MVC_Connect.ControlToModel.quickCallEvent(ClientMessage.class,
				(op == JOptionPane.YES_OPTION) ? MessageType.BATTLE_ACCEPT : MessageType.BATTLE_DENIAL);
	}

	static int showOptionDialog(Object title, Object content, Object JOptionPaneType, Object JOptionPaneStyle) {
		return JOptionPane.showOptionDialog(null, (String) content, (String) title, (int) JOptionPaneType,
				(int) JOptionPaneStyle, null, null, null);

	}

	static void showMessageDialog(JsonObject event) {
		showMessageDialog(event.get("title"), event.get("content"));
	}

	static void showMessageDialog(Object title, Object content) {
		JOptionPane.showMessageDialog(null, (String) content, (String) title, JOptionPane.PLAIN_MESSAGE);
	}

	void FrameClose(JFrame frclose) {

	}

	@Override
	public void onEvent(String event) {
		System.out.println("FrameControl.onEvent()");
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(event);
		methodCatch(element);
	}

	@Override
	public void methodCatch(Object event) {
		JsonElement obj = (JsonElement) event;
		switch (obj.getAsJsonObject().get("method").getAsString()) {
		case "FrameChange":
			FrameChange( obj.getAsJsonObject());
			break;
		case "showOptionDialog":
			showOptionDialog(obj.getAsJsonObject());
			break;
		case "showMessageDialog":
			showMessageDialog(obj.getAsJsonObject());
			break;
		}

	}
}
