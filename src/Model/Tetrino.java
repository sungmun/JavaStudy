package Model;

import ValueObject.Point;
import ValueObject.Space;

public class Tetrino implements MoveType {

	int mode = 1;
	private TetrinoType type;
	private Point flowtetrino;
	private Space[][] area = new Space[5][6];

	public Tetrino(int[][] tetrino, TetrinoType type) {
		for (int y = 0; y < 4; y++) {
			area[y][0] = null;
			for (int x = 1; x < 5; x++) {
				area[y][x] = (tetrino[y][x - 1] != 1) ? null : new Space(BlockType.FLOW, type);
			}
			area[y][5] = null;
		}
		for (int x = 0; x < 6; x++) {
			area[4][x] = null;
		}
		this.type = type;
	}

	private boolean sideMoveTetrino(int direction) {
		for (int y = 0; y < 5; y++) {
			for (int x = 0; x < 6; x++) {
				if (x + direction < 0 || x + direction >= 6) {
					continue;
				}
				Space spc1 = area[y][x];
				Space spc2 = area[y][x + direction];

				if (spc1 == null || spc1.getIsblock() != BlockType.FLOW) {
					continue;
				}
				if (!(spc2 == null || spc2.getIsblock() == BlockType.FLOW)) {
					return false;
				}
			}
		}
		flowtetrino = new Point(flowtetrino.getY(), flowtetrino.getX() + direction);

		return true;
	}

	private boolean downMoveTetrino() {
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 6; x++) {

				Space spc1;
				Space spc2;
				try {
					spc1 = area[y][x];
					spc2 = area[y + 1][x];

					if (spc1 == null || spc1.getIsblock() != BlockType.FLOW) {
						continue;
					}

					if (!(spc2 == null || spc2.getIsblock() == BlockType.FLOW)) {
						return false;
					}
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		flowtetrino = new Point(flowtetrino.getY() + 1, flowtetrino.getX());
		return true;
	}

	public boolean moveTetrino(int direction) {
		switch (direction) {
		case RIGHT:
		case LEFT:
			return sideMoveTetrino(direction);
		case DOWN:
			return downMoveTetrino();
		case TURN:
			return turnTetrino();
		case DROP:
		default:
			return false;
		}
	}

	public boolean turnTetrino() {
		Space[][] temp = new Space[4][6];
		Space[][] temp1 = new Space[4][6];
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 6; x++) {
				temp[y][x] = null;
				if (area[y][x] == null) {
				} else {
					temp1[y][x] = (area[y][x].getIsblock() != BlockType.FLOW) ? area[y][x] : null;
				}

			}
		}
		temp[0][2] = area[0][4];
		temp[0][3] = area[1][4];
		temp[0][4] = area[2][4];
		temp[1][2] = area[0][3];
		temp[1][3] = area[1][3];
		temp[1][4] = area[2][3];
		temp[2][2] = area[0][2];
		temp[2][3] = area[1][2];
		temp[2][4] = area[2][2];
		temp[1][1] = area[3][3];
		temp[3][3] = area[1][1];
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 6; x++) {
				if (temp[y][x] == null) {
					continue;
				}
				if (area[y][x] == null) {
				} else if (area[y][x].getIsblock() == BlockType.FIXED || area[y][x].getIsblock() == BlockType.ETC) {
					return false;
				}
				temp1[y][x] = temp[y][x];
			}
		}
		
		for (int i = 0; i < temp.length; i++) {
			area[i] = temp1[i].clone();
		}
		mode++;
		return true;
	}

	public Point getFlowTetrino() {
		return flowtetrino;
	}

	public Space[][] getTetrino() {
		return area;
	}

	public Space[][] getArea() {
		return area;
	}

	public TetrinoType getType() {
		return type;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public void setArea(int y, int x, Space spc) {
		area[y][x] = spc;
	}

	public void setFlowTetrino(Point pos) {
		flowtetrino = pos;
	}

	public void setType(TetrinoType type) {
		this.type = type;
	}

}
