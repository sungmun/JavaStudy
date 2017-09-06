package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.MoveType;
import View.MainView;

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
					MainView.getMainviewcopy().blockMoveRePaint();
					MainView.getTime().stop();
					return;
				}
				manager.setNowTetrino(null);
				int clearline=manager.lineCheack(nowpos);
				if(clearline>0) {
					manager.lineClear(clearline, nowpos);
				}
				return;
			}
		}
		MainView.getMainviewcopy().blockMoveRePaint();
	}
}
