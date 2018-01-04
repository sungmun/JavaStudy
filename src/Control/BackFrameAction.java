package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import Model.ClientMessage;
import Serversynchronization.MessageType;

public class BackFrameAction implements ActionListener{
	JFrame fopen;
	JFrame fclose;
	public BackFrameAction(JFrame fopen, JFrame fclose) {
		this.fclose=fclose;
		this.fopen=fopen;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		MVC_Connect.ControlToModel.quickCallEvent(ClientMessage.class, MessageType.LOGOUT);
		FrameControl.FrameChange(fopen,fclose);
	}

}
