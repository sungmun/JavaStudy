package Serversynchronization;

public class PlayerInformation implements Cloneable{
	
	private int level=1;
	private int score=0;
	public PlayerInformation() {
	}
	public PlayerInformation(int level, int score) {
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	@Override
	protected Object clone() {
		Object obj=null;
		try {
			obj= super.clone();
			return obj;
		}catch (CloneNotSupportedException e) {
			return null;
		}
	}

}
