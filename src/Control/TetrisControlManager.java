package Control;

import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.Timer;

import Model.CreateBlock;
import Model.Map;
import Model.MoveType;
import Model.Point;
import Model.Space;
import Model.Tetrino;
import Model.TetrinoType;

public class TetrisControlManager implements TetrinoType, MoveType {

	private static int height = 23;
	private static int width = 10;

	protected int score = 0;
	protected int level = 1;

	protected Tetrino save_block = null;
	protected Tetrino next_block = null;

	protected Space[][] realtimemap;
	public Tetrino tetrino;

	protected JPanel panel = null;
	protected Timer time = null;

	protected TetrisControlManager() {
		realtimemap = new Map(width, height).getMap();
	}

	public void saveBlock() {
		int y = 0;
		for (Space[] spaces : realtimemap) {
			int x=0;
			for (Space space : spaces) {
				if (space.getIsblock() == Space.FLOW) {
					realtimemap[y][x] = new Space();
				}
				x++;
			}
			y++;
		}
		if (this.save_block == null) {
			setSave_block(tetrino);
			createBlock();
		} else {
			Tetrino temp_block = tetrino;
			tetrino = save_block;
			setSave_block(temp_block);
		}
	}

	private Point pos(Point pos) {
		int x = pos.getX();
		int y = pos.getY();
		int tempx = 6;
		int tempy = 5;

		if (x + 6 >= width) {
			tempx += width - (x + 6);
		}
		if (y + 5 >= height) {
			tempy += height - (y + 5);
		}
		return new Point(tempy, tempx);
	}

