package Model;

public class Space implements SpaceSize,BlockType{
	private int isblock=SPACE;
	public int getIsblock() {
		return isblock;
	}
	public void setIsblock(int isblock) {
		this.isblock = isblock;
	}
	public int getSpace(){
		return isblock;
	}
	@Override
	public String toString() {
		return "Space";
	}
	public boolean equals(Space spc) {
		return (isblock==spc.isblock)?true:false; 
	}
}
