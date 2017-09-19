package Control;

import java.net.URI;
import java.nio.channels.NotYetConnectedException;

import org.java_websocket.WebSocketImpl;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import com.google.gson.Gson;

import Serversynchronization.MessageType;
import Serversynchronization.SocketMessage;

public class TetrisClient extends WebSocketClient implements MessageType{
	WebSocketImpl engin = null;

	public TetrisClient(URI serverUri) {
		super(serverUri);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onOpen(ServerHandshake handshakedata) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMessage(String message) {
		// TODO Auto-generated method stub
		SocketMessage socketmsg= new Gson().fromJson(message, SocketMessage.class);
		switch(socketmsg.getMessageType()) {
		case MAP_MESSAGE:
			
		case USER_LIST_MESSAGE:
		case GAMEOVER_MESSAGE:
		case BE_CHOSEN:
		}
	}

	@Override
	public void onClose(int code, String reason, boolean remote) {
		// TODO Auto-generated method stub
		System.out.println("connect Close code:" + code + "\t reason : " + reason + "\t remote : " + remote);
	}

	@Override
	public void onError(Exception ex) {
		// TODO Auto-generated method stub
		System.err.println(ex.getMessage());
	}

	@Override
	public void send(String text) throws NotYetConnectedException {
		
	}
}
