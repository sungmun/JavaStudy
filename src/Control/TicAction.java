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
	int speed = (int) (500 * Math.pow(0.999, 1));

	@Override
	public void actionPerformed(ActionEvent e) {
		System.gc();
		TotalJsonObject moveMessage = new TotalJsonObject();
		moveMessage.addProperty("method", "TetrinoBlockMove");
		moveMessage.addProperty("MoveType", MoveType.DOWN.toString());
		MVC_Connect.ControlToModel.callEvent(TetrisManager.class, moveMessage.toString());
		speedChange();
	}

	public void timeStop() {
		MVC_Connect.ControlToView.quickCallEvent(SingleFrame.class, "TimeStop");
		MVC_Connect.ControlToView.quickCallEvent(MultiFrame.class, "TimeStop");
	}

	public void speedChange() {
		User user = UserControl.users.getPlayer();
		if(user==null) {
			return;
		}
		
		this.speed = (int) (500 * Math.pow(0.999, user.getInfo().getLevel()-1));
		MVC_Connect.ControlToView.quickCallEvent(SingleFrame.class, "Delay", this.speed);
		MVC_Connect.ControlToView.quickCallEvent(MultiFrame.class, "Delay", this.speed);
	}
}
