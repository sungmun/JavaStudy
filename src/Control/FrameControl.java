package Control;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Client.ClientMessage;
import Serversynchronization.MessageType;

public class FrameControl implements EventListener {
	public FrameControl() {
		MVC_Connect.ModelToControl.addListener(this);
		MVC_Connect.ViewToControl.addListener(this);
	}

	static void FrameChange(Object fropen, Object frclose) {

		JSONObject open = new JSONObject();
		open.put("method", "setVisible");
		open.put("boolean", true);
		MVC_Connect.ControlToView.callEvent(fropen.getClass(), open);

		JSONObject close = new JSONObject();
		close.put("method", "dispose");
		MVC_Connect.ControlToView.callEvent(frclose.getClass(), close);
	}

	static void showOptionDialog(Object title, Object content, Object JOptionPaneType, Object JOptionPaneStyle) {
		int op = JOptionPane.showOptionDialog(null, (String) content, (String) title, (int) JOptionPaneType,
				(int) JOptionPaneStyle, null, null, null);
		ServerConnect.ControlToServer.quickCallEvent(ClientMessage.class.getClass(),
				(op == JOptionPane.YES_OPTION) ? MessageType.BATTLE_ACCEPT : MessageType.BATTLE_DENIAL);
	}

	static void showMessageDialog(Object title, Object content) {
		JOptionPane.showMessageDialog(null, (String) content, (String) title, JOptionPane.PLAIN_MESSAGE);
	}

	void FrameClose(JFrame frclose) {

	}

	@Override
	public void onEvent(JSONObject event) {
		System.out.println("FrameControl.onEvent()");
		System.out.println(event.toJSONString());
		JSONObject json = event;

		if (json.get("method") != null) {
			methodCatch(json);
		} else {
			System.out.println(json.toJSONString());
		}
	}

	public void methodCatch(JSONObject event) {
		switch ((String) event.get("method")) {
		case "FrameChange":
			FrameChange(event.get("firstFrame"), event.get("secondFrame"));
			break;
		case "showOptionDialog":
			showOptionDialog(event.get("title"), event.get("content"), event.get("JOptionPaneType"),
					event.get("JOptionPaneStyle"));
			break;
		case "showMessageDialog":
			showMessageDialog(event.get("title"), event.get("content"));
		}
	}
}
