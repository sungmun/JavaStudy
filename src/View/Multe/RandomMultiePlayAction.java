package View.Multe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import Control.TetrisClient;
import Serversynchronization.MessageType;
import Serversynchronization.SocketMessage;
import Serversynchronization.User;
import Serversynchronization.UsersList;

public class RandomMultiePlayAction implements ActionListener, MessageType {

	TetrisClient client = TetrisClient.getTetrisClient();

	@Override
	public void actionPerformed(ActionEvent e) {

		int random = new Random().nextInt(UsersList.getList().size());
		User us = UsersList.getList().get(random);
		client.send(new SocketMessage(USER_SELECTING, us));
	}

}
