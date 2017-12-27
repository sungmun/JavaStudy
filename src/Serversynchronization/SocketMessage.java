package Serversynchronization;

import com.google.gson.Gson;

public class SocketMessage {
	private MessageType messagetype;
	private String message;

	public SocketMessage(MessageType msg, Object obj) {
		messagetype = msg;
		setMessage(obj);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = new Gson().toJson(message);
	}

	public MessageType getMessageType() {
		return messagetype;
	}

	public void setMessageType(MessageType messagetype) {
		this.messagetype = messagetype;
	}
	
	public static <T> T GSONtoObject(String msg, Class<T> type) {
		return new Gson().fromJson(msg, type);
	}
	

}
