package Model;

import Client.TetrisClient;
import Serversynchronization.MessageType;
import Serversynchronization.SocketMessage;

public class UserTetrisControlManager extends TetrisControlManager implements MessageType{
	private static UserTetrisControlManager tetrismanager = null;
	public UserTetrisControlManager() {
		super();
	}
	public static UserTetrisControlManager createTetrisControlManager() {
		if (tetrismanager == null) {
			tetrismanager = new UserTetrisControlManager();
		}
		return tetrismanager;
	}
	public static UserTetrisControlManager getTetrisControlManager() {
		return tetrismanager;
	}
	@Override
	public void setLevel(int level) {
		super.setLevel(level);
		TetrisClient client=TetrisClient.getTetrisClient();
		if(client!=null) {
			client.send(new SocketMessage(LEVEL_MESSAGE, this.level));
		}
	}
	@Override
	public void setScore(int score) {
		super.setScore(score);
		TetrisClient client=TetrisClient.getTetrisClient();
		if(client!=null) {
			client.send(new SocketMessage(SCORE_MESSAGE, this.score));
		}
	}
	@Override
	public void setNext_block(Tetrino next_block) {
		super.setNext_block(next_block);
		TetrisClient client=TetrisClient.getTetrisClient();
		if(client!=null) {
			client.send(new SocketMessage(NEXT_BLOCK_MESSAGE, this.next_block));
		}
	}
	@Override
	public void setSave_block(Tetrino save_block) {
		super.setSave_block(save_block);
		TetrisClient client=TetrisClient.getTetrisClient();
		if(client!=null) {
			client.send(new SocketMessage(SAVE_BLOCK_MESSAGE, this.save_block));
		}
	}
	@Override
	public void rePaint() {
		super.rePaint();
		TetrisClient client=TetrisClient.getTetrisClient();
		if(client!=null) {
			client.send(new SocketMessage(MAP_MESSAGE, this.realtimemap));
		}
	}
}
