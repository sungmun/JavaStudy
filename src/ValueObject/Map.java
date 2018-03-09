package ValueObject;

public class Map {
	
	int width=0,height=0;

	Space[][] map;
	
	public Map(int width,int height) {
		this.width=width;
		this.height=height;
		this.map=new Space[height][width];
	}

	public Space[][] getMap(){
		return map.clone();
	}
}
