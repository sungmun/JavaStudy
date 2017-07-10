
public class Block extends Space{
	boolean ismove=true;		  //블럭고정이면 false, 블럭유동이면 true
	
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
	public static boolean BlockCheack(Space spc){
		if(!spc.getSpace()){
			return false;
		}
		spc=new Block();
		return true;
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
