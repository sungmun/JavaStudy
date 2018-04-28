package Control;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FrameControl implements EventListener {
	public FrameControl() {
		MVC_Connect.Controller.addListener(this);
	}

	public void FrameChange(Class<?> fropen, Class<?> frclose) {
		FrameOpen(fropen);
		FrameClose(frclose);
	}

	public void FrameClose(Class<?> frclose) {
		MVC_Connect.View.callEvent(frclose, (fr) -> ((JFrame) fr).dispose());
	}

	public void FrameOpen(Class<?> fropen) {
		try {
			fropen.newInstance();
			MVC_Connect.View.callEvent(fropen, (fr) -> ((JFrame) fr).setVisible(true));
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

	@Override
	public void onEvent(CallBackEvent event) {
		event.callBackEvent(this);
	}
}
