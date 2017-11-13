package View;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ETC_Panel extends JPanel implements CellSize {

	private NextPanel nextpanel;
	private SavePanel savepanel;
	private ScorePanel scorepanel;
	private LevelPanel levelpanel;

	public ETC_Panel() {

		super(true);
		setOpaque(false);
		setPreferredSize(new Dimension(width * 5 + 20, height * 20));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		nextpanel = new NextPanel();
		savepanel = new SavePanel();
		scorepanel = new ScorePanel();
		levelpanel = new LevelPanel();

		add(nextpanel);
		add(savepanel);
		add(scorepanel);
		add(levelpanel);
		add(new ExitButton(width));
	}

	@Override
	public void update(Graphics g) {
		paintComponent(g);
	}

	@Override
	public void repaint() {
		super.repaint();
		try {
			scorepanel.repaint();
			levelpanel.repaint();
		} catch (NullPointerException e) {
		}
	}
}
