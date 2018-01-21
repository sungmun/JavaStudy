package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameChangeAction implements ActionListener {
	Class<?> fopen;
	Class<?> fclose;

	public FrameChangeAction(Class<?> fopen, Class<?> fclose) {
		this.fclose = fclose;
		this.fopen = fopen;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		FrameControl.FrameChange(fopen.getName(), fclose.getName());
	}

}
