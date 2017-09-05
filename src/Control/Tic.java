package Control;

import java.awt.Rectangle;
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
				errCheck();
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
	public static void errCheck() {
		TetrisControlManager managers=TetrisControlManager.getTetrisControlManager();
		Point pos = managers.getNowTetrino().getFlowTetrino();
		Rectangle rec = managers.getNowTetrino().getActivespace();
		// rec는 그림을 그려야 하는 범위를 표시한다
		// pos는 현제 테트리노의 위치를 알려준다
		int repaintx = pos.getX() + (int) rec.getX() - 4;
		int repainty = pos.getY() + (int) rec.getY() - 2;
		if(23<= (int) rec.getHeight() + repainty) {
			System.out.println("Tic.errCheck()");
			TetrisControlManager.getTetrisControlManager().mapPaint();
			System.err.println("Y의 값이 범위를 초과 했습니다!!");
			System.err.println("repaintX : "+ repaintx + "\t\trepaintY : " + repainty );
			System.err.println("maxX : "+ ((int) rec.getWidth() + repaintx) + "\tmaxY : " + ((int) rec.getHeight() + repainty ));
			System.err.println("FlowTetrino "+pos.toString());
			System.err.println("Activespace "+rec.toString());
			System.err.println("");
		}
		if(10<=(int) rec.getWidth() + repaintx) {
			System.out.println("Tic.errCheck()");
			TetrisControlManager.getTetrisControlManager().mapPaint();
			System.err.println("X의 값이 범위를 초과 했습니다!!");
			System.err.println("repaintX : "+ repaintx + "\t\trepaintY : " + repainty );
			System.err.println("maxX : "+ ((int) rec.getWidth() + repaintx) + "\tmaxY : " + ((int) rec.getHeight() + repainty ));
			System.err.println("FlowTetrino "+pos.toString());
			System.err.println("Activespace "+rec.toString());
			System.err.println("");
		}
		
	}
}
