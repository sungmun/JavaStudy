package View;

import java.awt.Dimension;

import javax.swing.JPanel;

import Control.TetrisControlManager;
import Model.CellSize;

@SuppressWarnings("serial")
public class ETC_Panel extends JPanel implements CellSize{

	private NextPanel nextpanel;
	private SavePanel savepanel;
	private ScorePanel scorepanel;
	private LevelPanel levelpanel;
	
	public ETC_Panel(TetrisControlManager manager) {

		setOpaque(false);
		setPreferredSize(new Dimension(width * 5 + 20, height * 20 + 60));

		nextpanel=new NextPanel(manager);
		savepanel=new SavePanel(manager);
		scorepanel=new ScorePanel(manager);
		levelpanel=new LevelPanel(manager);
		
		add(nextpanel);
		add(savepanel);
		add(scorepanel);
		add(levelpanel);
		add(new RankButton(width));
		add(new ExitButton(width));
		
	}
}
