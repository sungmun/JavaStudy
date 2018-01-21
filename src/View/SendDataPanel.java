package View;

import Control.EventListener;
import Control.MVC_Connect;
import Model.ServerMessage;
import Model.TetrisManager;
import Serversynchronization.MessageType;
import Serversynchronization.TotalJsonObject;
import View.Multe.MultiFrame;
import View.Multe.PanelForTheOpponent;
import View.Single.SingleFrame;

@SuppressWarnings("serial")
public abstract class SendDataPanel extends BasicPanel implements EventListener {
	Class<?> originClass;

	public SendDataPanel() {
		super(true);

		MVC_Connect.ControlToView.addListener(this);
		
		StackTraceElement[] elements = new Throwable().getStackTrace();
		try {
			for (int i = 0; i < elements.length; i++) {
				String className = elements[i].getClassName();
				if (className == SingleFrame.class.getName() || className == MultiFrame.class.getName()) {
					originClass = Class.forName(elements[i - 1].getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void onEvent(Object event) {
		try {
			TotalJsonObject message = (TotalJsonObject) event;
			methodCatch(message);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

	public void methodCatch(Object event) {
		TotalJsonObject element = (TotalJsonObject) event;
		if (element.get("sentClass").equals(ServerMessage.class.getName())
				&& originClass == PanelForTheOpponent.class) {
			setData(element);
		} else if (element.get("sentClass").equals(TetrisManager.class.getName())
				&& originClass == PanelForTheUser.class) {
			System.out.println(element.get(MessageType.class.getSimpleName()));
			setData(element);
		}
	}

	abstract void setData(Object obj);

}
