package Model;

public class Block extends Space implements TetrinoType{
	boolean ismove=true;		  //블럭고정이면 false, 블럭유동이면 true
	private int type;
	public Block(int type) {
		this.type=type;
		if(type==DEFULT){
			ismove=false;
		}
	}
	public void stateChange(){
		this.ismove=false;
		spaceChange();
	}
	public static boolean SpaceCheack(Space spc) {
		return (spc.getSpace()&&isLiquidity((Block)spc))?true:false;
	}
	private static boolean isLiquidity(Block bck){
		return bck.ismove;
	}
	public int getType(){
		return type;
	}
	public boolean getIsMove() {
		return ismove;
	}
	@Override
	public String toString() {
		return "Block";
	}
	public boolean equals(Block blk) {
		// TODO Auto-generated method stub
		return (blk.ismove==ismove);
	}
}
