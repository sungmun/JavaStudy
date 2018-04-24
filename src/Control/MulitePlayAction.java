package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import Model.ClientMessage;
import Serversynchronization.User;
import Serversynchronization.UsersList;

public class MulitePlayAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		final boolean isRandom = (e.getActionCommand().equals("RandomStart")) ? true : false;
		final int LIST_SIZE = UsersList.list.size();

		if (LIST_SIZE < 1)
			return;

		if (isRandom) {
			randomSelectedAction(LIST_SIZE);
		} else {
			selectedAction();
		}

	}

	private void selectedAction() {
		User user=UserListModel.getData();
		if(user==null) {
			new FrameControl().showMessageDialog("경고", "데이터를 선택하세요");
			return;
		}
		ClientMessage.getClientMessageInstanse().UserSelecting(user);
	}

	private void randomSelectedAction(final int LIST_SIZE) {
		UserListModel.selectedRow=new Random().nextInt(LIST_SIZE);
		selectedAction();
	}

}
