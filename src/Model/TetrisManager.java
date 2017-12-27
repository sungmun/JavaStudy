package Model;

import java.util.Arrays;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Control.EventListener;
import Control.ImagePrint;
import Control.MVC_Connect;
import Control.UserControl;
import Serversynchronization.PlayerInformation;
import ValueObject.Map;
import ValueObject.Point;
import ValueObject.Space;
import View.GameBasicFrame;

public class TetrisManager implements EventListener {

	public static final int HEIGHT = 23;
	public static final int WIDTH = 10;

	private TetrinoType saveBlock = null;
	private TetrinoType nextBlock = null;

	private Space[][] realTimeMap;
	public Tetrino tetrino;

	protected TetrisManager() {
		realTimeMap = new Map(WIDTH, HEIGHT).getMap();
		MVC_Connect.ControlToModel.addListener(this);
	}

	public void saveBlock() {
		int y = 0;
		for (Space[] spaces : realTimeMap) {
			int x = 0;
			for (Space space : spaces) {
				if (space.getIsblock() == BlockType.FLOW) {
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
				Space spc = (tetrino.getFlowTetrino().getY() + i > HEIGHT || x + j < 0 || x + j > WIDTH - 1)
						? new Space(BlockType.ETC)
						: realTimeMap[y + i][x + j];
				if (!tetrino.getArea()[i][j].equals(spc)) {
					tetrino.setArea(i, j, spc);
				}
			}
		}
	}

	public void createBlock() {
		int createposition = WIDTH / 3;
		tetrino = (nextBlock == null) ? new CreateBlock().tetrinoRandomCreate()
				: new CreateBlock().tetrinoChoiceCreate(nextBlock);
		setNextBlock(TetrinoType.getRandomTetrino());
		for (int y = 0; y < 4; y++) {
			for (int x = 1; x < 5; x++) {
				realTimeMap[y][x + createposition] = tetrino.getTetrino()[y][x];
			}
		}
		tetrino.setFlowTetrino(new Point(1, createposition + 2));
	}

	public void createChoiceBlock(TetrinoType Type) {
		int createposition = WIDTH / 3;
		tetrino = new CreateBlock().tetrinoChoiceCreate(Type);
		for (int y = 0; y < 4; y++) {
			for (int x = 1; x < 5; x++) {
				realTimeMap[y][x + createposition] = tetrino.getTetrino()[y][x];
			}
		}
		tetrino.setFlowTetrino(new Point(1, createposition + 2));
	}

	public void lineClear(int clearline, Point pos) {

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
				realTimeMap[END_POS + i] = spc[CLEAR_LINE].clone();
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
		sendRealTimeMap();// controll로 맵 정보 전달
		// 점수 계산후 저장
		PlayerInformation info = UserControl.player.getInfo();
		info.setScore(info.getScore() + score * 100);
		int level = info.getScore() / 1000;
		if (level > info.getLevel()) {
			info.setLevel(level);
		}
		UserControl.player.setInfo(info);
	}

	public void setNowTetrino(Tetrino ttrn) {
		tetrino = ttrn;
	}

	public boolean TetrinoBlockDropMove() {
		boolean sucess = true;
		while (sucess) {
			sucess = TetrinoBlockMove(MoveType.DOWN);
		}
		return false;
	}

	public boolean TetrinoBlockMove(MoveType moveType) {
		Space[][] temp = new Space[HEIGHT][WIDTH];
		for (int i = 0; i < realTimeMap.length; i++) {
			temp[i] = realTimeMap[i].clone();
		}
		int x = tetrino.getFlowTetrino().getX() - 3;
		int y = tetrino.getFlowTetrino().getY() - 1;
		Point temp1 = pos(new Point(y, x));
		x = (x < 0) ? 0 : x;
		for (int i = 0; i < temp1.getY(); i++) {
			for (int j = 0; j < temp1.getX(); j++) {
				Space spc = realTimeMap[y + i][x + j];
				if (spc.getIsblock() == BlockType.FLOW) {
					realTimeMap[y + i][x + j] = null;
				}
			}
		}
		if (!tetrino.moveTetrino(moveType)) {

			for (int i = 0; i < temp.length; i++) {
				realTimeMap[i] = temp[i].clone();
			}
			if (moveType == MoveType.RIGHT || moveType == MoveType.LEFT || moveType == MoveType.TURN) {
				return false;
			}

			for (int i = 0; i < realTimeMap.length; i++) {
				for (int j = 0; j < realTimeMap[i].length; j++) {
					if (realTimeMap[i][j].getIsblock() == BlockType.FLOW) {
						realTimeMap[i][j].setIsblock(BlockType.FIXED);
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
					realTimeMap[i + y][j + x] = spc;
				}
			}
		}
		tetrino.setFlowTetrino(new Point(y + 1, x + 3));
		aroundSearch();
		sendRealTimeMap();
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
		JSONObject blockMessage = new JSONObject();
		blockMessage.put("method", "saveBlockPaint");
		blockMessage.put("TetrinoType", saveBlock);
		MVC_Connect.ModelToControl.callEvent(ImagePrint.class.getClass(), blockMessage);
	}

	public void setNextBlock(TetrinoType tetrino) {
		this.nextBlock = tetrino;
		JSONObject blockMessage = new JSONObject();
		blockMessage.put("method", "nextBlockPaint");
		blockMessage.put("TetrinoType", nextBlock);
		MVC_Connect.ModelToControl.callEvent(ImagePrint.class.getClass(), blockMessage);
	}

	private void dropCheck(Object obj) {
		
		MoveType type=(MoveType)obj;
		
		boolean check=(MoveType.DROP!=type)?TetrinoBlockMove(type):TetrinoBlockDropMove();
		//이동시 블럭이 멈추는지 아닌지를 판별
		
		Point nowpos = tetrino.getFlowTetrino();
		if (gameOverCheack(nowpos)) {
			GameBasicFrame.time.stop();
		}
		setNowTetrino(null);
		int clearline =lineCheack(nowpos);
		if (clearline > 0) {
			lineClear(clearline, nowpos);
		}
	}

	@Override
	public void onEvent(String event) {
		try {
			JSONObject object = (JSONObject) new JSONParser().parse(event);
			if (object.get("method").toString() != null) {
				methodCatch(object);
			} else {
				System.out.println("ImagePrint.onEvent()");
				System.err.println(object.toJSONString());
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void methodCatch(JSONObject event) {
		switch (event.get("method").toString()) {
		case "TetrinoBlockDropMove":
		case "TetrinoBlockMove":
			dropCheck(event.get("MoveType"));
		case "saveBlock":
			saveBlock();
		}
	}

	public void sendRealTimeMap() {
		JSONObject mapMessage = new JSONObject();
		mapMessage.put("method", "TetrinoBlockPaint");
		mapMessage.put("Space[][]", realTimeMap);
		MVC_Connect.ModelToControl.callEvent(ImagePrint.class.getClass(), mapMessage);
	}

}