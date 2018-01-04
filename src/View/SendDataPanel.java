package View;

import javax.swing.JPanel;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import Control.EventListener;
import Control.MVC_Connect;
import Model.ServerMessage;
import Model.TetrisManager;
import View.Multe.MultiFrame;
import View.Multe.PanelForTheOpponent;
import View.Single.SingleFrame;

@SuppressWarnings("serial")
public abstract class SendDataPanel extends JPanel implements EventListener {
	Class<?> originClass;

	public SendDataPanel() {
		super(true);

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

		MVC_Connect.ControlToView.addListener(this);
	}

	@Override
	public void onEvent(String event) throws ParseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void methodCatch(Object event) {
		JSONObject obj=(JSONObject) event;
		if (obj.get("sentClass") == ServerMessage.class && originClass == PanelForTheOpponent.class) {
			System.out.println("============================");
			System.out.println("SendDataPanel.onEvent()");
			System.out.println(obj.toJSONString());
			System.out.println("============================");
			setData(obj);
		} else if (obj.get("sentClass") == TetrisManager.class && originClass == PanelForTheUser.class) {
			System.out.println("============================");
			System.out.println("SendDataPanel.onEvent()");
			System.out.println(obj.toJSONString());
			System.out.println("============================");
			System.out.println();
			setData(obj);
		}
	}

	abstract void setData(Object obj);

}
