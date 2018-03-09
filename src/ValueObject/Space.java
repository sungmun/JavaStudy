package ValueObject;

import Model.BlockType;
import Model.TetrinoType;

public class Space {
	private BlockType isblock = BlockType.FLOW;
	private TetrinoType type = TetrinoType.DEFULT;

	public Space() {
	}

	public Space(BlockType isblock, TetrinoType type) {
		this.isblock = isblock;
		this.type = type;
	}

	public Space(BlockType isblock) {
		this.isblock = isblock;
	}

	public BlockType getIsblock() {
		return isblock;
	}

	public void setIsblock(BlockType isblock) {
		this.isblock = isblock;
	}

	public BlockType getSpace() {
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
