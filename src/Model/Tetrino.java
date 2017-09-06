package Model;

import Control.Point;

public class Tetrino implements MoveType, TetrinoType {

	int mode = 1;// 1부터 4까지 있다
	private int type;
	private Point flowtetrino; // 테트리노의 현재 위치
	// 테트리노의 현재 위치는 area를 기준으로 보면 [4][1]의 위치로 표시하며
	// 이 위치는 배열 0까지 포함한 위치이다.
	private Space[][] area = new Space[5][6];

	public Tetrino(int[][] tetrino, int type) {
		for (int y = 0; y < 4; y++) {
			area[y][0] = new Space();
			for (int x = 1; x < 5; x++) {
				area[y][x] = (tetrino[y][x - 1] != 1) ? new Space() : new Block(type);
			}
			area[y][5] = new Space();
		}
		for (int x = 0; x < 6; x++) {
			area[4][x] = new Space();
		}
		this.type = type;
	}

	private boolean sideMoveTetrino(int direction) { // 오른쪽은 +1 왼쪽은 -1
		for (int y = 0; y < 5; y++) {
			for (int x = 0; x < 6; x++) {
				if (x + direction < 0 || x + direction >= 6) {
					continue;
				}
				Space spc1 = area[y][x];
				Space spc2 = area[y][x + direction];
				if (spc1.getIsblock() == Block.ETC) {
					continue;
				} // 확인하는 공간이 예외적인 공간이면 체크를 그만두고 다음 공간을 확인한다.
				if (spc1.getIsblock() == Space.FLOW) {
					if (spc2.getIsblock() == Space.FIXED || (spc2.getIsblock() == Space.ETC)) {
						return false;// 확인하는 공간에 유동블록이 있으면서 그아래 공간은 고정블록이면 이동을 정지
					}
				}
			}
		}
		flowtetrino = new Point(flowtetrino.getY(), flowtetrino.getX() + direction);

		return true;
	}

	private boolean downMoveTetrino() {
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 6; x++) {
				Space spc1 = area[y][x];
				Space spc2 = area[y + 1][x];

				if (spc1.getIsblock() == Block.ETC) {
					continue;
				} // 확인하는 공간이 예외적인 공간이면 체크를 그만두고 다음 공간을 확인한다.
				if (spc1.getIsblock() == Space.FLOW) {
					if (spc2.getIsblock() == Space.FIXED || (spc2.getIsblock() == Space.ETC)) {
						return false;// 확인하는 공간에 유동블록이 있으면서 그아래 공간은 고정블록이면 이동을 정지
					}
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
				temp[y][x] = new Space();
				temp1[y][x] = (area[y][x].getIsblock() != Space.FLOW) ? area[y][x] : new Space();

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
				if (temp[y][x].getIsblock() != Space.FLOW) {
					continue;
				}
				if (area[y][x].getIsblock() == Space.FIXED || area[y][x].getIsblock() == Space.ETC) {
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

	public int getType() {
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

	public void setType(int type) {
		this.type = type;
	}

}
