package View.BaseClass;

import java.awt.Color;
import java.awt.GraphicsConfiguration;

import javax.swing.JFrame;

import Control.CallBackEvent;
import Control.EventListener;
import Control.MVC_Connect;

public class BasicFrame extends JFrame implements EventListener {

	/**
	 * 이 project에 정형화된 이미지를 위하여 사용되는 클래스로 프레임의 색깔과 커버를 할것인가 아닌가 그리고 프레임의 위치 마지막으로
	 * 프레임의 크기를 지정해 놓았다
	 */
	private static final long serialVersionUID = -8237381349552922375L;

	public BasicFrame(String title, GraphicsConfiguration GC) {
		super(title, GC);
		init();
	}

	public BasicFrame(String title) {
		super(title);
		init();
	}

	public BasicFrame(GraphicsConfiguration GC) {
		super(GC);
		init();
	}

	public BasicFrame() {
		super();
		init();
	}

	public void init() {
		this.getContentPane().setBackground(Color.BLACK);
		this.setUndecorated(true);
		this.setResizable(false);
		MVC_Connect.ControlToView.addListener(this);
	}

	@Override
	public void dispose() {
		super.dispose();
		MVC_Connect.ControlToView.removeListener(this);
	}

	@Override
	public void setVisible(boolean b) {
		super.setVisible(b);
		if (b) {
			this.pack();
			this.setLocationRelativeTo(null);
		}
	}

	@Override
	public void onEvent(CallBackEvent event) {
		event.callBackEvent(this);
	}

//	@Override
//	public void onEvent(Object event) {
//		TotalJsonObject object = new TotalJsonObject(event.toString()); 
//
//		if (object.get("method") == null) {
//			return;
//		}
//		methodCatch(object);
//	}
//
//	public void methodCatch(Object event) {
//		TotalJsonObject object = (TotalJsonObject) event;
//		switch (object.get("method").toString()) {
//		case "setVisible":
//			setVisible((Boolean) object.get("boolean"));
//			break;
//		case "dispose":
//			dispose();
//			break;
//		default:
//			break;
//		}
//	}
}
