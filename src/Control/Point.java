package Control;

public class Point {
	private int x;
	private int y;
	
	public Point(int y,int x) {
		// TODO Auto-generated constructor stub
		this.x=x;
		this.y=y;
	}
	public Point() {
		// TODO Auto-generated constructor stub
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public boolean equals(Point pos) {
		return (pos.getX()==x&&pos.getY()==y)?true:false; 
	}
}
