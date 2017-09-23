package Control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

import javax.swing.JFrame;

import com.google.gson.Gson;

import Model.CellSize;
import Serversynchronization.MessageType;
import Serversynchronization.SocketMessage;
import Serversynchronization.User;
import Serversynchronization.UsersList;
import View.Multe.ListViewFrame;

public class TetrisClient implements MessageType, CellSize {
	Socket socket;
	PrintWriter out;
	BufferedReader in;
	private static TetrisClient client = null;

	private TetrisClient(String host, int port, User user) throws UnknownHostException, IOException, ConnectException {
		socket = new Socket(host, port);
		send(new SocketMessage(LOGIN, user));
	}

	public static TetrisClient createTetrisClient(String host, int port, User user)
			throws UnknownHostException, IOException, ConnectException {
		if (client == null) {
			client = new TetrisClient(host, port, user);
		}
		return client;
	}

	public static TetrisClient getTetrisClient() {
		return client;
	}

	@SuppressWarnings("unchecked")
	public void onMessage(String message) {
		SocketMessage socketmsg = new Gson().fromJson(message, SocketMessage.class);
		switch (socketmsg.getMessageType()) {
		case MAP_MESSAGE:
			;

		case USER_LIST_MESSAGE:
			UsersList.setList((Vector<User>) socketmsg.transformJSON());
			JFrame fr = new ListViewFrame();
			fr.setVisible(true);
		case GAMEOVER_MESSAGE:
		case BE_CHOSEN:
		}
	}

	public void send(Object message) {
		String msg = new Gson().toJson(message);
		try {
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			out.write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
