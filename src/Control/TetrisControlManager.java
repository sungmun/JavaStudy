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
			for (int x=1;x<4;x++) {
				realtimemap[y][x+createposition]=tetrino.getTetrino()[y][x];
			}
		}
		tetrino.setFlowTetrino(new Point(1, createposition+3));
	}
	public boolean TetrinoBlockMove(int moveType){//��Ʈ������ �̵��� ���� ���� ���� ��������
		Space[][] temp=realtimemap;
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
		}//���� ���� ���� ����
		if(!tetrino.moveTetrino(moveType)){//�̵��� ������ ���
			realtimemap=temp; 			   //���� ����
			return false;						   //�Լ� ����
		}
		//��Ʈ������ �̵�
		x=tetrino.getFlowTetrino().getX()-3;
		y=tetrino.getFlowTetrino().getY()-1;
		temp1=pos(new Point(y, x));
		x=(x<0)?0:x;
		//��Ʈ������ �̵� ��ġ ������
		for(int i=0;i<temp1.getY();i++){
			for(int j=0;j<temp1.getX();j++){
				Space spc=tetrino.getTetrino()[i][j];
				if(spc.toString()=="Block"){
					Block temp2=(Block)spc;
					if(temp2.getIsMove()){
						realtimemap[i+y][j+i]=spc;
					}
				}
			}
		}
		//�̵��� ��Ʈ���븦 �ǽð� �ʿ� �ݿ�
		aroundSearch();
		//��Ʈ������ �ֺ� ������ �Է�
		return true;
	}
	private void aroundSearch(){
		int x=tetrino.getFlowTetrino().getX()-3;
		int y=tetrino.getFlowTetrino().getY()-1;
		
		Point temp=pos(new Point(y, x));
		x=(x<0)?0:x;
		for(int i=0;i<temp.getY();i++){
			for(int j=0;j<temp.getX();j++){
				Space spc=realtimemap[y+i][x+j];
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
