
public class TetrisControlManager {
	
	private static int height=21;
	private static int width=11;
	
	Space[][] realtimemap;
	Tetrino tetrino; 
	
	static TetrisControlManager tetrismanager=null;
	
	private TetrisControlManager() {
		Map tetris=Map.createMap(11, 21);
		realtimemap=tetris.map;
	}

	public static TetrisControlManager createTetrisControlManager(){
		if(tetrismanager==null){
			tetrismanager=new TetrisControlManager();
		}
		return tetrismanager;
	}
	public void createBlock() {
		int createposition=width/3;
		tetrino=CreateBlock.tetrinoRandomCreate();
		for (int y=0;y<4;y++) {
			for (int x=1;x<5;x++) {
				realtimemap[x+createposition][y]=tetrino.getTetrino()[x][y];
			}
		}
		tetrino.setFlowTetrino(new Point(createposition+3,1));
	}
	public void TetrinoBlockMove(int moveType){//��Ʈ������ �̵��� ���� ���� ���� ��������
		Space[][] temp=realtimemap;
		int x=tetrino.getFlowTetrino().getX()-3;
		int y=tetrino.getFlowTetrino().getY()-1;
		for(int i=0;i<4;i++){
			for(int j=1;j<5;j++){
				Space spc=realtimemap[x+j][y+i];
				if(spc.getSpace()&&((Block)spc).ismove){
					realtimemap[x+j][y+i]=new Space();
				}
			}
		}//���� ���� ���� ����
		if(!tetrino.moveTetrino(moveType)){//�̵��� ������ ���
			realtimemap=temp; 			   //���� ����
			return;						   //�Լ� ����
		}
		//��Ʈ������ �̵�
		x=tetrino.getFlowTetrino().getX()-3;
		y=tetrino.getFlowTetrino().getY()-1;
		//��Ʈ������ �̵� ��ġ ������
		for (int j=0;y<4;y++) {
			for (int i=1;x<5;x++) {
				Space spc=tetrino.getTetrino()[j][i];
				if(spc.getSpace()&&((Block)spc).ismove){
					realtimemap[x+j][y+i]=spc;
				}
			}
		}
		//�̵��� ��Ʈ���븦 �ǽð� �ʿ� �ݿ�
		aroundSearch();
		//��Ʈ������ �ֺ� ������ �Է�
		
	}
	private void aroundSearch(){
		int x=tetrino.getFlowTetrino().getX()-3;
		int y=tetrino.getFlowTetrino().getY()-1;
		for(int i=0;i<5;i++){
			for(int j=0;j<6;j++){
				Space spc=realtimemap[x+j][y+i];
				tetrino.setArea(j, i, spc);
			}
		}
	}
	public static int getWidth(){
		return width;
	}
	public static int getHeight(){
		return height;
	}
}
