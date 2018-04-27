package Model;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import Control.CallBackEvent;
import Control.EventListener;
import Control.ImagePrint;
import Control.MVC_Connect;
import Control.MainThread;
import Control.UserControl;
import Serversynchronization.User;
import ValueObject.Map;
import ValueObject.Point;
import ValueObject.Space;
import View.LevelPanel;
import View.NextBlockPanel;
import View.PanelForTheUser;
import View.SaveBlockPanel;
import View.ScorePanel;
import View.TetrinoBlockPanel;
import View.BaseClass.GameBasicFrame;
import View.Multe.MultiFrame;
import View.Single.SingleFrame;

public class TetrisManager implements EventListener {

	public static final int HEIGHT = 23;
	public static final int WIDTH = 10;

	final String sentClass = this.getClass().getName();
	private TetrinoType saveBlock = null;
	private TetrinoType nextBlock = null;

	private Space[][] realTimeMap;
	public Tetrino tetrino;

	public TetrisManager() {
		MVC_Connect.ControlToModel.addListener(this);
		realTimeMap = new Map(WIDTH, HEIGHT).getMap();
		createBlock();
	}

	public void saveBlock() {
		int y = 0;
		for (Space[] spaces : realTimeMap) {
			int x = 0;
			for (Space space : spaces) {
				if (space != null && space.getIsblock() == BlockType.FLOW) {
					realTimeMap[y][x] = null;
				}
				x++;
			}
			y++;
		}

		if (this.saveBlock == null) {
			setSaveBlock(tetrino.getType());
			createBlock();
		} else {
			TetrinoType temp = tetrino.getType();
			createChoiceBlock(saveBlock);
			setSaveBlock(temp);
		}
	}

	private Point pos(Point pos) {
		int x = pos.getX();
		int y = pos.getY();
		int tempx = 6;
		int tempy = 5;

		if (x + 6 >= WIDTH) {
			tempx += WIDTH - (x + 6);
		}
		if (y + 5 >= HEIGHT) {
			tempy += HEIGHT - (y + 5);
		}
		return new Point(tempy, tempx);
	}

