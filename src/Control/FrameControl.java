package Control;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Model.ClientMessage;
import Serversynchronization.MessageType;

public class FrameControl implements EventListener {
	public FrameControl() {
		MVC_Connect.ModelToControl.addListener(this);
		MVC_Connect.ViewToControl.addListener(this);
	}

	static void FrameChange(TotalJsonObject obj) {
		FrameChange(obj.get("firstFrame"), obj.get("secondFrame"));
	}

	static void FrameChange(Object fropen, Object frclose) {

		TotalJsonObject open = new TotalJsonObject();
		open.addProperty("method", "setVisible");
		open.addProperty("boolean", true);
		MVC_Connect.ControlToView.callEvent(fropen.getClass(), open.toString());

		TotalJsonObject close = new TotalJsonObject();
		close.addProperty("method", "dispose");
		MVC_Connect.ControlToView.callEvent(frclose.getClass(), close.toString());
	}

	static void showOptionDialog(TotalJsonObject obj) {
		int op = showOptionDialog(obj.get("title"), obj.get("content"), obj.get("JOptionPaneType"),
				obj.get("JOptionPaneStyle"));
		MVC_Connect.ControlToModel.quickCallEvent(ClientMessage.class,
				(op == JOptionPane.YES_OPTION) ? MessageType.BATTLE_ACCEPT : MessageType.BATTLE_DENIAL);
	}

	static int showOptionDialog(Object title, Object content, Object JOptionPaneType, Object JOptionPaneStyle) {
		return JOptionPane.showOptionDialog(null, (String) content, (String) title, (int) JOptionPaneType,
				(int) JOptionPaneStyle, null, null, null);

	}

	static void showMessageDialog(TotalJsonObject event) {
		showMessageDialog(event.get("title"), event.get("content"));
	}

	static void showMessageDialog(Object title, Object content) {
		JOptionPane.showMessageDialog(null, (String) content, (String) title, JOptionPane.PLAIN_MESSAGE);
	}

	void FrameClose(JFrame frclose) {

	}

	@Override
	public void onEvent(Object event) {
		System.out.println("FrameControl.onEvent()");
		TotalJsonObject object = new TotalJsonObject((String) event);
		methodCatch(object);
	}

	@Override
	public void methodCatch(Object event) {
		TotalJsonObject obj = (TotalJsonObject) event;
		switch (obj.get("method").toString()) {
		case "FrameChange":
			FrameChange(obj);
			break;
		case "showOptionDialog":
			showOptionDialog(obj);
			break;
		case "showMessageDialog":
			showMessageDialog(obj);
			break;
		}

	}
}
