package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Control.EventListener;
import Model.ServerInfomation;
import Serversynchronization.MessageType;
import Serversynchronization.SocketMessage;
import Serversynchronization.User;
import View.CellSize;

public class TetrisClient extends Thread implements EventListener {
	public final static String IP = "mydirectory.iptime.org";
	public final static int PORT = 4160;

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
			client.start();
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
		} catch (IOException e) {
			close();
		}
	}

	public void onMessage(Socket server) throws IOException {
//		ServerMessage event = new ServerMessage();
		try {
			JSONObject object = (JSONObject) new JSONParser().parse(in.readLine());
			if (object.get("MessageType").toString() != null) {
				ServerMessage.
			} else {
				System.out.println("TetrisClient.onMessage()");
				System.err.println(object.toJSONString());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// SocketMessage socketmsg = SocketMessage.GSONtoObject(in.readLine(),
		// SocketMessage.class);
		// if (!socketmsg.equals(null)) {
		// event.getEvent(socketmsg);
		// }
		in.reset();
	}

	public void send(String message) {
		// out.println(new Gson().toJson(message));
		out.println(message);
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

	@Override
	public void onEvent(String event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void methodCatch(JSONObject event) {
		

	}

}
