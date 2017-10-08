package View.Multe;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JPanel;
import javax.swing.Timer;

import Control.ImagePrint;
import Control.KeyBoardEvent;
import Control.OpponentEvent;
import Control.TicAction;
import Control.UserEvent;
import Model.MoveType;
import Model.OpponentControl;
import Model.UserControl;
import View.CellSize;
import View.GameBasicFrame;
import View.LevelPanel;
import View.MainPanel;
import View.ScorePanel;

@SuppressWarnings("serial")
public class MultiFrame extends GameBasicFrame implements MoveType, CellSize {

	static private MultiFrame multeviewcopy = null;

	private MultiFrame() {
		setLayout(new GridLayout(1, 2));

		JPanel user = new JPanel(new BorderLayout(), true);
		JPanel opponent = new JPanel(new BorderLayout(), true);

		user.setOpaque(false);
		opponent.setOpaque(false);

		MainPanel userpanel = new MainPanel();
		user.add(new Label(UserControl.getUserControl().getUser().getName()), BorderLayout.NORTH);
		user.add(userpanel, BorderLayout.CENTER);
		add(user);

		MainPanel opponentpanel = new MainPanel();
		opponent.add(new Label(OpponentControl.getOpponentControl().getUser().getName()), BorderLayout.NORTH);
		opponent.add(opponentpanel, BorderLayout.CENTER);
		add(opponent);

		ImagePrint userprint = new ImagePrint(userpanel);
		ImagePrint opponentprint = new ImagePrint(opponentpanel);

		this.pack();
		this.setLocationRelativeTo(null);

		time=new Timer(speed, new TicAction(userprint) {
			
			@Override
			public void timeStop() {
				// TODO Auto-generated method stub
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
		time.start();
		UserEvent usevent=new UserEvent();
		usevent.add((ScorePanel)userprint.getContmap().get(ScorePanel.class));
		usevent.add((LevelPanel)userprint.getContmap().get(LevelPanel.class));
		
		OpponentEvent opevent=OpponentEvent.createOpponentEvent();
		opevent.setPrint(opponentprint);
		opevent.add((ScorePanel)opponentprint.getContmap().get(ScorePanel.class));
		opevent.add((LevelPanel)opponentprint.getContmap().get(LevelPanel.class));
		this.addKeyListener(new KeyBoardEvent(userprint));
	}

	@Override
	public void update(Graphics g) {
		paintComponents(g);
	}

	public static MultiFrame createMulteFrame() {
		if (multeviewcopy == null) {
			multeviewcopy = new MultiFrame();
		}
		return multeviewcopy;
	}

	public static MultiFrame getMulteFrame() {
		return multeviewcopy;
	}
}
