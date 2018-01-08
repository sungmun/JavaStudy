package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.MoveType;
import Model.TetrisManager;
import Serversynchronization.TotalJsonObject;
import Serversynchronization.User;
import View.Multe.MultiFrame;
import View.Single.SingleFrame;

public class TicAction implements ActionListener {
	ImagePrint mainprint;

	@Override
	public void actionPerformed(ActionEvent e) {
		System.gc();
		TotalJsonObject moveMessage = new TotalJsonObject();
		moveMessage.addProperty("method", "TetrinoBlockMove");
		moveMessage.addProperty("MoveType", MoveType.DOWN.toString());
		MVC_Connect.ControlToModel.callEvent(TetrisManager.class,moveMessage.toString());
		speedChange();
	}

	public void timeStop() {
		MVC_Connect.ControlToView.quickCallEvent(SingleFrame.class, "TimeStop");
		MVC_Connect.ControlToView.quickCallEvent(MultiFrame.class, "TimeStop");
	}

	public void speedChange() {
		User user = UserControl.users.getPlayer();
		int level = user.getInfo().getLevel();
		MVC_Connect.ControlToView.quickCallEvent(SingleFrame.class, "Delay", (int) (500 * Math.pow(0.999, level - 1)));
		MVC_Connect.ControlToView.quickCallEvent(MultiFrame.class, "Delay", (int) (500 * Math.pow(0.999, level - 1)));
	}
}
