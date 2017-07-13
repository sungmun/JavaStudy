package Control;
import java.util.Arrays;

import Model.Block;
import Model.CreateBlock;
import Model.Map;
import Model.Space;
import Model.Tetrino;

public class TetrisControlManager {
	
	private static int height=23;
	private static int width=11;
	
	Space[][] realtimemap;
	Tetrino tetrino; 
	
	static TetrisControlManager tetrismanager=null;
	
	private TetrisControlManager() {
		Map tetris=Map.createMap(11, 21);
		realtimemap=tetris.getMap();
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
	public boolean TetrinoBlockMove(int moveType){//테트리노의 이동시 현재 맵의 정보 리프래쉬
		Space[][] temp=realtimemap;
		int x=tetrino.getFlowTetrino().getX()-3;
		int y=tetrino.getFlowTetrino().getY()-1;
		for(int i=0;i<4;i++){
			for(int j=1;j<5;j++){
				Space spc=realtimemap[x+j][y+i];
				if(spc.toString()=="Space"){
					Block temp1=(Block)spc;
					if(temp1.getIsMove()){
						realtimemap[x+j][y+i]=new Space();
					}
				}
			}
		}//유동 블럭을 전부 제거
		if(!tetrino.moveTetrino(moveType)){//이동을 실패한 경우
			realtimemap=temp; 			   //원상 복귀
			return false;						   //함수 종료
		}
		//테트리노의 이동
		x=tetrino.getFlowTetrino().getX()-3;
		y=tetrino.getFlowTetrino().getY()-1;
		//테트리노의 이동 위치 재정의
		for (int j=0;y<4;y++) {
			for (int i=1;x<5;x++) {
				Space spc=tetrino.getTetrino()[j][i];
				if(spc.toString()=="Space"){
					Block temp1=(Block)spc;
					if(temp1.getIsMove()){
						realtimemap[x+j][y+i]=spc;
					}
				}
			}
		}
		//이동한 테트리노를 실시간 맵에 반영
		aroundSearch();
		//테트리노의 주변 정보를 입력
		return true;
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
	public Space[][] getRangeRealTimeMap(int height){
		Space[][] spc=new Space[4][width];
		for(int i=0;i<4;i++){
			for(int j=0;j<width;j++){
				spc[i][j]=realtimemap[i+height][j];
			}
		}
		return spc;
	}
	public void lineClear(int clearline,Point pos){
		Space[] spc=new Space[width];
		int startpos=pos.getY()-1;
		
		for(int i=0;i<4;i++){
			int bit=0x01<<i;
			if((clearline &bit)==bit){
				realtimemap[startpos+i]=spc;
			}	
		}
		for(int i=startpos+4;i>=0;i--){
			if(Arrays.equals(spc, realtimemap[i])){
				for(int j=i;j>0;i--){
					realtimemap[j]=realtimemap[j-1];
				}
				realtimemap[0]=spc;
			}
		}
		
	}
	public int lineCheack(Point pos){
		Space[][] spc= getRangeRealTimeMap(pos.getY()-1);
		boolean cheack=false;
		int returnvalue=0;
		for (int i=0;i<4;i++) {
			for(int j=0;i<getWidth();j++){
				if(spc[i][j].toString()=="Space"){
					cheack=false;
					break;
				}else{
					cheack=true;
				}
			}
			if(cheack){
				returnvalue|=(0x01<<i);
			}
		}
		return returnvalue;
	}
	public boolean gameOverCheack(Point pos){
		if(getNowTetrino().getFlowTetrino().getY()>=5){
			return false;
		}
		
		Space[][] cheackmap;
		
		{
			cheackmap=getRangeRealTimeMap(0);
			Space[][] spc=new Space[3][width];
			spc[0]=cheackmap[0];
			spc[1]=cheackmap[1];
			spc[2]=cheackmap[2];
			cheackmap=spc;
		}
		for (Space[] spaces : cheackmap) {
			for (Space space : spaces) {
				if(space.toString()=="Block"){
					return true;
				}
			}
		}
		return false;
	}
	public Tetrino getNowTetrino() {
		return tetrino;
	}
	public void setNowTetrino(Tetrino ttrn) {
		tetrino=ttrn;
	}
	public static int getWidth(){
		return width;
	}
	public static int getHeight(){
		return height;
	}
}
