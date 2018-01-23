package Control;

import javax.swing.JOptionPane;

import Serversynchronization.TotalJsonObject;

public class FrameControl implements EventListener {
	public FrameControl() {
		MVC_Connect.ModelToControl.addListener(this);
		MVC_Connect.ViewToControl.addListener(this);
	}

	static void FrameChange(TotalJsonObject obj) {
		FrameChange(obj.get("firstFrame"), obj.get("secondFrame"));
	}

	static void FrameChange(Object fropen, Object frclose) {

		try {
			Class.forName(fropen.toString()).newInstance();

			TotalJsonObject open = new TotalJsonObject();
			open.addProperty("method", "setVisible");
			open.addProperty("boolean", true);
			MVC_Connect.ControlToView.callEvent(Class.forName(fropen.toString()), open);

			TotalJsonObject close = new TotalJsonObject();
			close.addProperty("method", "dispose");
			MVC_Connect.ControlToView.callEvent(Class.forName(frclose.toString()), close);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	static int showOptionDialog(Object title, Object content, Object JOptionPaneType, Object JOptionPaneStyle) {
		int type = Integer.parseInt(JOptionPaneType.toString());
		int style = Integer.parseInt(JOptionPaneStyle.toString());
		return JOptionPane.showOptionDialog(null, (String) content, (String) title, type, style, null, null, null);
	}

	static void showMessageDialog(Object title, Object content) {
		JOptionPane.showMessageDialog(null, (String) content, (String) title, JOptionPane.PLAIN_MESSAGE);
	}


	@Override
	public void onEvent(Object event) {
		System.out.println("FrameControl.onEvent()");
		TotalJsonObject object = new TotalJsonObject((String) event);
		methodCatch(object);
	}

	public void methodCatch(Object event) {
		TotalJsonObject obj = (TotalJsonObject) event;
		switch (obj.get("method").toString()) {
		case "FrameChange":
			FrameChange(obj);
			break;
		}

	}
}
