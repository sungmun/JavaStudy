package View.Multe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Client.TetrisClient;
import Serversynchronization.MessageType;
import Serversynchronization.SocketMessage;
import Serversynchronization.User;
import Serversynchronization.UsersList;
import View.BasicButton;
import View.StartFrame;

@SuppressWarnings("serial")
public class ListViewFrame extends JFrame implements MessageType {
	ListView list;
	BasicButton mulite_play_btn, back_frame_btn, random_multie_play_btn;

	private static ListViewFrame listframe;

	private ListViewFrame() {
		super("접속자 목록");
		setLayout(new BorderLayout());
		list = ListView.createListView();
		
		JPanel etc = new JPanel(new GridLayout(3, 1));
		etc.setBackground(Color.BLACK);
		
		mulite_play_btn = new BasicButton("지정한 사람과 같이하기");
		random_multie_play_btn = new BasicButton("랜덤으로 같이하기");
		back_frame_btn = new BasicButton("뒤로가기");

		TetrisClient client = TetrisClient.getTetrisClient();

		mulite_play_btn.addActionListener((e) -> {
			Object us = list.getData();
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
}
