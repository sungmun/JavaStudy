package View;

import java.awt.Color;

import Model.TetrinoType;

public enum TetrisBlockColor {
	DEFULT,
	ITYPE,
	JTYPE,
	LTYPE,
	OTYPE,
	STYPE,
	TTYPE,
	ZTYPE;
	public static Color getColor(TetrinoType type) {
		switch (type) {
		case DEFULT:
			return new Color(255,0,0,0);
		case ITYPE:
			return new Color(135, 206, 235); // - I
		case JTYPE:
			return new Color(0, 153, 255); // - J
		case LTYPE:
			return new Color(248, 155, 0); // - L
		case OTYPE:
			return new Color(255, 255, 0); // - O
		case STYPE:
			return new Color(0, 128, 0); //  - S
		case TTYPE:
			return new Color(102, 0, 153); //  - T
		case ZTYPE:
			return new Color(255, 0, 0); //  - Z
		}
		return new Color(255,0,0,0);
	}

}
