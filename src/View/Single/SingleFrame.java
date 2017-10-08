package View.Single;

import javax.swing.Timer;

import Control.ImagePrint;
import Control.KeyBoardEvent;
import Control.TicAction;
import Control.UserEvent;
import View.CellSize;
import View.GameBasicFrame;
import View.LevelPanel;
import View.MainPanel;
import View.ScorePanel;

@SuppressWarnings("serial")
public class SingleFrame extends GameBasicFrame implements  CellSize {

	static private SingleFrame singleviewcopy = null;
	

	public SingleFrame() {

		MainPanel mainpanel = new MainPanel();
		ImagePrint print = new ImagePrint(mainpanel);

		this.add(mainpanel);

		this.pack();
		this.setLocationRelativeTo(null);

		time = new Timer(speed, new TicAction(print) {

			@Override
			public void timeStop() {
				time.stop();
			}

			@Override
			public void speedChange() {
				int delay = (int) (500 * Math.pow(1.005, manager.info.getLevel() - 1));
				if (delay < 10) {
					time.setDelay(delay);
				}
			}
		});
		UserEvent event=new UserEvent();
		event.add((ScorePanel)print.getContmap().get(ScorePanel.class));
		event.add((LevelPanel)print.getContmap().get(LevelPanel.class));
		time.start();
		addKeyListener(new KeyBoardEvent(print));
	}

	public static SingleFrame createSingleFrame() {
		if (singleviewcopy == null) {
			singleviewcopy = new SingleFrame();
		}
		return singleviewcopy;
	}

	public static SingleFrame getMainviewcopy() {
		return singleviewcopy;
	}
}
