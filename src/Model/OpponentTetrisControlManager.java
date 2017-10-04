package Model;

public class OpponentTetrisControlManager extends TetrisControlManager {
	private static OpponentTetrisControlManager tetrismanager = null;

	public OpponentTetrisControlManager() {
		super();
	}

	public static OpponentTetrisControlManager createTetrisControlManager() {
		if (tetrismanager == null) {
			tetrismanager = new OpponentTetrisControlManager();
		}
		return tetrismanager;
	}

	public static OpponentTetrisControlManager getTetrisControlManager() {
		return tetrismanager;
	}
	@Override
	public void setLevel(int level) {
		super.setLevel(level);
		rePaint();
	}
	@Override
	public void setScore(int score) {
		super.setScore(score);
	}
	@Override
	public void setNext_block(Tetrino next_block) {
		super.setNext_block(next_block);
	}
	@Override
	public void setRealtimemap(Space[][] realtimemap) {
		super.setRealtimemap(realtimemap);
		rePaint();
	}
	@Override
	public void setSave_block(Tetrino save_block) {
		super.setSave_block(save_block);
	}
}
