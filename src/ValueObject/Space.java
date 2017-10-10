package ValueObject;

import Model.BlockType;
import Model.SpaceSize;
import Model.TetrinoType;

public class Space implements SpaceSize, BlockType, TetrinoType {
	private int isblock = SPACE;
	private int type = 0;
	public Space() {}
	public Space(int isblock) {
		this.isblock = isblock;
	}
	public Space(int isblock,int type) {
		this.isblock = isblock;
		this.type=type;
	}
	public int getIsblock() {
		return isblock;
	}

	public void setIsblock(int isblock) {
		this.isblock = isblock;
	}

	public int getSpace() {
		return isblock;
	}

	public int getType() {
		return type;
	}

	protected void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Space";
	}

	public boolean equals(Space spc) {
		return (isblock == spc.isblock) ? true : false;
	}
}
