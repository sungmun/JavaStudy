
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
	public void TetrinoBlockMove(int moveType){//테트리노의 이동시 현재 맵의 정보 리프래쉬
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
		}//유동 블럭을 전부 제거
		if(!tetrino.moveTetrino(moveType)){//이동을 실패한 경우
			realtimemap=temp; 			   //원상 복귀
			return;						   //함수 종료
		}
		//테트리노의 이동
		x=tetrino.getFlowTetrino().getX()-3;
		y=tetrino.getFlowTetrino().getY()-1;
		//테트리노의 이동 위치 재정의
		for (int j=0;y<4;y++) {
			for (int i=1;x<5;x++) {
				Space spc=tetrino.getTetrino()[j][i];
				if(spc.getSpace()&&((Block)spc).ismove){
					realtimemap[x+j][y+i]=spc;
				}
			}
		}
		//이동한 테트리노를 실시간 맵에 반영
		aroundSearch();
		//테트리노의 주변 정보를 입력
		
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
