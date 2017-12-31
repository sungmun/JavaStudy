package Model;

import Control.User;

public class UserInformation {
	private User user;
	private int level;
	private int score;

	public UserInformation(User user, int level, int score) {
		this.user = user;
		this.level = level;
		this.score = score;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setLevel(int level) {
		
		this.level = level;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		setLevel(score/1000);
		this.score = score;
	}

}
