package Control;
import java.util.Arrays;

import Model.Block;
import Model.CreateBlock;
import Model.Map;
import Model.Space;
import Model.Tetrino;
import Model.TetrinoType;

public class TetrisControlManager implements TetrinoType{
	
	private static int height=23;
	private static int width=11;
	
	Space[][] realtimemap;
	Tetrino tetrino; 
	
	static TetrisControlManager tetrismanager=null;
	
	private TetrisControlManager() {
		Map tetris=new Map(width, height);
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
				realtimemap[y][x+createposition]=tetrino.getTetrino()[y][x];
			}
		}
		tetrino.setFlowTetrino(new Point(1, createposition+3));
	}
	public boolean TetrinoBlockMove(int moveType){//테트리노의 이동시 현재 맵의 정보 리프래쉬
		Space[][] temp=new Space[height][width];
		for(int i=0;i<realtimemap.length;i++){
			temp[i]=realtimemap[i].clone();
		}
		int x=tetrino.getFlowTetrino().getX()-3;
		int y=tetrino.getFlowTetrino().getY()-1;
		Point temp1=pos(new Point(y, x));
		x=(x<0)?0:x;
		for(int i=0;i<temp1.getY();i++){
			for(int j=0;j<temp1.getX();j++){
				Space spc=realtimemap[y+i][x+j];
				if(spc.toString()=="Block"){
					Block temp2=(Block)spc;
					if(temp2.getIsMove()){
						realtimemap[y+i][x+j]=new Space();
					}
				}
			}
		}//유동 블럭을 전부 제거
		if(!tetrino.moveTetrino(moveType)){//이동을 실패한 경우
			realtimemap=temp; 			   //원상 복귀
			for(int i=0;i<temp1.getY();i++){
				for(int j=0;j<temp1.getX();j++){
					Space spc=realtimemap[y+i][x+j];
					if(spc.toString()=="Block"){
						Block temp2=(Block)spc;
						if(temp2.getIsMove()){
							((Block)realtimemap[y+i][x+j]).stateChange();
						}
					}
				}
			}
			return false;						   //함수 종료
		}
		//테트리노의 이동
		x=tetrino.getFlowTetrino().getX()-3;
		y=tetrino.getFlowTetrino().getY()-1;
		temp1=pos(new Point(y, x));
		x=(x<0)?0:x;
		//테트리노의 이동 위치 재정의
		for(int i=0;i<temp1.getY();i++){
			for(int j=0;j<temp1.getX();j++){
				Space spc=tetrino.getTetrino()[i][j];
				if(spc.toString()=="Block"){
					Block temp2=(Block)spc;
					if(temp2.getIsMove()){
						realtimemap[i+y][j+x]=spc;
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
		
		Point temp=new Point(5, 6);
		for(int i=0;i<temp.getY();i++){
			for(int j=0;j<temp.getX();j++){
				Space spc = null;
				if(tetrino.getFlowTetrino().getY()+i > TetrisControlManager.getHeight()){
					spc= new Block(DEFULT);
				}else if(x+j<0){
					spc= new Block(DEFULT);
				}else if(x+j>TetrisControlManager.getWidth()){
					spc= new Block(DEFULT);
				}else{
					spc=realtimemap[y+i][x+j];
				}
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
	public Space[][] getRealTimeMap(){
		return realtimemap;
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
	private Point pos(Point pos){
		int x=pos.getX();
		int y=pos.getY();
		int tempx=6;
		int tempy=5;
		
		
		
		if(x+5>=width){
			tempy+=width-(x+5);
		}
		if(y+4>=height){
			tempy+=height-(y+4)-1;
		}
		return new Point(tempy, tempx);
	}
}
