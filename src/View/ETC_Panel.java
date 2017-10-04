package View;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import Model.CellSize;
import Model.TetrisControlManager;

@SuppressWarnings("serial")
public class ETC_Panel extends JPanel implements CellSize{

	private NextPanel nextpanel;
	private SavePanel savepanel;
	private ScorePanel scorepanel;
	private LevelPanel levelpanel;
	
	public ETC_Panel(TetrisControlManager manager) {
		
		super(true);
		setOpaque(false);
		setPreferredSize(new Dimension(width * 5 + 20, height * 20 ));

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
	public void update(Graphics g) {
		paintComponent(g);
	}
	@Override
	public void repaint() {
		super.repaint();
		try {
			scorepanel.repaint();
			levelpanel.repaint();
		} catch (NullPointerException e) {		}
	}
}
