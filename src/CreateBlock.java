public class CreateBlock implements TetrinoType{
	static int type;
	public static Tetrino tetrinoRandomCreate(){
		type=(int)(Math.random()*10)%6;
		switch(type){
		case OTYPE:
			return tetrinoOType();
		case ITYPE:
			return tetrinoIType();
		case STYPE:
			return tetrinoSType();
		case ZTYPE:
			return tetrinoZType();
		case LTYPE:
			return tetrinoLType();
		case JTYPE:
			return tetrinoJType();
		default:
			return tetrinoTType();
		}
	}
	private static Tetrino tetrinoOType(){
		int[][] type={
				{0,0,0,0},
				{0,1,1,0},
				{0,1,1,0},
				{0,0,0,0},
		};
		return new Tetrino(type);
	}
	private static Tetrino tetrinoIType(){
		int[][] type={
				{0,0,0,0},
				{1,1,1,1},
				{0,0,0,0},
				{0,0,0,0},
		};
		return new Tetrino(type);
	}
	private static Tetrino tetrinoSType(){
		int[][] type={
				{0,0,0,0},
				{0,0,1,1},
				{0,1,1,0},
				{0,0,0,0},
		};
		return new Tetrino(type);
	}
	private static Tetrino tetrinoZType(){
		int[][] type={
				{0,0,0,0},
				{0,1,1,0},
				{0,0,1,1},
				{0,0,0,0},
		};
		return new Tetrino(type);
	}
	private static Tetrino tetrinoLType(){
		int[][] type={
				{0,0,0,0},
				{0,1,1,1},
				{0,1,0,0},
				{0,0,0,0},
		};
		return new Tetrino(type);
	}
	private static Tetrino tetrinoJType(){
		int[][] type={
				{0,0,0,0},
				{0,1,1,1},
				{0,0,0,1},
				{0,0,0,0},
		};
		return new Tetrino(type);
	}
	private static Tetrino tetrinoTType(){
		int[][] type={
				{0,0,0,0},
				{0,1,1,1},
				{0,0,1,0},
				{0,0,0,0},
		};
		return new Tetrino(type);
	}
}
