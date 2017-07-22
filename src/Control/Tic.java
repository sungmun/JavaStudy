package Control;
import Model.MoveType;
import View.TetrinoBlockPanel;

public class Tic extends Thread implements MoveType{
	TetrisControlManager manager=TetrisControlManager.createTetrisControlManager();
	TetrinoBlockPanel panel=TetrinoBlockPanel.createTetrinoBlockPanel();
	double speed=0.1;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			if(manager.getNowTetrino()==null){
				manager.createBlock();
			}else{
				if(!manager.TetrinoBlockMove(DOWN)){
					Point nowpos=manager.tetrino.getFlowTetrino();
					if(manager.gameOverCheack(nowpos)){
						break;
					}
					manager.setNowTetrino(null);
				}
			}
			try {
				panel.revalidate();
				panel.repaint();
				synchronized (this) {
					wait();
				}
				//print();
				sleep((long)(speed*1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/*
		�̺κп� ��Ʈ��ũ �κ� �ۼ� 
		*/
	}
	public void speedUp() {
		speed+=0.1;
	}
	
}
