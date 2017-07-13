package Model;
public class Map {
	
	private static int width,height;
	
	private static Map createmap=null;
	Space[][] map;
	
	
	private Map(int width,int height) {
		// TODO Auto-generated constructor stub
		Map.width=width;
		Map.height=height;
		this.map=new Space[width][height];
	}
	
	public static Map createMap(int width,int height){
		if(createmap==null){
			createmap=new Map(width,height);
		}
		return createmap;
	}
	public void changeMap(int width,int height){
		Map.width=width;
		Map.height=height;
		this.map=new Space[width][height];
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
