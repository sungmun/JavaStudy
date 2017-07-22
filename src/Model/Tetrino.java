package Model;
import Control.Point;
import Control.TetrisControlManager;

public class Tetrino implements BlockAxis,MoveType,TetrinoType{

	int mode=1;//1���� 4���� �ִ�
	private Point flowtetrino; 				//��Ʈ������ ���� ��ġ
	//��Ʈ������ ���� ��ġ�� area�� �������� ���� [4][1]�� ��ġ�� ǥ���ϸ�
	//�� ��ġ�� �迭 0���� ������ ��ġ�̴�.
	
	private Space[][] area=new Space[5][6];
	
	public Tetrino(int[][] tetrino,int type) {
		for(int y=0;y<4;y++){
			area[y][0]=new Space();
			for(int x=1;x<5;x++){
				if(tetrino[y][x-1]==1){
					area[y][x]=new Block(type);
				}else{
					area[y][x]=new Space();
				}
			}
			area[y][5]=new Space();
		}
		for(int x=0;x<6;x++){
			area[4][x]=new Space();
		}
	}
	public boolean moveTetrino(int direction){
		return (Math.abs(direction)==1)?sideMoveTetrino(direction):downMoveTetrino();
	}
	private boolean sideMoveTetrino(int direction){ //�������� +1 ������ -1
		if((direction==RIGHT&&(flowtetrino.getX()+3>21))||(direction==LEFT&&(flowtetrino.getX()-3<0))){
			return false;	//���� �� ������ �� ���� ������ ������ �ϰų� �������� ������ �ϴ� ���
		}
		for(int y=0;y<5;y++){
			for(int x=0;x<6;x++){
				if(area[x][y].toString()==area[x+direction][y].toString()){
					if(area[x][y].toString()=="Space"){
						return true;
					}else if(area[x][y].equals(area[x+direction][y])){
						return false;
					}
				}
			}
		}
		flowtetrino=new Point(flowtetrino.getY(), flowtetrino.getX()+1);
		return true;
	}
	
	private boolean downMoveTetrino(){
		for(int y=0;y<4;y++){
			for(int x=0;x<6;x++){
				if(area[y][x].toString()=="Block"&&!(((Block)area[y][x]).getType()==DEFULT)&&area[y+1][x].toString()=="Block"){
					Block spc1=(Block)area[y][x];
					Block spc2=(Block)area[y+1][x];
					if((spc1.ismove&&(!spc2.ismove))){
						return false;
					}
				}
				
			}
		}
		flowtetrino=new Point(flowtetrino.getY()+1, flowtetrino.getX());
		return true;
	}
	public boolean turnTetrino(){
		Space[][] temp=new Space[5][4];
		Space[][] temp1=area;
		for(int y=0;y<5;y++){
			for(int x=0;x<6;x++){
				if(area[y][x].toString()=="Block"){
					Block temp3=(Block)area[y][x];
					if(temp3.ismove){//������ ������� �ʰ� 
						blockTurnMove(x,y,(Block[][])temp);//�����̴� ���϶�	
					}
				}
			}
		}
		for(int y=0;y<4;y++){
			for(int x=1;x<5;x++){
				if(area[y][x].toString()=="Block"){
					Block temp3=(Block)area[y][x];
					if(!temp3.ismove){//���� �������ΰ�?
						if(temp[y][x].getSpace()){		//���� �� ���ΰ�?
							area=temp1;					//�̵��� ���� ���� ������
							return false;				//�׷��� �� ȸ�� ����
						}
					}
				}
				area[y][x]=temp[y][x];				//���� �̵�
			}
		}
		mode++;
		return true;
	}
	public void blockTurnMove(int x,int y,Block[][] blk) {
		Block temp=(Block)area[y][x];
		Point pos=new Point(x,y);
		
		if(pos.equals(new Point(1,3))){
			return;
		}

		if(pos.equals(new Point(1,1))){
			blk[3][3]=temp;
			return;
		}else if(pos.equals(new Point(3,3))){
			blk[1][1]=temp;
			return;
		}
		
		for(int i=0;i<5;i++){
			if(pos.equals(SUBURB[i])){
				pos=SUBURB[i+1];
				break;
			}else if(pos.equals(CENTER[i])){
				pos=CENTER[i+1];;
				break;
			}
		}
		blk[pos.getY()][pos.getX()]=temp;
	}
	public void setFlowTetrino(Point pos){
		flowtetrino=pos;
	}
	public Point getFlowTetrino(){
		return flowtetrino;
	}
	public void setArea(int x,int y,Space spc){
		area[y][x]=spc;
	}
	public Space[][] getTetrino() {
		return area;
	}

}
