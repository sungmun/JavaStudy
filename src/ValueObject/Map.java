package ValueObject;

public class Map {
	
	private static int width,height;

	Space[][] map;
	
	
	public Map(int width,int height) {
		// TODO Auto-generated constructor stub
		Map.width=width;
		Map.height=height;
		this.map=new Space[height][width];
	}

	public static int getMapWidth(){
		return Map.width;
	}
	public static int getMapHeight(){
		return Map.height;
	}
	public Space[][] getMap(){
		return map;
	}
}
