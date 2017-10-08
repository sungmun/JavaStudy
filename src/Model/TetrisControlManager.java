package Model;

import java.util.Arrays;

import Control.PlayerInformation;
import Model.ValueObject.Map;
import Model.ValueObject.Point;
import Model.ValueObject.Space;

public class TetrisControlManager implements TetrinoType, MoveType {

	private static int height = 23;
	private static int width = 10;

	public PlayerInformation info;

	protected int save_block = 0;
	protected int next_block = 0;

	protected Space[][] realtimemap;
	public Tetrino tetrino;

	protected TetrisControlManager() {
		realtimemap = new Map(width, height).getMap();
	}

	public void saveBlock() {
		int y = 0;
		for (Space[] spaces : realtimemap) {
			int x = 0;
			for (Space space : spaces) {
				if (space.getIsblock() == BlockType.FLOW) {
					realtimemap[y][x] = new Space();
				}
				x++;
			}
			y++;
		}

		if (this.save_block == 0) {
			setSave_block(tetrino.getType());
			createBlock();
		} else {
			int temp = tetrino.getType();
			createChoiceBlock(save_block);
			setSave_block(temp);
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
						|| x + j > TetrisControlManager.getWidth() - 1) ? new Space(BlockType.ETC)
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
		tetrino = (next_block == 0) ? CreateBlock.tetrinoRandomCreate() : CreateBlock.tetrinoChoiceCreate(next_block);
		setNext_block((int) (Math.random() * 10) % 7 + 1);
		for (int y = 0; y < 4; y++) {
			for (int x = 1; x < 5; x++) {
				realtimemap[y][x + createposition] = tetrino.getTetrino()[y][x];
			}
		}
		tetrino.setFlowTetrino(new Point(1, createposition + 2));
	}

	public void createChoiceBlock(int Type) {
		int createposition = width / 3;
		tetrino = CreateBlock.tetrinoChoiceCreate(Type);
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

		info.setScore(info.getScore() + score * 100);
		int level=info.getScore() / 1000;
		if(level>info.getLevel()) {
			info.setLevel(level);
		}
		

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

	public boolean TetrinoBlockMove(int moveType) {
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
				if (spc.getIsblock() == BlockType.FLOW) {
					realtimemap[y + i][x + j] = new Space();
				}
			}
		}
		if (!tetrino.moveTetrino(moveType)) {

			for (int i = 0; i < temp.length; i++) {
				realtimemap[i] = temp[i].clone();
			}
			if (moveType == RIGHT || moveType == LEFT || moveType == TURN) {
				return false;
			}

			for (int i = 0; i < realtimemap.length; i++) {
				for (int j = 0; j < realtimemap[i].length; j++) {
					if (realtimemap[i][j].getIsblock() == BlockType.FLOW) {
						realtimemap[i][j].setIsblock(BlockType.FIXED);
					}
				}
			}
			return false;
		}
		x = tetrino.getFlowTetrino().getX() - 3;
		y = tetrino.getFlowTetrino().getY() - 1;
		temp1 = pos(new Point(y, x));

		for (int i = 0; i < temp1.getY(); i++) {
			for (int j = 0; j < temp1.getX(); j++) {
				Space spc = tetrino.getTetrino()[i][j];
				if (spc.getIsblock() == BlockType.FLOW) {
					realtimemap[i + y][j + x] = spc;
				}
			}
		}
		tetrino.setFlowTetrino(new Point(y + 1, x + 3));
		aroundSearch();
		return true;
	}

	public boolean gameOverCheack(Point pos) {
		if (getNowTetrino().getFlowTetrino().getY() >= 5) {
			return false;
		}

		for (Space[] spaces : getRangeRealTimeMap(0)) {
			for (Space space : spaces) {
				if (space.getIsblock() == BlockType.FIXED) {
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
				if (spc[i][j].getIsblock() == BlockType.SPACE) {
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

	public int getSave_block() {
		return save_block;
	}

	public int getNext_block() {
		return next_block;
	}

	public void setRealtimemap(Space[][] realtimemap) {
		this.realtimemap = realtimemap;
	}

	public void setSave_block(int tetrino) {
		this.save_block = tetrino;
	}

	public void setNext_block(int next_block) {
		this.next_block = next_block;
	}

}
