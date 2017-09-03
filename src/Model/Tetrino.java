package Model;

import java.awt.Rectangle;

import Control.Point;

public class Tetrino implements BlockAxis, MoveType, TetrinoType {

	int mode = 1;// 1���� 4���� �ִ�
	private int type;
	private Point flowtetrino; // ��Ʈ������ ���� ��ġ
	// ��Ʈ������ ���� ��ġ�� area�� �������� ���� [4][1]�� ��ġ�� ǥ���ϸ�
	// �� ��ġ�� �迭 0���� ������ ��ġ�̴�.
	private Space[][] area = new Space[5][6];
	private Rectangle activespace;

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

	private boolean sideMoveTetrino(int direction) { // �������� +1 ������ -1
		if (direction == RIGHT && flowtetrino.getX() + 3 > 21) {
			return false; // ���� �� ������ �� ���� ������ ������ �ϰų� �������� ������ �ϴ� ���
		} else if (direction == LEFT && flowtetrino.getX() - 3 < 0) {
			return false;
		}
		for (int y = 0; y < 5; y++) {
			for (int x = 0; x < 6; x++) {
				if (area[x][y].equals(area[x + direction][y])) {
					return false;
				} else if (area[x][y].getIsblock() == Space.SPACE) {
					return true;
				}
			}
		}
		flowtetrino = new Point(flowtetrino.getY(), flowtetrino.getX() + direction);
		return true;
	}

	private boolean downMoveTetrino() {
		if (getFlowTetrino().getY() == 19) {
			System.out.print("");
		}
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 6; x++) {
				Space spc1 = area[y][x];
				Space spc2 = area[y + 1][x];

				if (spc1.getIsblock() == Block.ETC) {
					continue;
				} // Ȯ���ϴ� ������ �������� �����̸� üũ�� �׸��ΰ� ���� ������ Ȯ���Ѵ�.

				if ((spc1.getIsblock() == Space.FLOW) && (spc2.getIsblock() == Space.FIXED)) {
					return false;// Ȯ���ϴ� ������ ��������� �����鼭 �׾Ʒ� ������ ��������̸� �̵��� ����
				} else if ((spc2.getIsblock() == Space.ETC)) {
					return false;
				} // Ȯ���ϴ� ������ �Ʒ������� �������� �����̸� ���� �̵��� ����
			}
		}
		flowtetrino = new Point(flowtetrino.getY() + 1, flowtetrino.getX());
		return true;
	}

	public boolean moveTetrino(int direction) {
		return (Math.abs(direction) == 1) ? sideMoveTetrino(direction) : downMoveTetrino();
	}

	public boolean turnTetrino() {
		Space[][] temp = new Space[5][4];
		Space[][] temp1 = area;
		for (int y = 0; y < 5; y++) {
			for (int x = 0; x < 6; x++) {
				if (area[y][x].getIsblock() == Space.FLOW) {// ������ ������� �ʰ�
					blockTurnMove(x, y, (Block[][]) temp);// �����̴� ���϶�
				}
			}
		}
		for (int y = 0; y < 4; y++) {
			for (int x = 1; x < 5; x++) {
				if (area[y][x].getIsblock() == Block.FIXED) {// ���� �������ΰ�?
					if (temp[y][x].getIsblock() == Space.SPACE) {
						continue;
					}
					// ���� �� ���ΰ�?
					area = temp1; // �̵��� ���� ���� ������
					return false; // �׷��� �� ȸ�� ����
				}
				area[y][x] = temp[y][x]; // ���� �̵�
			}
		}
		mode++;
		return true;
	}

	public void blockTurnMove(int x, int y, Block[][] blk) {
		Block temp = (Block) area[y][x];
		Point pos = new Point(x, y);

		if (pos.equals(new Point(1, 3))) {
			return;
		}

		if (pos.equals(new Point(1, 1))) {
			blk[3][3] = temp;
			return;
		} else if (pos.equals(new Point(3, 3))) {
			blk[1][1] = temp;
			return;
		}

		for (int i = 0; i < 5; i++) {
			if (pos.equals(SUBURB[i])) {
				pos = SUBURB[i + 1];
				break;
			} else if (pos.equals(CENTER[i])) {
				pos = CENTER[i + 1];
				;
				break;
			}
		}
		blk[pos.getY()][pos.getX()] = temp;
	}

	public void viewPointCheck() {
		int x = 0, y = 0, height = 0, width = 0;
		if (flowtetrino.getY() < 3) {
			y = 3;
			height = flowtetrino.getY() + 2;
		} else if (flowtetrino.getY() > 15) {
			y = 0;
			for (int i = 0; i < 5; i++) {
				if (area[i][3].getIsblock() == Space.ETC) {
					height = i;
					break;
				}
			}
		}
		if (flowtetrino.getX() < 3) {
			for (int i = 0; i < 6; i++) {
				if (area[2][i].getIsblock() == Space.ETC) {
					x = i;
				} else {
					width++;
				}
			}
		} else if (flowtetrino.getX() > 7) {
			x = 0;
			for (int i = 0; i < 6; i++) {
				if (area[2][i].getIsblock() == Space.ETC) {
					width = i;
				}
			}
		}
		height = (height == 0) ? 4 : height;
		width = (width == 0) ? 5 : width;
		activespace = new Rectangle(x, y, width, height);
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

	public Rectangle getActivespace() {
		return activespace;
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

	public void setActivespace(Rectangle activeSpace) {
		System.out.println("Tetrino.setActivespace()");
		this.activespace = activeSpace;
	}

	public void setType(int type) {
		this.type = type;
	}

}
