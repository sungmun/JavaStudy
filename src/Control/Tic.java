package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import Model.MoveType;
import View.MainView;

@SuppressWarnings("serial")
public class Tic extends Timer implements MoveType {
	TetrisControlManager manager;

	public Tic(int delay, TetrisControlManager manager) {
		super(delay, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (manager.getNowTetrino() == null) {
					manager.createBlock();
				} else {
					if (!manager.TetrinoBlockMove(DOWN)) {
						Point nowpos = manager.tetrino.getFlowTetrino();
						if (manager.gameOverCheack(nowpos)) {
							MainView.getMainviewcopy().blockMoveRePaint();
							MainView.getTime().stop();
						}
						manager.setNowTetrino(null);
						return;
					}
				}
				MainView.getMainviewcopy().blockMoveRePaint();
			}

		});
		this.manager = manager;
	}

	@Override
	public void start() {
		super.start();
		System.out.println("Tic.start()");
	}

	@Override
	public void stop() {
		super.stop();
		System.out.println("Tic.stop()");
	}
}
