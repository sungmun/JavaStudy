package Control;

import java.util.Arrays;

import Model.Block;
import Model.CreateBlock;
import Model.Map;
import Model.Space;
import Model.Tetrino;
import Model.TetrinoType;

public class TetrisControlManager implements TetrinoType {

	private static int height = 23;
	private static int width = 10;

	public Space[][] realtimemap;
	public Tetrino tetrino;
 
	static TetrisControlManager tetrismanager = null;

	private TetrisControlManager() {
		Map tetris = new Map(width, height);
		realtimemap = tetris.getMap();
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
				
				Space spc = null;
				if (tetrino.getFlowTetrino().getY() + i > TetrisControlManager.getHeight()) {
					spc = new Block();
					spc.setIsblock(Space.ETC);
				} else if (x + j < 0) {
					spc = new Block();
					spc.setIsblock(Space.ETC);
				} else if (x + j > TetrisControlManager.getWidth() - 1) {
					spc = new Block();
					spc.setIsblock(Space.ETC);
				} else {
					spc = realtimemap[y + i][x + j];
				}
				if (!tetrino.getArea()[i][j].equals(spc)) {
					tetrino.setArea(i, j, spc);
				}
			}
		}

		tetrino.viewPointCheck();
	}

	public static TetrisControlManager createTetrisControlManager() {
		if (tetrismanager == null) {
			tetrismanager = new TetrisControlManager();
		}
		return tetrismanager;
	}

	public static TetrisControlManager getTetrisControlManager() {
		return tetrismanager;
	}

	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}

	public void createBlock() {
		int createposition = width / 3;
		tetrino = CreateBlock.tetrinoRandomCreate();
		for (int y = 0; y < 4; y++) {
			for (int x = 1; x < 5; x++) {
				realtimemap[y][x + createposition] = tetrino.getTetrino()[y][x];
			}
		}
		tetrino.setFlowTetrino(new Point(1, createposition + 2));
		tetrino.viewPointCheck();
	}

	public void lineClear(int clearline, Point pos) {
		Space[] spc = new Space[width];
		int startpos = pos.getY() - 1;

		for (int i = 0; i < 4; i++) {
			int bit = 0x01 << i;
			if ((clearline & bit) == bit) {
				realtimemap[startpos + i] = spc;
			}
		}
		for (int i = startpos + 4; i >= 0; i--) {
			if (Arrays.equals(spc, realtimemap[i])) {
				for (int j = i; j > 0; i--) {
					realtimemap[j] = realtimemap[j - 1];
				}
				realtimemap[0] = spc;
			}
		}

	}

	public void setNowTetrino(Tetrino ttrn) {
		tetrino = ttrn;
	}

	public void paint() {
		System.out.print("flowposX: " + tetrino.getFlowTetrino().getX());
		System.out.print(" flowposY: " + tetrino.getFlowTetrino().getY());
		System.out.println();
		System.out.println("type: " + tetrino.getType());
		for (Space[] spaces : tetrino.getArea()) {
			for (Space space : spaces) {

				System.out.print((space.getIsblock() >= 0 ? " " : "") + space.getIsblock());
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}

	public void mapPaint() {
		System.out.print("flowposX: " + tetrino.getFlowTetrino().getX());
		System.out.print(" flowposY: " + tetrino.getFlowTetrino().getY());
		System.out.println();
		System.out.println("type: " + tetrino.getType());
		for (Space[] spaces : realtimemap) {
			for (Space space : spaces) {
				System.out.print((space.getIsblock() >= 0 ? " " : "") + space.getIsblock());
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}

	public boolean TetrinoBlockMove(int moveType) {// 테트리노의 이동시 현재 맵의 정보 리프래쉬
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
		} // 유동 블럭을 전부 제거
		if (!tetrino.moveTetrino(moveType)) {// 이동을 실패한 경우
			// 원상 복귀
			
			for (int i = 0; i < temp.length; i++) {
				realtimemap[i] = temp[i].clone();
			}
			if (Math.abs(moveType) == 1) {
				return false;
			}

			for (int i = 0; i < realtimemap.length; i++) {
				for (int j = 0; j < realtimemap[i].length; j++) {
					if (realtimemap[i][j].getIsblock() == Space.FLOW) {
						realtimemap[i][j].setIsblock(Space.FIXED);
					}
				}
			}
			return false; // 함수 종료
		}
		// 테트리노의 이동
		x = tetrino.getFlowTetrino().getX() - 3;
		y = tetrino.getFlowTetrino().getY() - 1;
		temp1 = pos(new Point(y, x));
		
		// 테트리노의 이동 위치 재정의
		for (int i = 0; i < temp1.getY(); i++) {
			for (int j = 0; j < temp1.getX(); j++) {
				try {
					Space spc = tetrino.getTetrino()[i][j];
				if (spc.getIsblock() == Space.FLOW) {
					realtimemap[i + y][j + x] = spc;
				} 
				} catch (ArrayIndexOutOfBoundsException e) {
					System.err.println("TetrisControlManager.TetrinoBlockMove()");
					TetrisControlManager.getTetrisControlManager().mapPaint();
					System.err.println("temp : "+temp1.toString());
					System.err.println("오류가 난 위치 : [j="+j+",i="+i+"]");
					System.err.println();
				}
			}
		}
		tetrino.setFlowTetrino(new Point(y + 1, x + 3));
		// 이동한 테트리노의 위치 저장
		// 이동한 테트리노를 실시간 맵에 반영
		aroundSearch();
		// 테트리노의 주변 정보를 입력
		return true;
	}

	public boolean gameOverCheack(Point pos) {
		if (getNowTetrino().getFlowTetrino().getY() >= 5) {
			return false;
		}

		Space[][] cheackmap;

		{
			cheackmap = getRangeRealTimeMap(0);
			Space[][] spc = new Space[3][width];
			spc[0] = cheackmap[0];
			spc[1] = cheackmap[1];
			spc[2] = cheackmap[2];
			cheackmap = spc;
		}
		for (Space[] spaces : cheackmap) {
			for (Space space : spaces) {
				if (space.toString() == "Block") {
					return true;
				}
			}
		}
		return false;
	}

	public Space[][] getRangeRealTimeMap(int height) {
		Space[][] spc = new Space[4][width];
		for (int i = 0; i < 4; i++) {
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
		for (int i = 0; i < 4; i++) {
			for (int j = 0; i < getWidth(); j++) {
				if (spc[i][j].toString() == "Space") {
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
}
