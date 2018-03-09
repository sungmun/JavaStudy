package Model;

import java.util.Random;

public enum TetrinoType {
	DEFULT,
	ITYPE,
	JTYPE,
	LTYPE,
	OTYPE,
	STYPE,
	TTYPE,
	ZTYPE;
	public static TetrinoType getRandomTetrino() {
		Random random=new Random();
		return values()[random.nextInt(values().length)];
	}
}
