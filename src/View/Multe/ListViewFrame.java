package View.Multe;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Serversynchronization.MessageType;
import View.FrameMoveAction;
import View.StartFrame;

@SuppressWarnings("serial")
public class ListViewFrame extends JFrame implements MessageType {
	ListView list;
	JButton mulite_play_btn, back_frame_btn, random_multie_play_btn;

	private static ListViewFrame listframe;

	private ListViewFrame() {
		super("유저 목록");
		setSize(new Dimension(500, 500));
		list = ListView.createListView();
		JPanel etc = new JPanel();
		
		mulite_play_btn = new JButton("지정한 상대와 같이하기");
		random_multie_play_btn = new JButton("랜덤한 상대와 같이하기");
		back_frame_btn = new JButton("이전화면으로 가기");

		mulite_play_btn.addActionListener(new MultiPlayAction());
		random_multie_play_btn.addActionListener(new RandomMultiPlayAction());
		back_frame_btn.addActionListener(new FrameMoveAction(StartFrame.getStartFrame(), this));

		etc.add(mulite_play_btn);
		etc.add(random_multie_play_btn);
		etc.add(back_frame_btn);

		add(list);
		add(etc);
	}

	public static ListViewFrame createListViewFrame() {
		if (listframe == null) {
			listframe = new ListViewFrame();
		}
		return listframe;
	}

	public static ListViewFrame getListViewFrame() {
		return listframe;
	}
}
