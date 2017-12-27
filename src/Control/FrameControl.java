package Control;

import javax.swing.JFrame;

public class FrameControl {
	public static void FrameChange(JFrame fropen, JFrame frclose) {
		if (fropen != null || frclose == null) {
			fropen.setVisible(true);
			frclose.dispose();
		}
	}
	public static void FrameClose(JFrame frclose) {
		
	}
}
