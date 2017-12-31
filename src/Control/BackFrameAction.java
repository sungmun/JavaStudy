package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import Client.ClientMessage;
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
		ServerConnect.ControlToServer.quickCallEvent(ClientMessage.class.getClass(), MessageType.LOGOUT);
		FrameControl.FrameChange(fopen,fclose);
	}

}
