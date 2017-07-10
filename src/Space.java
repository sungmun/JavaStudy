
public class Space {
	private boolean isblock=false; //블럭이 있으면 true;
	public void spaceChange() {
		// TODO Auto-generated method stub
		isblock=(!isblock);
	}
	public boolean getSpace(){
		return isblock;
	}
	@Override
	public String toString() {
		return "Space";
	}
}
