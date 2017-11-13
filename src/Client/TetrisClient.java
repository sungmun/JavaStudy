package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import Control.OpponentEvent;
import Control.ServerMessageEvent;
import Model.ServerInfomation;
import Model.UserManager;
import Serversynchronization.MessageType;
import Serversynchronization.SocketMessage;
import Serversynchronization.User;
import ValueObject.Space;
import View.CellSize;

public class TetrisClient extends Thread implements MessageType, CellSize, ServerInfomation {
	Socket socket;
	PrintWriter out;
	BufferedReader in;

	private static TetrisClient client = null;

	private TetrisClient(User user) throws UnknownHostException, IOException, ConnectException {
		socket = new Socket(IP, PORT);
		out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		send(new SocketMessage(LOGIN, user));
	}

	public void deConnect() {
		TetrisClient.client = null;
	}

	public static TetrisClient createTetrisClient(User user)
			throws UnknownHostException, IOException, ConnectException {
		if (client == null) {
			client = new TetrisClient(user);
		}
		return client;
	}

	public static TetrisClient getTetrisClient() {
		return client;
	}


	@Override
	public void run() {
		try {
			while (true) {
				onMessage(socket);
			}
		} catch (JsonSyntaxException | IOException e) {
			close();
		}
	}

	public <T> T transObject(String msg, Class<T> cla) {
		return new Gson().fromJson(msg, cla);
	}

	public void onMessage(Socket server) throws JsonSyntaxException, IOException {
		ServerMessageEvent event = new ServerMessageEvent();
		SocketMessage socketmsg = transObject(in.readLine(), SocketMessage.class);
		try {
			switch (socketmsg.getMessageType()) {
			case USER_SERIAL_NUM:
				UserManager.createUserManager().getUser()
						.setUserNumber(transObject(socketmsg.getMessage(), Integer.class));
				break;
			case LOGIN:
				event.logIn(socketmsg);
				break;
			case LOGOUT:
				event.logOut(socketmsg);
				break;
			case USER_LIST_MESSAGE:
				event.userListMessage(socketmsg);
				break;
			case GAMEOVER_MESSAGE:
				event.gameOverEvent();
				break;
			case RANK:
				event.rankEvent(socketmsg);
				break;
			case BE_CHOSEN:
				event.beChoice(socketmsg);
				break;
			case WAR_ACCEPT:
				event.warAccept(socketmsg);
				break;
			case WAR_DENIAL:
				JOptionPane.showMessageDialog(null, "상대방이 거부하였습니다");
				break;
			case WAR_START:
				event.logOut(socketmsg);
				break;
			case SCORE_MESSAGE:
				OpponentEvent.getOpponentEvent().managerSetScore(transObject(socketmsg.getMessage(), int.class));
				break;
			case LEVEL_MESSAGE:
				OpponentEvent.getOpponentEvent().managerSetLevel(transObject(socketmsg.getMessage(), int.class));
				break;
			case SAVE_BLOCK_MESSAGE:
				OpponentEvent.getOpponentEvent().managerSetSaveBlock(transObject(socketmsg.getMessage(), int.class));
				break;
			case NEXT_BLOCK_MESSAGE:
				OpponentEvent.getOpponentEvent().managerSetNextBlock(transObject(socketmsg.getMessage(), int.class));
				break;
			case MAP_MESSAGE:
				OpponentEvent.getOpponentEvent()
						.managerSetRealtimemap(transObject(socketmsg.getMessage(), Space[][].class));
				break;
			case USER_MESSAGE:
				event.UserMessage(socketmsg);
				break;
			}
		} catch (NullPointerException e) {
			in.reset();
		}
	}

	public void send(SocketMessage message) {
		out.println(new Gson().toJson(message));
		out.flush();
		System.gc();
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
