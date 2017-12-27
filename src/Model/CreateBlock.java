package Model;

public class CreateBlock  {

	public Tetrino tetrinoRandomCreate() {
		return tetrinoChoiceCreate(TetrinoType.getRandomTetrino());
	}

	public Tetrino tetrinoChoiceCreate(TetrinoType type) {
		switch (type) {
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
		case TTYPE:
			return tetrinoTType();
		default:
			return tetrinoZType();
		}
	}

	private Tetrino tetrinoOType() {
		int[][] type = { 
				{ 0, 0, 0, 0 }, 
				{ 0, 1, 1, 0 }, 
				{ 0, 1, 1, 0 }, 
				{ 0, 0, 0, 0 }, };

		return new Tetrino(type, TetrinoType.OTYPE);
	}

	private Tetrino tetrinoIType() {
		int[][] type = { 
				{ 0, 0, 1, 0 }, 
				{ 0, 0, 1, 0 }, 
				{ 0, 0, 1, 0 }, 
				{ 0, 0, 1, 0 }, };
		return new Tetrino(type, TetrinoType.ITYPE);
	}

	private Tetrino tetrinoSType() {
		int[][] type = { 
				{ 0, 0, 0, 0 }, 
				{ 0, 0, 1, 1 }, 
				{ 0, 1, 1, 0 }, 
				{ 0, 0, 0, 0 }, };
		return new Tetrino(type, TetrinoType.STYPE);
	}

	private Tetrino tetrinoZType() {
		int[][] type = { 
				{ 0, 0, 0, 0 }, 
				{ 0, 1, 1, 0 }, 
				{ 0, 0, 1, 1 }, 
				{ 0, 0, 0, 0 }, };
		return new Tetrino(type, TetrinoType.ZTYPE);
	}

	private Tetrino tetrinoLType() {
		int[][] type = { 
				{ 0, 0, 0, 0 }, 
				{ 0, 1, 1, 1 }, 
				{ 0, 1, 0, 0 }, 
				{ 0, 0, 0, 0 }, };
		return new Tetrino(type, TetrinoType.LTYPE);
	}

	private Tetrino tetrinoJType() {
		int[][] type = { 
				{ 0, 0, 0, 0 }, 
				{ 0, 1, 1, 1 }, 
				{ 0, 0, 0, 1 }, 
				{ 0, 0, 0, 0 }, 
				};
		return new Tetrino(type, TetrinoType.JTYPE);
	}

	private Tetrino tetrinoTType() {
		int[][] type = { 
				{ 0, 0, 0, 0 }, 
				{ 0, 1, 1, 1 }, 
				{ 0, 0, 1, 0 }, 
				{ 0, 0, 0, 0 }, };
		return new Tetrino(type, TetrinoType.TTYPE);
	}
}