package Control;

public class UserControl {
	public static UserControl users=new UserControl();
	private User player;
	private User opplayer;

	public UserControl() {
	}

	public User getPlayer() {
		return player;
	}

	public void setPlayer(User player) {
		this.player = player;
	}

	public User getOpplayer() {
		return opplayer;
	}

	public void setOpplayer(User opplayer) {
		this.opplayer = opplayer;
	}

}