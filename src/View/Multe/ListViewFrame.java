package View.Multe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Control.FrameChangeAction;
import Control.EventListener;
import Control.MVC_Connect;
import Control.MulitePlayAction;
import Serversynchronization.TotalJsonObject;
import View.StartFrame;
import View.BaseClass.BasicButton;
import View.BaseClass.BasicFrame;
import View.BaseClass.BasicPanel;

@SuppressWarnings("serial")
public class ListViewFrame extends BasicFrame{
	ListView list;
	BasicButton mulite_play_btn, back_frame_btn, random_multie_play_btn;

	public ListViewFrame() {
		super("접속자 목록");
		
		MVC_Connect.ControlToView.addListener(this);
		
		setLayout(new BorderLayout());
		list = new ListView();

		JPanel etc = new BasicPanel(new GridLayout(3, 1));
		etc.setBackground(Color.BLACK);

		mulite_play_btn = new BasicButton("Start",new MulitePlayAction());
		etc.add(mulite_play_btn);

		random_multie_play_btn = new BasicButton("RandomStart",new MulitePlayAction());
		etc.add(random_multie_play_btn);

		back_frame_btn = new BasicButton("Back",new FrameChangeAction(new StartFrame().getClass(), this.getClass()));
		etc.add(back_frame_btn);
		

		add(list, BorderLayout.WEST);
		add(etc, BorderLayout.EAST);

	}
	@Override
	public void onEvent(Object event) {
		TotalJsonObject object=(TotalJsonObject) event;
		
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
		default:
			break;
		}
	}
}
