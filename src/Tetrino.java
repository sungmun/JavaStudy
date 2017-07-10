public class Tetrino implements BlockAxis,MoveType{

	int mode=1;//1부터 4까지 있다
	private Point flowtetrino; 				//테트리노의 현재 위치
	//테트리노의 현재 위치는 area를 기준으로 보면 [4][1]의 위치로 표시하며
	//이 위치는 배열 0까지 포함한 위치이다.
	
	private Space[][] area=new Space[5][4];
	
	public Tetrino(int[][] tetrino) {
		for(int y=0;y<4;y++){
			for(int x=1;x<5;x++){
				if(tetrino[y][x]==1){
					area[y][x]=new Block();
				}
			}
		}
	}
	public boolean moveTetrino(int direction){
		return (Math.abs(direction)==1)?sideMoveTetrino(direction):downTetrino();
	}
	private boolean sideMoveTetrino(int direction){ //오른쪽은 +1 왼쪽은 -1
		if((direction==RIGHT&&(flowtetrino.getX()+3>21))||(direction==LEFT&&(flowtetrino.getX()-3<0))){
			return false;	//오른 쪽 끝에서 더 오른 쪽으로 가려고 하거나 외쪽으로 가려고 하는 경우
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
	
	private boolean downTetrino(){
		for(int y=0;y<5;y++){
			for(int x=0;x<6;x++){
				if(area[x][y].getSpace()&&((Block)area[x][y]).ismove){
					if(area[x][y+1].getSpace()&&(!((Block)area[x][y+1]).ismove)){
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
					if(temp3.ismove){//공간이 비어있지 않고 
						blockTurnMove(x,y,(Block[][])temp);//움직이는 블럭일때	
					}
				}
			}
		}
		for(int y=0;y<4;y++){
			for(int x=1;x<5;x++){
				if(area[y][x].getSpace()&&(!((Block)area[y][x]).ismove)){//블럭이 고정블럭인가?
					if(temp[y][x].getSpace()){		//넣을 게 블럭인가?
						area=temp1;					//이동한 블럭을 원상 복귀후
						return false;				//그러면 블럭 회전 실패
					}
				}
				area[y][x]=temp[y][x];				//블럭을 이동
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
		area[x][y]=spc;
	}
	public Space[][] getTetrino() {
		return area;
	}

}
