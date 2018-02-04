package View.BaseClass;

import Control.Tic;
import Serversynchronization.TotalJsonObject;

@SuppressWarnings("serial")
public class GameBasicFrame extends BasicFrame{
	protected int speed = 500;
	public static Tic time;
	public GameBasicFrame( ) {
		super("Tetris");
	}
	
	@Override
	public void onEvent(Object event) {
		TotalJsonObject object=new TotalJsonObject(event.toString());
		
		if(object.get("method")==null) {
			return;
		}
		methodCatch(object);
	}

	public void methodCatch(Object event) {
		TotalJsonObject object=(TotalJsonObject) event;
		switch (object.get("method").toString()) {
		case "setVisible":
			setVisible((Boolean)object.get("boolean"));
			break;
		case "dispose":
			dispose();
			break;
		case "stop":
			time.stop();
			break;
		default:
			break;
		}
	}
}
