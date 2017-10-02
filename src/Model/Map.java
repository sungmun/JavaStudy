package Model;
public class Map {
	
	private static int width,height;

	Space[][] map;
	
	
	public Map(int width,int height) {
		// TODO Auto-generated constructor stub
		Map.width=width;
		Map.height=height;
		this.map=new Space[height][width];
		for(int y=0;y<height;y++){
			for(int x=0;x<width;x++){
				map[y][x]=new Space();
			}
		}
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
