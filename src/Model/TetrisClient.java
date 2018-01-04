package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TetrisClient extends Thread {
	public final static String IP = "mydirectory.iptime.org";
	public final static int PORT = 4160;

	Socket socket;
	PrintWriter out;
	BufferedReader in;

	private static TetrisClient client = null;

	private TetrisClient()  {
		try {
			socket = new Socket(IP, PORT);
			out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public void deConnect() {
		TetrisClient.client = null;
		close();
	}

	public static TetrisClient createTetrisClient(){
		if (client == null) {
			client = new TetrisClient();
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
		// ServerMessage event = new ServerMessage();
		try {
			JSONObject object = (JSONObject) new JSONParser().parse(in.readLine());
			if (object.get("MessageType")!= null) {
				ServerMessage.eventCatch(object);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		in.reset();
	}

	public void send(String msg) {
		out.println(msg);
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
