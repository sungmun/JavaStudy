package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import Model.ClientMessage;
import Serversynchronization.MessageType;
import View.Multe.ListView;

public class MulitePlayAction implements ActionListener {
	ListView list;
	boolean isRandom;

	public MulitePlayAction(ListView list, boolean isRandom) {
		this.list = list;
		this.isRandom = isRandom;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int index = 0;
		if (UsersList.getList().size() < 1)
			return;
		
		if (isRandom) {
			index = new Random().nextInt(UsersList.getList().size());
		} else {
			Object us = list.getData();
			if (UsersList.findList(us))
				index = UsersList.getList().indexOf(us);
		}

		User us = UsersList.getList().get(index);
		MVC_Connect.ControlToModel.quickCallEvent(ClientMessage.class, MessageType.USER_SELECTING,us);
	}

}
