package Model;

import Client.TetrisClient;
import Control.PlayUserInformation;
import Serversynchronization.MessageType;
import Serversynchronization.SocketMessage;

public class UserTetrisControlManager extends TetrisControlManager implements MessageType{
	private static UserTetrisControlManager tetrismanager = null;
	public UserTetrisControlManager() {
		super();
		info=new PlayUserInformation();
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
