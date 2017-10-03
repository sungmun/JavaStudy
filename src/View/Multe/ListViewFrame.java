package View.Multe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Control.TetrisClient;
import Serversynchronization.MessageType;
import Serversynchronization.SocketMessage;
import Serversynchronization.User;
import Serversynchronization.UsersList;
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

		TetrisClient client = TetrisClient.getTetrisClient();

		mulite_play_btn.addActionListener((e) -> {
			User us = list.getUser();
			if (UsersList.findList(us)) {
				int index=UsersList.getList().indexOf(us);
				us = UsersList.getList().get(index);
				client.send(new SocketMessage(USER_SELECTING, us));
			}
		});
		random_multie_play_btn.addActionListener((e) -> {
			if(UsersList.getList().size()<1) {
				return;
			}
			int random = new Random().nextInt(UsersList.getList().size());
			User us = UsersList.getList().get(random);
			client.send(new SocketMessage(USER_SELECTING, us));
		});
		back_frame_btn.addActionListener((e) -> {
			client.send(new SocketMessage(LOGOUT, null));
			client.deConnect();
			StartFrame.getStartFrame().setVisible(true);
			dispose();
		});

		etc.add(mulite_play_btn);
		etc.add(random_multie_play_btn);
		etc.add(back_frame_btn);

		add(list, BorderLayout.WEST);
		add(etc, BorderLayout.EAST);
	}

	public static ListViewFrame createListViewFrame() {
		if (listframe == null) {
			listframe = new ListViewFrame();
		}
		return listframe;
	}
}
