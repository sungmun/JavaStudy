package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Client.TetrisClient;
import Model.MoveType;
import Model.TetrisControlManager;
import Model.UserTetrisControlManager;
import Model.ValueObject.Point;
import Serversynchronization.MessageType;
import Serversynchronization.SocketMessage;

public abstract class TicAction implements ActionListener, MoveType,MessageType {
	protected TetrisControlManager manager = UserTetrisControlManager.getTetrisControlManager();
	ImagePrint mainprint;
	
	public TicAction(ImagePrint mainprint) {
		super();
		this.mainprint = mainprint;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (manager.getNowTetrino() == null) {
			manager.createBlock();
			mainprint.NextBlockPaint(manager);
		} else if (!manager.TetrinoBlockMove(DOWN)) {
			check();
		}
		mainprint.TetrinoBlockPaint(manager);
		speedChange();
		TetrisClient client=TetrisClient.getTetrisClient();
		if(client!=null) {
			client.send(new SocketMessage(MAP_MESSAGE, manager.getRealTimeMap()));
		}
	}

	public boolean check() {
		Point nowpos = manager.tetrino.getFlowTetrino();
		if (manager.gameOverCheack(nowpos)) {
			timeStop();
			return false;
		}
		manager.setNowTetrino(null);
		int clearline = manager.lineCheack(nowpos);
		if (clearline > 0) {
			manager.lineClear(clearline, nowpos);
		}
		return true;
	}

	public abstract void timeStop();

	public abstract void speedChange();
}
