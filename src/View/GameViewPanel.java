package View;

@SuppressWarnings("serial")
public class GameViewPanel extends BasicPanel {

	public GameViewPanel() {
		super(true);
		add(new TetrinoBlockPanel());
	}
}
