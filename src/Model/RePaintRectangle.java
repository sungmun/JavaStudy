package Model;

import java.awt.Rectangle;

@SuppressWarnings("serial")
public class RePaintRectangle extends Rectangle {

	public RePaintRectangle(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public String toString() {
		return "rePaintRectangle [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + "]";
	}
	
}
