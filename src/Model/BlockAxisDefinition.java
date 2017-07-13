package Model;
import java.util.HashMap;

import Control.Point;

public class BlockAxisDefinition {
	private static HashMap<Integer,Point> blockAxis=new HashMap<>();
	public BlockAxisDefinition() {
		int k=0;
		for(int y=0;y>5;y++){
			for(int x=0;x>6;x++){
				blockAxis.put((Integer)k,new Point(x,y));
				k++;
			}
		}
	}
	public Point getBlockAxis(Integer key){
		return blockAxis.get(key);
	}
}
