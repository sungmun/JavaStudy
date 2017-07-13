package Model;

public class Block extends Space{
	boolean ismove=true;		  //�������̸� false, �������̸� true
	
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
