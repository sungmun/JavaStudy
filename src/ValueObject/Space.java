package ValueObject;

import Model.BlockType;
import Model.SpaceSize;
import Model.TetrinoType;

public class Space implements SpaceSize, BlockType {
	private int isblock = FLOW;
	private TetrinoType type = TetrinoType.DEFULT;

	public Space(int isblock, TetrinoType type) {
		this.isblock = isblock;
		this.type = type;
	}

	public Space(int isblock) {
		this.isblock = isblock;
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

	public TetrinoType getType() {
		return type;
	}

	public void setType(TetrinoType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Space";
	}

	public boolean equals(Space spc) {
		if (spc == null)
			return false;
		return (isblock == spc.isblock) ? true : false;
	}
}
