package Serversynchronization;

public class PlayerInformation {
	
	private int level=0;
	private int score=0;
	public PlayerInformation() {
	}
	public PlayerInformation(int level, int score) {
		setLevel(level);
		setScore(score);
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


}