	private void aroundSearch() {
		int x = tetrino.getFlowTetrino().getX() - 3;
		int y = tetrino.getFlowTetrino().getY() - 1;

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 6; j++) {
				Space spc = ((tetrino.getFlowTetrino().getY() + i > HEIGHT) || (x + j < 0) || (x + j > WIDTH - 1))
						? new Space(BlockType.ETC)
						: realTimeMap[y + i][x + j];
				tetrino.setArea(i, j, spc);
			}
		}
	}

	private void createBlock() {
		final int createPosition = WIDTH / 3;

		if (gameOverCheack()) {
			gameOver();
			return;
		}

		if (nextBlock == null) {
			setNextBlock(TetrinoType.getRandomTetrino());
			tetrino = new CreateBlock().tetrinoRandomCreate();
		} else {
			tetrino = new CreateBlock().tetrinoChoiceCreate(nextBlock);
			setNextBlock(TetrinoType.getRandomTetrino());
		}

		for (int y = 0; y < 4; y++) {
			for (int x = 1; x < 5; x++) {
				if (realTimeMap[y][x + createPosition] != null || tetrino.getTetrino()[y][x] == null) {
					realTimeMap[y][x + createPosition] = tetrino.getTetrino()[y][x];
				} else if (realTimeMap[y][x + createPosition] == null || tetrino.getTetrino()[y][x] != null) {
					realTimeMap[y][x + createPosition] = tetrino.getTetrino()[y][x];
				}
			}
		}

		tetrino.setFlowTetrino(new Point(1, createPosition + 2));
		sendRealTimeMap();
	}

	private void createChoiceBlock(TetrinoType Type) {
		int createposition = WIDTH / 3;
		tetrino = new CreateBlock().tetrinoChoiceCreate(Type);
		for (int y = 0; y < 4; y++) {
			for (int x = 1; x < 5; x++) {
				realTimeMap[y][x + createposition] = tetrino.getTetrino()[y][x];
			}
		}
		tetrino.setFlowTetrino(new Point(1, createposition + 2));
	}

	private void lineClear(int clearline, Point pos) {

		final int CLEAR_LINE = 1;
		final int NULL_LINE = 0;
		final int START_POS = pos.getY() - 1;
		final int END_POS = (START_POS > 17) ? HEIGHT - START_POS - 1 : 4;

		Space[][] spc = new Space[2][WIDTH];
		boolean success;

		for (int x = 0; x < WIDTH; x++) {
			spc[NULL_LINE][x] = null;
			spc[CLEAR_LINE][x] = new Space(BlockType.CLEAR);
		}

		int score = 0;
		for (int i = 0; i <= END_POS; i++) {
			// 여기에서 clearline은 0101...과 같은 형식으로 삭제를 해야하는 줄과 삭제를 하지 말아야하는 줄로 나눠져있다
			int bit = 0x01 << i;
			// bit에는 한줄씩 1의 값을 넣어준다
			if ((clearline & bit) == bit) {// clearline의 비트단위로 1이 있으면 실행한다.
				realTimeMap[START_POS + i] = spc[CLEAR_LINE].clone();
				score += 1 + score;
			}
		}

		success = true;
		while (success) {
			success = false;
			for (int i = START_POS + END_POS; i > START_POS + END_POS - 4; i--) {
				if (Arrays.equals(spc[1], realTimeMap[i])) {// 클리어 라인은 검색을 하며, 찾으면, success값을 true로 변경한다.
					for (int j = i; j > 0; j--) {// 찾은 클리어 라인을 기준으로 모든 라인을 한칸씩 내려오개 해준다
						realTimeMap[j] = realTimeMap[j - 1].clone();
					}
					realTimeMap[0] = spc[0].clone();// 가장위에 비어있는 공간을 만들어 준다.
					success = true;// 라인을 찾았으므로 성공표시를 한다.
				}
			}
		}

		scoreCalculation(score);// 점수 계산후 저장
		sendRealTimeMap();// controll로 맵 정보 전달

	}

	private void scoreCalculation(int score) {
		User user = UserControl.users.getPlayer();
		user.setScore(user.getScore() + score * 100);

		MVC_Connect.ModelToControl.callEvent(MVC_Connect.class, (controllerObj) -> {
			MVC_Connect.ControlToView.callEvent(ScorePanel.class, (viewObj) -> {
				ScorePanel panel = ((ScorePanel) viewObj);
				if (panel.originClass == PanelForTheUser.class) {
					panel.score.setText(Integer.toString(user.getScore()));
				}
			});
		});
		ClientMessage.getClientMessageInstanse().scoreMessageSendEvent(score);

		int level = user.getScore() / 1000;
		UserControl.users.setPlayer(user);

		if (level <= user.getLevel()) {
			return;
		}

		user.setLevel(level);
		MVC_Connect.ModelToControl.callEvent(MVC_Connect.class, (controllerObj) -> {
			MVC_Connect.ControlToView.callEvent(LevelPanel.class, (viewObj) -> {
				LevelPanel panel = ((LevelPanel) viewObj);
				if (panel.originClass == PanelForTheUser.class) {
					panel.level.setText(Integer.toString(level));
				}
			});
		});
		ClientMessage.getClientMessageInstanse().levelMessageSendEvent(level);
	}

	private void setNowTetrino(Tetrino ttrn) {
		tetrino = ttrn;
	}

	private boolean TetrinoBlockDropMove() {
		boolean sucess = true;
		while (sucess) {
			sucess = TetrinoBlockMove(MoveType.DOWN);
		}

		return false;
	}

	private boolean TetrinoBlockMove(MoveType moveType) {
		/*
		 * Space[][] temp = new Space[HEIGHT][WIDTH];
		 * 
		 * for (int i = 0; i < realTimeMap.length; i++) { temp[i] =
		 * realTimeMap[i].clone(); }
		 * 
		 * int x = tetrino.getFlowTetrino().getX() - 3; int y =
		 * tetrino.getFlowTetrino().getY() - 1; Point temp1 = pos(new Point(y, x)); x =
		 * (x < 0) ? 0 : x;
		 * 
		 * for (int i = 0; i < temp1.getY(); i++) { for (int j = 0; j < temp1.getX();
		 * j++) { Space spc = realTimeMap[y + i][x + j]; if (spc.getIsblock() ==
		 * BlockType.FLOW) { realTimeMap[y + i][x + j] = null; } } }
		 * 
		 * if (!tetrino.moveTetrino(moveType)) {
		 * 
		 * for (int i = 0; i < temp.length; i++) realTimeMap[i] = temp[i].clone();
		 * 
		 * if (moveType == MoveType.RIGHT || moveType == MoveType.LEFT || moveType ==
		 * MoveType.TURN) return false;
		 * 
		 * --이부분은 굳이 해줘야하는 의문이 든다 다른 부분의 오류를 다 고치고 지우던지 재판단 필요 -역활은 마지막 줄에 있는 블럭중 움직이는
		 * 블럭이 있으면 움직이지 않는 블럭으로 변경해주는 부--
		 * 
		 * for (int i = 0; i < realTimeMap.length; i++) { for (int j = 0; j <
		 * realTimeMap[i].length; j++) { if (realTimeMap[i][j].getIsblock() ==
		 * BlockType.FLOW) { realTimeMap[i][j].setIsblock(BlockType.FIXED); } } } return
		 * false; } x = tetrino.getFlowTetrino().getX() - 3; y =
		 * tetrino.getFlowTetrino().getY() - 1; temp1 = pos(new Point(y, x));
		 * 
		 * for (int i = 0; i < temp1.getY(); i++) { for (int j = 0; j < temp1.getX();
		 * j++) { Space spc = tetrino.getTetrino()[i][j]; if (spc.getIsblock() ==
		 * BlockType.FLOW) { realTimeMap[i + y][j + x] = spc; } } }
		 * tetrino.setFlowTetrino(new Point(y + 1, x + 3));
		 */

		int x = tetrino.getFlowTetrino().getX() - 3;
		int y = tetrino.getFlowTetrino().getY() - 1;

		if (!tetrino.moveTetrino(moveType)) {
			return false;
		}

		Point temp1 = pos(new Point(y, x));
		x = (x < 0) ? 0 : x;
		// 블럭이 이동할 곳의 값중 움직이는 블럭만 비워준다
		for (int i = 0; i < temp1.getY(); i++) {
			for (int j = 0; j < temp1.getX(); j++) {
				Space spc = realTimeMap[y + i][x + j];
				if (spc != null && spc.getIsblock() == BlockType.FLOW) {
					realTimeMap[y + i][x + j] = null;
				}
			}
		}
		x = tetrino.getFlowTetrino().getX() - 3;
		y = tetrino.getFlowTetrino().getY() - 1;
		temp1 = pos(new Point(y, x));
		// 이동한 블럭의 값만 넣어준다
		for (int i = 0; i < temp1.getY(); i++) {
			for (int j = 0; j < temp1.getX(); j++) {
				Space spc = tetrino.getTetrino()[i][j];
				if (spc != null && spc.getIsblock() == BlockType.FLOW) {
					realTimeMap[i + y][j + x] = spc;
				}
			}
		}
		tetrino.setFlowTetrino(new Point(y + 1, x + 3));
		aroundSearch();
		sendRealTimeMap();
		return true;
	}

	public boolean gameOverCheack() {
		for (int i = 0; i < 3; i++) {
			for (Space space : realTimeMap[i]) {
				if (space != null && space.getIsblock() == BlockType.FIXED) {
					return true;
				}
			}
		}

		return false;
	}

	public Space[][] getRangeRealTimeMap(int height) {

		int temp_height = 4;
		if (height > 17) {
			temp_height = HEIGHT - height - 1;
		}
		Space[][] spc = new Space[temp_height + 1][WIDTH];
		for (int i = 0; i <= temp_height; i++) {
			for (int j = 0; j < WIDTH; j++) {
				spc[i][j] = realTimeMap[i + height][j];
			}
		}
		return spc;
	}

	public Space[][] getRealTimeMap() {
		return realTimeMap;
	}

	public Tetrino getNowTetrino() {
		return tetrino;
	}

	private int lineCheack(Point pos) {
		Space[][] spc = getRangeRealTimeMap(pos.getY() - 1);
		int returnvalue = 0;

		for (int i = 0; i < spc.length; i++) {
			boolean cheack = true;
			for (int j = 0; j < spc[i].length; j++) {
				if (spc[i][j] == null) {
					cheack = false;
					break;
				}
			}
			if (cheack) {
				returnvalue |= 0x01 << i;
			}
		}
		return returnvalue;
	}

	public TetrinoType getSaveBlock() {
		return saveBlock;
	}

	public TetrinoType getNextBlock() {
		return nextBlock;
	}

	public void setRealtimemap(Space[][] realtimemap) {
		this.realTimeMap = realtimemap;
	}

	public void setSaveBlock(TetrinoType tetrino) {
		this.saveBlock = tetrino;

		MVC_Connect.ModelToControl.callEvent(ImagePrint.class, (controllerObj) -> {
			BufferedImage image = ((ImagePrint) controllerObj).saveBlockPaint(saveBlock);
			MVC_Connect.ControlToView.callEvent(SaveBlockPanel.class, (viewObj) -> {
				SaveBlockPanel panel = ((SaveBlockPanel) viewObj);
				if (panel.originClass == PanelForTheUser.class) {
					panel.paintImage(image);
				}
			});
		});

		ClientMessage.getClientMessageInstanse().saveBlockMessageSendEvent(saveBlock);
	}

	public void setNextBlock(TetrinoType tetrino) {
		this.nextBlock = tetrino;

		MVC_Connect.ModelToControl.callEvent(ImagePrint.class, (controllerObj) -> {
			BufferedImage image = ((ImagePrint) controllerObj).nextBlockPaint(nextBlock);
			MVC_Connect.ControlToView.callEvent(NextBlockPanel.class, (viewObj) -> {
				NextBlockPanel panel = ((NextBlockPanel) viewObj);
				if (panel.originClass == PanelForTheUser.class) {
					panel.paintImage(image);
				}
			});
		});

		ClientMessage.getClientMessageInstanse().nextBlockMessageSendEvent(nextBlock);
	}

	public void dropCheck(Object obj) {

		MoveType type = (MoveType) obj;
		if (tetrino == null) {
			createBlock();
		}
		boolean check = (MoveType.DROP != type) ? TetrinoBlockMove(type) : TetrinoBlockDropMove();
		// 이동시 블럭이 멈추는지 아닌지를 판별
		if (check) {
			return;
		}

		Point nowpos = tetrino.getFlowTetrino();

		if (MoveType.DROP == type || MoveType.DOWN == type) {
			setNowTetrino(null);
			for (int i = 0; i < realTimeMap.length; i++) {
				for (int j = 0; j < realTimeMap[i].length; j++) {
					if (realTimeMap[i][j] != null && realTimeMap[i][j].getIsblock() == BlockType.FLOW) {
						realTimeMap[i][j].setIsblock(BlockType.FIXED);
					}
				}
			}
		}

		if (gameOverCheack()) {
			gameOver();
			return;
		}

		int clearline = lineCheack(nowpos);
		if (clearline > 0) {
			lineClear(clearline, nowpos);
		}
	}

	public void sendRealTimeMap() {
		MVC_Connect.ModelToControl.callEvent(ImagePrint.class, (controllerObj) -> {
			BufferedImage image = ((ImagePrint) controllerObj).tetrinoBlockPaint(realTimeMap);
			MVC_Connect.ControlToView.callEvent(TetrinoBlockPanel.class, (viewObj) -> {
				TetrinoBlockPanel panel = ((TetrinoBlockPanel) viewObj);
				if (panel.originClass == PanelForTheUser.class) {
					panel.paintImage(image);
				}
			});
		});

		ClientMessage.getClientMessageInstanse().mapMessageSendEvent(realTimeMap);
	}

	public void gameOver() {
		MVC_Connect.ModelToControl.callEvent(MVC_Connect.class, (controllerobj) -> {
			CallBackEvent event = (e) -> GameBasicFrame.time.stop();
			MVC_Connect.ControlToView.callEvent(SingleFrame.class, event);
			MVC_Connect.ControlToView.callEvent(MultiFrame.class, event);
			MainThread.gameflag = false;
		});

		ClientMessage.getClientMessageInstanse().gameOverEvent(UserControl.users.getPlayer());
	}

	@Override
	public void onEvent(CallBackEvent event) {
		event.callBackEvent(this);
	}
}