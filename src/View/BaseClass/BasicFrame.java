package View.BaseClass;

import java.awt.Color;
import java.awt.GraphicsConfiguration;

import javax.swing.JFrame;

import Control.EventListener;
import Control.MVC_Connect;

public abstract class BasicFrame extends JFrame implements EventListener{
	private static final long serialVersionUID = 5233852257830218529L;

	public BasicFrame(String title,GraphicsConfiguration GC) {
		super(title,GC);
		init();
	}
	public BasicFrame(String title) {
		super(title);
		init();
	}
	public BasicFrame(GraphicsConfiguration GC) {
		super(GC);
		init();
	}
	public BasicFrame() {
		super();
		init();
	}
	public void init() {
		this.getContentPane().setBackground(Color.BLACK);
		this.setUndecorated(true);
		this.setResizable(false);
		MVC_Connect.ControlToView.addListener(this);
	}
	
	@Override
	public void dispose() {
		super.dispose();
		MVC_Connect.ControlToView.removeListener(this);
	}
	@Override
	public void setVisible(boolean b) {
		super.setVisible(b);
		if(b) {
			this.pack();
			this.setLocationRelativeTo(null);
		}
	}
}
