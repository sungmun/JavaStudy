package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.MoveType;
import Model.TetrisManager;
import Serversynchronization.User;
import View.BaseClass.GameBasicFrame;
import View.Multe.MultiFrame;
import View.Single.SingleFrame;

public class TicAction implements ActionListener {
	ImagePrint mainprint;
	int speed = (int) (500 * Math.pow(0.999, 1));

	@Override
	public void actionPerformed(ActionEvent e) {
		System.gc();
		MVC_Connect.ControlToModel.callEvent(TetrisManager.class,
				(modelObj) -> ((TetrisManager) modelObj).dropCheck(MoveType.DOWN));
		speedChange();
	}

	public void timeStop() {
		MVC_Connect.ControlToView.callEvent(SingleFrame.class, (view) -> {
			GameBasicFrame.time.timerstop();
		});
	}

	public void speedChange() {
		User user = UserControl.users.getPlayer();
		if (user == null) {
			return;
		}

		this.speed = (int) (500 * Math.pow(0.999, user.getLevel() - 1));
		MVC_Connect.ControlToView.callEvent(SingleFrame.class, (frame) -> GameBasicFrame.time.setDelay(speed));
		MVC_Connect.ControlToView.callEvent(MultiFrame.class, (frame) -> GameBasicFrame.time.setDelay(speed));
	}
}
