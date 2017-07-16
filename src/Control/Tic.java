package Control;
import Model.MoveType;
import Model.Space;
import View.TetrinoBlockPanel;

public class Tic extends Thread implements MoveType{
	TetrisControlManager manager=TetrisControlManager.createTetrisControlManager();
	TetrinoBlockPanel panel=TetrinoBlockPanel.createTetrinoBlockPanel();
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
				panel.repaint();
				for (Space[] spaces : manager.realtimemap) {
					for (Space space : spaces) {
						System.out.print(((space.toString()=="Space")?0:1)+" ");
					}
					System.out.println();
				}
				System.out.println();
				System.out.println();
				System.out.println();
				sleep((long)(speed*1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/*
		이부분에 네트워크 부분 작성 
		*/
	}
	public void speedUp() {
		speed+=0.1;
	}
	
}