	private void aroundSearch() {
		int x = tetrino.getFlowTetrino().getX() - 3;
		int y = tetrino.getFlowTetrino().getY() - 1;

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 6; j++) {
				Space spc = (tetrino.getFlowTetrino().getY() + i > TetrisControlManager.getHeight() || x + j < 0
						|| x + j > TetrisControlManager.getWidth() - 1) ? new Space(Space.ETC)
								: realtimemap[y + i][x + j];
				if (!tetrino.getArea()[i][j].equals(spc)) {
					tetrino.setArea(i, j, spc);
				}
			}
		}
	}

	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}

	public void createBlock() {
		int createposition = width / 3;
		tetrino = (next_block == null) ? CreateBlock.tetrinoRandomCreate() : next_block;
		setNext_block(CreateBlock.tetrinoRandomCreate());
		for (int y = 0; y < 4; y++) {
			for (int x = 1; x < 5; x++) {
				realtimemap[y][x + createposition] = tetrino.getTetrino()[y][x];
			}
		}
		tetrino.setFlowTetrino(new Point(1, createposition + 2));
	}

	public void lineClear(int clearline, Point pos) {
		Space[] spc = new Space[width];
		int startpos = pos.getY() - 1;
		int endpos = 4;
		boolean success;

		if (startpos > 17) {
			endpos = TetrisControlManager.height - startpos - 1;
		}
		for (int x = 0; x < width; x++) {
			spc[x] = new Space();
		}
		int score = 0;
		for (int i = 0; i <= endpos; i++) {
			int bit = 0x01 << i;
			if ((clearline & bit) == bit) {
				realtimemap[startpos + i] = spc.clone();
				score += 1 + score;
			}
		}
		
		setScore(getScore()+score*100);
		setLevel((int) (this.score / 1000));
		
		success = true;
		while (success) {
			success = false;
			for (int i = startpos + endpos; i > startpos + endpos - 4; i--) {
				if (Arrays.equals(spc, realtimemap[i])) {
					for (int j = i; j > 0; j--) {
						realtimemap[j] = realtimemap[j - 1].clone();
					}
					realtimemap[0] = spc;
					success = true;
				}
			}
		}
	}

	public void setNowTetrino(Tetrino ttrn) {
		tetrino = ttrn;
	}

	public void TetrinoBlockDropMove() {
		boolean sucess = true;
		while (sucess) {
			sucess = TetrinoBlockMove(DOWN);
		}
	}

	public boolean TetrinoBlockMove(int moveType) {// ��Ʈ������ �̵��� ���� ���� ���� ��������
		Space[][] temp = new Space[height][width];
		for (int i = 0; i < realtimemap.length; i++) {
			temp[i] = realtimemap[i].clone();
		}
		int x = tetrino.getFlowTetrino().getX() - 3;
		int y = tetrino.getFlowTetrino().getY() - 1;
		Point temp1 = pos(new Point(y, x));
		x = (x < 0) ? 0 : x;
		for (int i = 0; i < temp1.getY(); i++) {
			for (int j = 0; j < temp1.getX(); j++) {
				Space spc = realtimemap[y + i][x + j];
				if (spc.getIsblock() == Space.FLOW) {
					realtimemap[y + i][x + j] = new Space();
				}
			}
		} // ���� ���� ���� ����
		if (!tetrino.moveTetrino(moveType)) {// �̵��� ������ ���
			// ���� ����

			for (int i = 0; i < temp.length; i++) {
				realtimemap[i] = temp[i].clone();
			}
			if (moveType == RIGHT || moveType == LEFT || moveType == TURN) {
				return false;
			}

			for (int i = 0; i < realtimemap.length; i++) {
				for (int j = 0; j < realtimemap[i].length; j++) {
					if (realtimemap[i][j].getIsblock() == Space.FLOW) {
						realtimemap[i][j].setIsblock(Space.FIXED);
					}
				}
			}
			return false; // �Լ� ����
		}
		// ��Ʈ������ �̵�
		x = tetrino.getFlowTetrino().getX() - 3;
		y = tetrino.getFlowTetrino().getY() - 1;
		temp1 = pos(new Point(y, x));

		// ��Ʈ������ �̵� ��ġ ������
		for (int i = 0; i < temp1.getY(); i++) {
			for (int j = 0; j < temp1.getX(); j++) {
				Space spc = tetrino.getTetrino()[i][j];
				if (spc.getIsblock() == Space.FLOW) {
					realtimemap[i + y][j + x] = spc;
				}
			}
		}
		tetrino.setFlowTetrino(new Point(y + 1, x + 3));
		// �̵��� ��Ʈ������ ��ġ ����
		// �̵��� ��Ʈ���븦 �ǽð� �ʿ� �ݿ�
		aroundSearch();
		// ��Ʈ������ �ֺ� ������ �Է�
		return true;
	}

	public boolean gameOverCheack(Point pos) {
		if (getNowTetrino().getFlowTetrino().getY() >= 5) {
			return false;
		}

		for (Space[] spaces : getRangeRealTimeMap(0)) {
			for (Space space : spaces) {
				if (space.getIsblock() == Space.FIXED) {
					return true;
				}
			}
		}
		return false;
	}

	public Space[][] getRangeRealTimeMap(int height) {

		int temp_height = 4;
		if (height > 17) {
			temp_height = TetrisControlManager.height - height - 1;
		}
		Space[][] spc = new Space[temp_height + 1][width];
		for (int i = 0; i <= temp_height; i++) {
			for (int j = 0; j < width; j++) {
				spc[i][j] = realtimemap[i + height][j];
			}
		}
		return spc;
	}

	public Space[][] getRealTimeMap() {
		return realtimemap;
	}

	public Tetrino getNowTetrino() {
		return tetrino;
	}

	public int lineCheack(Point pos) {
		Space[][] spc = getRangeRealTimeMap(pos.getY() - 1);
		boolean cheack = false;
		int returnvalue = 0;

		for (int i = 0; i < spc.length; i++) {
			for (int j = 0; j < spc[i].length; j++) {
				if (spc[i][j].getIsblock() == Space.SPACE) {
					cheack = false;
					break;
				} else {
					cheack = true;
				}
			}
			if (cheack) {
				returnvalue |= 0x01 << i;
			}
		}
		return returnvalue;
	}

	public Tetrino getSave_block() {
		return save_block;
	}

	public Tetrino getNext_block() {
		return next_block;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void rePaint() {
		System.gc();
		panel.repaint();
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel pa) {
		this.panel = pa;
	}

	public Timer getTime() {
		return time;
	}

	public void setTime(Timer time) {
		this.time = time;
		time.start();
	}

	public void setRealtimemap(Space[][] realtimemap) {
		this.realtimemap = realtimemap;
	}

	public void setSave_block(Tetrino save_block) {
		this.save_block = save_block;
	}

	public void setNext_block(Tetrino next_block) {
		this.next_block = next_block;
	}

}
