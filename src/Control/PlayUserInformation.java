package Control;

import Client.TetrisClient;
import Serversynchronization.MessageType;
import Serversynchronization.PlayerInformation;
import Serversynchronization.SocketMessage;

public class PlayUserInformation extends PlayerInformation implements MessageType {
	
	UserEvent event = new UserEvent();
	public PlayUserInformation(int level, int score) {
		super(level, score);
	}
	public PlayUserInformation() {
	}
	@Override
	public void setLevel(int level) {
		super.setLevel(level);
		event.notifyObserver("level", Integer.toString(this.getLevel()));
		TetrisClient client=TetrisClient.getTetrisClient();
		if(client!=null) {
			client.send(new SocketMessage(LEVEL_MESSAGE, level));
		}
	}

	@Override
	public void setScore(int score) {
		super.setScore(score);
		event.notifyObserver("score", Integer.toString(this.getScore()));
		TetrisClient client=TetrisClient.getTetrisClient();
		if(client!=null) {
			client.send(new SocketMessage(SCORE_MESSAGE, score));
		}
	}
}
