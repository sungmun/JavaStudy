package Control;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FrameControl implements EventListener {
	public FrameControl() {
		MVC_Connect.ModelToControl.addListener(this);
		MVC_Connect.ViewToControl.addListener(this);
	}

	public void FrameChange(Class<?> fropen, Class<?> frclose) {
		FrameOpen(fropen);
		FrameClose(frclose);
	}

	public void FrameClose(Class<?> frclose) {
		MVC_Connect.ControlToView.callEvent(frclose, (fr) -> ((JFrame) fr).dispose());
	}

	public void FrameOpen(Class<?> fropen) {

		try {
			fropen.newInstance();
			MVC_Connect.ControlToView.callEvent(fropen, (fr) -> ((JFrame) fr).setVisible(true));
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public int showOptionDialog(String title, String content, int JOptionPaneType, int JOptionPaneStyle) {
		return JOptionPane.showOptionDialog(null, content, title, JOptionPaneType, JOptionPaneStyle, null, null, null);
	}

	public void showMessageDialog(String title, String content) {
		JOptionPane.showMessageDialog(null, content, title, JOptionPane.PLAIN_MESSAGE);
	}
	//
	// @Override
	// public void onEvent(Object event) {
	// System.out.println("FrameControl.onEvent()");
	// TotalJsonObject object = new TotalJsonObject((String) event);
	// methodCatch(object);
	// }
	//
	// public void methodCatch(Object event) {
	// TotalJsonObject obj = (TotalJsonObject) event;
	// switch (obj.get("method").toString()) {
	// case "FrameChange":
	// FrameChange(obj);
	// break;
	// case "FrameOpen":
	// FrameOpen(obj);
	// break;
	// case "FrameClose":
	// FrameClose(obj);
	// break;
	// }
	//
	// }

	@Override
	public void onEvent(CallBackEvent event) {
		event.callBackEvent(this);
	}
}
