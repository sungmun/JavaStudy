package View.Multe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Control.BackFrameAction;
import Control.EventListener;
import Control.MVC_Connect;
import Control.MulitePlayAction;
import Serversynchronization.TotalJsonObject;
import View.BasicButton;
import View.StartFrame;

@SuppressWarnings("serial")
public class ListViewFrame extends JFrame implements EventListener{
	ListView list;
	BasicButton mulite_play_btn, back_frame_btn, random_multie_play_btn;

	private static ListViewFrame listframe;

	private ListViewFrame() {
		super("접속자 목록");
		
		MVC_Connect.ControlToView.addListener(this);
		
		setLayout(new BorderLayout());
		list = ListView.createListView();

		JPanel etc = new JPanel(new GridLayout(3, 1));
		etc.setBackground(Color.BLACK);

		mulite_play_btn = new BasicButton("지정한 사람과 같이하기");
		random_multie_play_btn = new BasicButton("랜덤으로 같이하기");
		back_frame_btn = new BasicButton("뒤로가기");

		mulite_play_btn.addActionListener(new MulitePlayAction(list,false));
		random_multie_play_btn.addActionListener(new MulitePlayAction(list,true));
		back_frame_btn.addActionListener(new BackFrameAction(StartFrame.getStartFrame(), this));

		etc.add(mulite_play_btn);
		etc.add(random_multie_play_btn);
		etc.add(back_frame_btn);

		add(list, BorderLayout.WEST);
		add(etc, BorderLayout.EAST);

		this.getContentPane().setBackground(Color.BLACK);
		this.setUndecorated(true);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
	}

	public static ListViewFrame createListViewFrame() {
		if (listframe == null) {
			listframe = new ListViewFrame();
		}
		return listframe;
	}

	@Override
	public void onEvent(Object event) {
		TotalJsonObject object=(TotalJsonObject) event;
		
		if(object.get("method")==null) {
			return;
		}
		methodCatch(object);
	}

	@Override
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
