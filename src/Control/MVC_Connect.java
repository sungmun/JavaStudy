package Control;

import org.json.simple.JSONObject;

public class MVC_Connect implements EventListener{
	public static EventHandler ModelToControl=new EventHandler();
	public static EventHandler ControlToModel=new EventHandler();
	public static EventHandler ControlToView=new EventHandler();
	public static EventHandler ViewToControl=new EventHandler();
	public MVC_Connect() {
		ModelToControl.addListener(this);
		ViewToControl.addListener(this);
	}
	@Override
	public void onEvent(JSONObject event) {
		System.out.println("MVC_Connect.onEvent()");
		System.out.println(event.toString());
	}
	@Override
	public void methodCatch(JSONObject event) {
		// TODO Auto-generated method stub
		
	}
	public void stringCatch(JSONObject event) {
		// TODO Auto-generated method stub
		
	}
}
