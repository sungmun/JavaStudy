package Control;

public class OpponentControl extends ControlBasic{
	
	private static OpponentControl control=null;
	private OpponentControl() {
		super(OpponentTetrisControlManager.createTetrisControlManager());
	}
	public static OpponentControl createOpponentControl() {
		if(control==null) {
			control=new OpponentControl();
		}
		return control;
	}
	public static OpponentControl getOpponentControl() {
		return control;
	}
	
	public static void setOpponentControl(ControlBasic userControl) {
		control=(OpponentControl) userControl;
	}
	
		
	
}
