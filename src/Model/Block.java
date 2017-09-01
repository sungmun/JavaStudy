package Model;

public class Block extends Space{
	private int type=0;

	public Block(int type) {
		this.type=type;
		this.setIsblock(Space.FLOW);
	}
	public Block() {
		// TODO Auto-generated constructor stub
	}
	public int getType(){
		return type;
	}
	@Override
	public String toString() {
		return "Block";
	}
}
