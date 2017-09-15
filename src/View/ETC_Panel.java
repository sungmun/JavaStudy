package View;

import java.awt.Dimension;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ETC_Panel extends JPanel {

	private NextPanel nextpanel;
	private SavePanel savepanel;
	private ScorePanel scorepanel;
	private LevelPanel levelpanel;
	
	public ETC_Panel(int width, int height) {

		setOpaque(false);
		setPreferredSize(new Dimension(width * 5 + 20, height * 20 + 60));

		nextpanel=new NextPanel(width, height);
		savepanel=new SavePanel(width, height);
		scorepanel=new ScorePanel(width);
		levelpanel=new LevelPanel(width);
		
		add(nextpanel);
		add(savepanel);
		add(scorepanel);
		add(levelpanel);
		add(new RankButton(width));
		add(new ExitButton(width));
		
	}
}
