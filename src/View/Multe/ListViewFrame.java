package View.Multe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Client.TetrisClient;
import Control.BackFrameAction;
import Control.FrameControl;
import Control.MVC_Connect;
import Control.MulitePlayAction;
import Control.User;
import Control.UsersList;
import Serversynchronization.MessageType;
import Serversynchronization.SocketMessage;
import View.BasicButton;
import View.StartFrame;

@SuppressWarnings("serial")
public class ListViewFrame extends JFrame {
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
}
