package View;

import java.awt.Color;

import Control.Point;

public class BlockView {
	private Color Blockcol;
	private Point pos;
	
	public void setPos(Point pos) {
		this.pos=pos;
	}
	public void setBlockColor(Color col) {
		this.Blockcol=col;
	}
	public Color getColor(){
		return Blockcol;
	}
	public Point getPos(){
		return pos;
	}
}
