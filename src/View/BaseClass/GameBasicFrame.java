package View.BaseClass;

import Control.CallBackEvent;
import Control.Tic;

public class GameBasicFrame extends BasicFrame {
	/**
	 * 이 Project에서의 almost 일련된 하나의 Image를 make위한 GameFrame Class이다.
	 */
	private static final long serialVersionUID = -7952471597925708344L;
	protected int speed = 500;
	public static Tic time;

	public GameBasicFrame() {
		super("Tetris");
	}

	@Override
	public void onEvent(CallBackEvent event) {
		event.callBackEvent(this);
	}

}
