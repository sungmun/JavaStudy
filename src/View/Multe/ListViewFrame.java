package View.Multe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Control.TetrisClient;
import Serversynchronization.MessageType;
import Serversynchronization.SocketMessage;
import View.StartFrame;

@SuppressWarnings("serial")
public class ListViewFrame extends JFrame implements MessageType {
	ListView list;
	JButton mulite_play_btn, back_frame_btn, random_multie_play_btn;

	private static ListViewFrame listframe;

	private ListViewFrame() {
		super("유저 목록");
		setLayout(new BorderLayout());
		setSize(new Dimension(637, 200));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		list = ListView.createListView();
		JPanel etc = new JPanel(new GridLayout(3, 1));
		
		mulite_play_btn = new JButton("지정한 상대와 같이하기");
		random_multie_play_btn = new JButton("랜덤한 상대와 같이하기");
		back_frame_btn = new JButton("이전화면으로 가기");

		mulite_play_btn.addActionListener(new MultiPlayAction());
		random_multie_play_btn.addActionListener(new RandomMultiPlayAction());
		back_frame_btn.addActionListener((e)->{
			TetrisClient.getTetrisClient().send(new SocketMessage(LOGOUT, null));
			TetrisClient.getTetrisClient().deConnect();
			StartFrame.getStartFrame().setVisible(true);
			dispose();
		});

		etc.add(mulite_play_btn);
		etc.add(random_multie_play_btn);
		etc.add(back_frame_btn);

		add(list,BorderLayout.WEST);
		add(etc,BorderLayout.EAST);
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
