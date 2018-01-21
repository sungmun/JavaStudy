package View;

import java.awt.Color;

import javax.swing.JFrame;

import Control.EventListener;
import Control.MVC_Connect;
import Control.Tic;
import Serversynchronization.TotalJsonObject;

@SuppressWarnings("serial")
public class GameBasicFrame extends JFrame implements EventListener{
	protected int speed = 500;
	public static Tic time;
	public GameBasicFrame( ) {
		super("Tetris");
		MVC_Connect.ControlToView.addListener(this);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		this.getContentPane().setBackground(Color.BLACK);
	}
	@Override
	public void onEvent(Object event) {
		TotalJsonObject object=new TotalJsonObject(event.toString());
		
		if(object.get("method")==null) {
			return;
		}
		methodCatch(object);
	}

	public void methodCatch(Object event) {
		TotalJsonObject object=(TotalJsonObject) event;
		switch (object.get("method").toString()) {
		case "setVisible":
			setVisible((Boolean)object.get("boolean"));
			break;
		case "dispose":
			dispose();
			break;
		case "stop":
			time.stop();
			break;
		default:
			break;
		}
	}
}
