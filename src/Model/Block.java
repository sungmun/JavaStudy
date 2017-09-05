package Model;

public class Block extends Space{
	

	public Block(int type) {
		setType(type);
		this.setIsblock(Space.FLOW);
	}
	public Block() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Block";
	}
}
