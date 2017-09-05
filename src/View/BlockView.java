package View;

import java.awt.Color;
import java.awt.Image;

import Control.Point;

public class BlockView {

	Point pos;

	// width and height of DrawingPanel JPanel
	private int dpWidth;
	private int dpHeight;

	// image to draw
	private Image image;
	private Color col;

	public BlockView(int dpWidth, int dpHeight, Point pos) {
		this.dpHeight = dpHeight;
		this.dpWidth = dpWidth;
		this.pos = pos;
		this.col=new Color(255, 0, 0, 0);
	}

	public void BlockChange(Color col) {
		this.setCol(col);
	}

	public int getDpWidth() {
		return dpWidth;
	}

	public void setDpWidth(int dpWidth) {
		this.dpWidth = dpWidth;
	}

	public int getDpHeight() {
		return dpHeight;
	}

	public void setDpHeight(int dpHeight) {
		this.dpHeight = dpHeight;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Color getCol() {
		return col;
	}

	public void setCol(Color col) {
		this.col = col;
	}

	public Point getPos() {
		return pos;
	}

	public void setPos(Point pos) {
		this.pos = pos;
	}

}
