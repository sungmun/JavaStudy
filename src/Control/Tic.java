package Control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.MoveType;
import View.MainView;

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
					MainView.getTime().stop();
				}
				manager.setNowTetrino(null);
			}
		}
		new MainView().blockMoveRePaint();
	}
	
	
	
}
