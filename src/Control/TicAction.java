package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.json.simple.JSONObject;

import Model.MoveType;
import Model.TetrisManager;
import View.Multe.MultiFrame;
import View.Single.SingleFrame;

public class TicAction implements ActionListener {
	ImagePrint mainprint;

	@Override
	public void actionPerformed(ActionEvent e) {
		System.gc();
		JSONObject moveMessage = new JSONObject();
		moveMessage.put("method", "TetrinoBlockMove");
		moveMessage.put("MoveType", MoveType.DOWN);
		MVC_Connect.ControlToModel.callEvent(TetrisManager.class.getClass(), moveMessage);
		speedChange();
	}

	public void timeStop() {
		MVC_Connect.ControlToView.quickCallEvent(SingleFrame.class.getClass(), "TimeStop");
		MVC_Connect.ControlToView.quickCallEvent(MultiFrame.class.getClass(), "TimeStop");
	}

	public void speedChange() {
		User user=UserControl.users.getPlayer();
		int level=user.getInfo().getLevel();
		MVC_Connect.ControlToView.quickCallEvent(SingleFrame.class.getClass(), "Delay",(int) (500 * Math.pow(0.999, level - 1)));
		MVC_Connect.ControlToView.quickCallEvent(MultiFrame.class.getClass(), "Delay",(int) (500 * Math.pow(0.999, level - 1)));
	}
}
