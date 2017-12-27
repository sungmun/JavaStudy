package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import Model.ServerInfomation;
import Serversynchronization.MessageType;
import Serversynchronization.SocketMessage;
import Serversynchronization.User;
import View.CellSize;

public class TetrisClient extends Thread implements CellSize, ServerInfomation {
	Socket socket;
	PrintWriter out;
	BufferedReader in;

	private static TetrisClient client = null;

	private TetrisClient(User user) throws UnknownHostException, IOException, ConnectException {
		socket = new Socket(IP, PORT);
		out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		send(new SocketMessage(MessageType.LOGIN, user));
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

	public void onMessage(Socket server) throws JsonSyntaxException, IOException {
		ServerMessage event = new ServerMessage();
		SocketMessage socketmsg = SocketMessage.GSONtoObject(in.readLine(), SocketMessage.class);
		if (!socketmsg.equals(null)) {
			event.getEvent(socketmsg);
		}
		in.reset();
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
