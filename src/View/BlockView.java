package View;

import java.awt.Color;

import Control.Point;
import Model.TetrinoType;

public class BlockView implements TetrinoType{
	private Color Blockcol;
	private Point pos;
	private int width,height;
	
	public BlockView(Color col, Point pos, int width, int height) {
		this.Blockcol=col;
		this.pos=pos;
		this.width=width;
		this.height=height;
	}
	
	public static Color SetBlockColor(int type){
		switch(type){
		case OTYPE:
			return new Color(255, 255, 0);
		case ITYPE:
			return new Color(135, 206, 235);
		case STYPE:
			return new Color(0, 128, 0);
		case ZTYPE:
			return new Color(255, 0, 0);
		case LTYPE:
			return new Color(248, 155, 0);
		case JTYPE:
			return new Color(0, 153, 255);
		case TTYPE:
			return new Color(102, 0, 153);
		default:
			return new Color(128, 128, 128);
		}
	}
	
	public void setWidth(int width) {
		this.width=width;
	}
	public void setHeight(int height) {
		this.height=height;
	}
	public void setPos(Point pos) {
		this.pos=pos;
	}
	public void setBlockColor(Color col) {
		this.Blockcol=col;
	}
	public int getwidth(){
		return width;
	}
	public int getheight(){
		return height;
	}
	public Color getColor(){
		return Blockcol;
	}
	public Point getPos(){
		return pos;
	}
}
