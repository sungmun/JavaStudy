package Client;

import Model.TetrinoType;
import Model.UserManager;
import Serversynchronization.MessageType;
import Serversynchronization.SocketMessage;
import ValueObject.Space;

public class ClientMessage {
	TetrisClient client = TetrisClient.getTetrisClient();

	public void warAccept(SocketMessage msg) {
		sendMessage(new SocketMessage(MessageType.USER_MESSAGE, UserManager.getUserManager().getUser()));
		sendMessage(new SocketMessage(MessageType.WAR_START, null));
	}

	public void mapSend(Space[][] map) {
		sendMessage(new SocketMessage(MessageType.MAP_MESSAGE, map));
	}

	public void levelSend(int level) {
		sendMessage(new SocketMessage(MessageType.LEVEL_MESSAGE, level));
	}

	public void scoreSend(int score) {
		sendMessage(new SocketMessage(MessageType.SCORE_MESSAGE, score));
	}

	public void gameOverSend() {
		sendMessage(new SocketMessage(MessageType.GAMEOVER_MESSAGE, null));
	}

	public void nextBlockSend(TetrinoType tetrino) {
		sendMessage(new SocketMessage(MessageType.NEXT_BLOCK_MESSAGE, tetrino));
	}
	public void saveBlockSend(TetrinoType tetrino) {
		sendMessage(new SocketMessage(MessageType.SAVE_BLOCK_MESSAGE, tetrino));
	}
	private void sendMessage(SocketMessage message) {
		if(client!=null) {
			client.send(message);
		}
	}
}
