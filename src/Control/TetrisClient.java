package Control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import Model.CellSize;
import Model.Space;
import Model.Tetrino;
import Serversynchronization.MessageType;
import Serversynchronization.SocketMessage;
import Serversynchronization.User;
import Serversynchronization.UsersList;
import View.FrameMoveAction;
import View.Multe.ListViewFrame;
import View.Multe.LoginFrame;
import View.Multe.MultiFrame;
import View.Multe.ServerInfomation;

public class TetrisClient extends Thread implements MessageType, CellSize, ServerInfomation {
	Socket socket;
	PrintWriter out;
	BufferedReader in;
	private static TetrisClient client = null;

	private TetrisClient() throws UnknownHostException, IOException, ConnectException {
		socket = new Socket(IP, PORT);
		out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		send(new SocketMessage(LOGIN, UserControl.getUserControl().getUser()));
	}

	public void deConnect() {
		TetrisClient.client = null;
	}

	public static TetrisClient createTetrisClient() throws UnknownHostException, IOException, ConnectException {
		if (client == null) {
			client = new TetrisClient();
		}
		return client;
	}

	public static TetrisClient getTetrisClient() {
		return client;
	}

	public <T> T transObject(String msg, Class<T> cla) {
		Gson gson = new Gson();
		return gson.fromJson(msg, cla);
	}

	@Override
	public void run() {

		while (true) {
			try {
				onMessage(socket);
			} catch (JsonSyntaxException | IOException e) {
				System.err.println(e.getMessage());
				close();
				return;
			}
		}
	}

	public void logIn(SocketMessage msg) {
		UsersList.add(transObject(msg.getMessage(), User.class));
	}

	public void logOut(SocketMessage msg) {
		UsersList.delete(transObject(msg.getMessage(), User.class));
	}

	public void userListMessage(SocketMessage msg) {
		UsersList.setList(transObject(msg.getMessage(), User[].class));
		FrameMoveAction.moveActeion(ListViewFrame.createListViewFrame(), LoginFrame.getLoginFrame());
	}

	public void beChoice(SocketMessage msg) {
		User player = transObject(msg.getMessage(), User.class);
		int val = JOptionPane.showOptionDialog(null, player.getName() + "님이 대전을 요청하셨습니다", "대전 요청",
				JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
		if (val == JOptionPane.YES_OPTION) {
			send(new SocketMessage(WAR_ACCEPT, null));
			warAccept(msg);
		} else {
			send(new SocketMessage(WAR_DENIAL, null));
		}
	}

	public void warAccept(SocketMessage msg) {
		System.out.println("TetrisClient.warAccept()");
		send(new SocketMessage(USER_MESSAGE, UserControl.getUserControl().getUser()));
		send(new SocketMessage(WAR_START, null));
	}

	public void onMessage(Socket server) throws JsonSyntaxException, IOException {
		// 백터나 컬렉션의 정렬화는 지원하나 역정렬화는 지원을 않한다

		SocketMessage socketmsg = transObject(in.readLine(), SocketMessage.class);
		OpponentControl opcontrol = OpponentControl.createOpponentControl();
		try {
			switch (socketmsg.getMessageType()) {
			case USER_SERIAL_NUM:
				UserControl.createUserControl().getUser()
						.setUserNumber(transObject(socketmsg.getMessage(), Integer.class));
				break;
			case LOGIN:
				logIn(socketmsg);
				break;
			case LOGOUT:
				logOut(socketmsg);
				break;
			case USER_LIST_MESSAGE:
				userListMessage(socketmsg);
				break;
			case GAMEOVER_MESSAGE:

			case BE_CHOSEN:
				beChoice(socketmsg);
				break;
			case WAR_ACCEPT:
				warAccept(socketmsg);
				break;
			case WAR_DENIAL:
				JOptionPane.showMessageDialog(null, "대전이 거부 당하셨습니다");
				break;
			case WAR_START:
				logOut(socketmsg);
				break;
			case SCORE_MESSAGE:
				opcontrol.getManager().setScore(transObject(socketmsg.getMessage(), int.class));
				break;
			case LEVEL_MESSAGE:
				opcontrol.getManager().setLevel(transObject(socketmsg.getMessage(), int.class));
				break;
			case SAVE_BLOCK_MESSAGE:
				opcontrol.getManager().setSave_block(transObject(socketmsg.getMessage(), Tetrino.class));
				break;
			case NEXT_BLOCK_MESSAGE:
				opcontrol.getManager().setNext_block(transObject(socketmsg.getMessage(), Tetrino.class));
				break;
			case MAP_MESSAGE:
				opcontrol.getManager().setRealtimemap(transObject(socketmsg.getMessage(), Space[][].class));
				break;
			case USER_MESSAGE:
				opcontrol.setUser(transObject(socketmsg.getMessage(), User.class));
				JFrame fr1 = MultiFrame.createMulteFrame();
				JFrame fr2 = ListViewFrame.createListViewFrame();
				if (fr1 != null || fr2 == null) {
					FrameMoveAction.moveActeion(fr1, fr2);
				}
				break;
			}
		} catch (NullPointerException e) {
			in.reset();
		}
	}

	public void send(SocketMessage message) {
		out.println(new Gson().toJson(message));
		out.flush();
	}

	private void close() {
		try {
			in.close();
			socket.close();
			out.close();
			interrupt();
		} catch (IOException | ArrayIndexOutOfBoundsException e) {
			System.err.println("TetrisClient.close()");
		}
	}
}
