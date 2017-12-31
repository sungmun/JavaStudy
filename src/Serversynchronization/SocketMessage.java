package Serversynchronization;


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
	}

	public MessageType getMessageType() {
		return messagetype;
	}

	public void setMessageType(MessageType messagetype) {
		this.messagetype = messagetype;
	}
	
	

}
