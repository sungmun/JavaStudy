package Model;

import Client.TetrisClient;
import Control.PlayUserInformation;
import Serversynchronization.MessageType;
import Serversynchronization.SocketMessage;

public class UserTetrisManager extends TetrisManager implements MessageType{
	private static UserTetrisManager tetrismanager = null;
	public UserTetrisManager() {
		super();
		info=new PlayUserInformation();
	}
	public static UserTetrisManager createTetrisManager() {
		if (tetrismanager == null) {
			tetrismanager = new UserTetrisManager();
		}
		return tetrismanager;
	}
	public static UserTetrisManager getTetrisManager() {
		return tetrismanager;
	}
	@Override
	public void setNext_block(int next_block) {
		super.setNext_block(next_block);
		TetrisClient client=TetrisClient.getTetrisClient();
		if(client!=null) {
			client.send(new SocketMessage(NEXT_BLOCK_MESSAGE, this.next_block));
		}
	}
	@Override
	public void setSave_block(int tetrino) {
		super.setSave_block(tetrino);
		TetrisClient client=TetrisClient.getTetrisClient();
		if(client!=null) {
			client.send(new SocketMessage(SAVE_BLOCK_MESSAGE, this.save_block));
		}
	}
	
}
