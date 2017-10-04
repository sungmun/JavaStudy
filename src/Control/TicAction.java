package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.MoveType;
import Model.Point;
import Model.TetrisControlManager;

public class TicAction implements ActionListener, MoveType {
	TetrisControlManager manager;
	private static TicAction tic = null;

	private TicAction(TetrisControlManager manager) {
		this.manager = manager;
	}

	public static TicAction ticActionCreate(TetrisControlManager manager) {
		if (tic == null) {
			tic = new TicAction(manager);
		}
		return tic;
	}

	public static TicAction getTic() {
		return tic;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		ticActions();
	}

	public void ticActions() {
		// TODO Auto-generated method stub
		if (manager.getNowTetrino() == null) {
			manager.createBlock();
		} else {
			if (!manager.TetrinoBlockMove(DOWN)) {
				Point nowpos = manager.tetrino.getFlowTetrino();
				if (manager.gameOverCheack(nowpos)) {
					manager.rePaint();
					manager.getTime().stop();
					return;
				}
				manager.setNowTetrino(null);
				int clearline=manager.lineCheack(nowpos);
				if(clearline>0) {
					manager.lineClear(clearline, nowpos);
					manager.rePaint();
				}
				return;
			}
		}
		if(manager.getLevel()<10) {
			manager.getTime().setDelay(500-manager.getLevel()*35);
		}
		manager.rePaint();
	}
}
