package Model;

public class Space implements SpaceSize{
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
	public boolean equals(Space spc) {
		return (isblock==spc.isblock)?true:false; 
	}
}
