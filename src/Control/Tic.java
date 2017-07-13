package Control;
import Model.MoveType;

public class Tic extends Thread implements MoveType{
	TetrisControlManager manager=TetrisControlManager.createTetrisControlManager();
	double speed=1.0;
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
