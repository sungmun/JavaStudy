package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class FrameMoveAction implements ActionListener {
	JFrame movefr,nowfr;
	public FrameMoveAction(JFrame moveframe,JFrame nowframe) {
		movefr=moveframe;
		nowfr=nowframe;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		movefr.setVisible(true);
		nowfr.dispose();
	}

}
