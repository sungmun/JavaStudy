package View;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;

import Control.EventListener;
import Control.FrameChangeAction;
import Control.MVC_Connect;
import Serversynchronization.TotalJsonObject;
import View.BaseClass.BasicButton;
import View.Multe.LoginFrame;
import View.Single.SingleFrame;

@SuppressWarnings("serial")
public class StartFrame extends JFrame implements EventListener{

	public StartFrame() {
		super();
		MVC_Connect.ControlToView.addListener(this);
		
		BasicButton single = new BasicButton("혼자하기");
		single.addActionListener(new FrameChangeAction(SingleFrame.class, this.getClass()));
		add(single);

		BasicButton mulite = new BasicButton("같이하기");
		mulite.addActionListener(new FrameChangeAction(LoginFrame.class, this.getClass()));
		add(mulite);
		
		BasicButton exit=new BasicButton("Exit");
		exit.addActionListener((e)->System.exit(0));
		exit.setPreferredSize(single.getPreferredSize());
		add(exit);
		
		
		this.getContentPane().setBackground(Color.BLACK);
		this.setUndecorated(true);
		this.setResizable(false);
		this.setLayout(new FlowLayout());
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	@Override
	public void onEvent(Object event) {
		TotalJsonObject object=(TotalJsonObject) event;
		
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
			this.dispose();
			break;
		default:
			break;
		}
	}
}
