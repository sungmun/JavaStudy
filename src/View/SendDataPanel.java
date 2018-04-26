package View;

import Control.CallBackEvent;
import Control.EventListener;
import Control.MVC_Connect;
import View.BaseClass.BasicPanel;
import View.Multe.MultiFrame;
import View.Single.SingleFrame;

public abstract class SendDataPanel extends BasicPanel implements EventListener {
	/**
	 * 이클래스는 데이터를 받는 클래스로 이 클래스를 상속받는 클래스는 데이터를 상속받을 수 있다 데이터를 받아서 setData함수를 통해서
	 * 데이터를 전달한다 이때 setData함수는 이 클래스를 상속받는 클래스에서 구현을 한다 originClass변수는 이 클래스를 상속받는
	 * 클래스가 어디에서 호출을 받는지를 구볗하기위한 변수로 데이터를 현사용자의 정보를 표시하는 곳에 전달을 할것인지, 아니면 상대방의 상태를
	 * 표시하는 곳에 전달을 할 것인지 구별해준다
	 */
	private static final long serialVersionUID = 5122925426455093897L;
	public Class<?> originClass;

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
	public void onEvent(CallBackEvent event) {
		event.callBackEvent(this);
	}

}
