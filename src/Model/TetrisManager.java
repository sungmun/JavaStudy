package Model;

import java.util.Arrays;

import Control.ModelToControlConnect;
import Serversynchronization.MessageType;
import Serversynchronization.PlayerInformation;
import Serversynchronization.SocketMessage;
import ValueObject.Map;
import ValueObject.Point;
import ValueObject.Space;

public class TetrisManager implements MoveType {

	private static int height = 23;
	private static int width = 10;

	public PlayerInformation info;

	protected TetrinoType save_block = null;
	protected TetrinoType next_block = null;

	protected Space[][] realtimemap;
	public Tetrino tetrino;

	protected TetrisManager() {
		realtimemap = new Map(width, height).getMap();
	}

	public void saveBlock() {
		int y = 0;
		for (Space[] spaces : realtimemap) {
			int x = 0;
			for (Space space : spaces) {
				if (space.getIsblock() == BlockType.FLOW) {
					realtimemap[y][x] = null;
				}
				x++;
			}
			y++;
		}

		if (this.save_block == null) {
			setSave_block(tetrino.getType());
			createBlock();
		} else {
			TetrinoType temp = tetrino.getType();
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
				Space spc = (tetrino.getFlowTetrino().getY() + i > TetrisManager.getHeight() || x + j < 0
						|| x + j > TetrisManager.getWidth() - 1) ? new Space(BlockType.ETC) : realtimemap[y + i][x + j];
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
		tetrino = (next_block == null) ? new CreateBlock().tetrinoRandomCreate()
				: new CreateBlock().tetrinoChoiceCreate(next_block);
		setNext_block(TetrinoType.getRandomTetrino());
		for (int y = 0; y < 4; y++) {
			for (int x = 1; x < 5; x++) {
				realtimemap[y][x + createposition] = tetrino.getTetrino()[y][x];
			}
		}
		tetrino.setFlowTetrino(new Point(1, createposition + 2));
	}

	public void createChoiceBlock(TetrinoType Type) {
		int createposition = width / 3;
		tetrino = new CreateBlock().tetrinoChoiceCreate(Type);
		for (int y = 0; y < 4; y++) {
			for (int x = 1; x < 5; x++) {
				realtimemap[y][x + createposition] = tetrino.getTetrino()[y][x];
			}
		}
		tetrino.setFlowTetrino(new Point(1, createposition + 2));
	}

	public void lineClear(int clearline, Point pos) {
		Space[][] spc = new Space[2][width];
		final int CLEAR_LINE = 1;
		final int NULL_LINE = 0;
		final int START_POS = pos.getY() - 1;
		final int END_POS = (START_POS > 17) ? TetrisManager.height - START_POS - 1 : 4;
		boolean success;

		for (int x = 0; x < width; x++) {
			spc[NULL_LINE][x] = null;
			spc[CLEAR_LINE][x] = new Space(BlockType.CLEAR);
		}

		int score = 0;
		for (int i = 0; i <= END_POS; i++) {
			// 여기에서 clearline은 0101...과 같은 형식으로 삭제를 해야하는 줄과 삭제를 하지 말아야하는 줄로 나눠져있다
			int bit = 0x01 << i;
			// bit에는 한줄씩 1의 값을 넣어준다
			if ((clearline & bit) == bit) {// clearline의 비트단위로 1이 있으면 실행한다.
				realtimemap[END_POS + i] = spc[CLEAR_LINE].clone();
				ModelToControlConnect.connect.callEvent(caller, new SocketMessage(MessageType.SCORE_MESSAGE, new Integer(score)));
				score += 1 + score;
			}
		}

		success = true;
		while (success) {
			success = false;
			for (int i = START_POS + END_POS; i > START_POS + END_POS - 4; i--) {
				if (Arrays.equals(spc[1], realtimemap[i])) {// 클리어 라인은 검색을 하며, 찾으면, success값을 true로 변경한다.
					for (int j = i; j > 0; j--) {// 찾은 클리어 라인을 기준으로 모든 라인을 한칸씩 내려오개 해준다
						realtimemap[j] = realtimemap[j - 1].clone();
					}
					realtimemap[0] = spc[0].clone();// 가장위에 비어있는 공간을 만들어 준다.
					success = true;// 라인을 찾았으므로 성공표시를 한다.
				}
			}
		}
		
		// info.setScore(info.getScore() + score * 100);
		// int level = info.getScore() / 1000;
		// if (level > info.getLevel()) {
		// info.setLevel(level);
		// }
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
					realtimemap[y + i][x + j] = null;
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
		Space[][] spaces = getRangeRealTimeMap(0);
		for (int i = 0; i < 3; i++) {
			for (Space space : spaces[i]) {
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
			temp_height = TetrisManager.height - height - 1;
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
				if (spc[i][j] == null) {
					returnvalue |= 0x01 << i;
					break;
				}
			}
		}
		return returnvalue;
	}

	public TetrinoType getSave_block() {
		return save_block;
	}

	public TetrinoType getNext_block() {
		return next_block;
	}

	public void setRealtimemap(Space[][] realtimemap) {
		this.realtimemap = realtimemap;
	}

	public void setSave_block(TetrinoType tetrino) {
		this.save_block = tetrino;
	}

	public void setNext_block(TetrinoType next_block) {
		this.next_block = next_block;
	}

}