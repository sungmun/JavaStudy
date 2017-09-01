package Control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import Model.MoveType;
import View.MainView;
import View.TetrinoBlockPanel;

public class Tic implements MoveType,ActionListener{
	TetrisControlManager manager=TetrisControlManager.createTetrisControlManager();
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if(manager.getNowTetrino()==null){
			manager.createBlock();
		}else{
			if(!manager.TetrinoBlockMove(DOWN)){
				Point nowpos=manager.tetrino.getFlowTetrino();
				if(manager.gameOverCheack(nowpos)){
					new MainView().getTime().stop();
				}
				manager.setNowTetrino(null);
			}
		}
		new MainView().blockMoveRePaint();
	}
	
	
	
}
