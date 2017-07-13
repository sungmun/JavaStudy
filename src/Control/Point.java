package Control;

public class Point {
	private int x;
	private int y;
	public Point(int y,int x) {
		// TODO Auto-generated constructor stub
		this.x=x;
		this.y=y;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public boolean equals(Point pos) {
		return (pos.getX()==x&&pos.getY()==y)?true:false; 
	}
}
