package View.BaseClass;

import Control.Tic;
import Serversynchronization.TotalJsonObject;

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
	public void methodCatch(Object event) {
		super.methodCatch(event);
		TotalJsonObject object = (TotalJsonObject) event;
		if(object.get("method").equals("stop")) {
			time.stop();
		}
	}
}
