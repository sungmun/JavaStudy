package View.Multe;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.Timer;

import Control.ImagePrint;
import Control.KeyBoardEvent;
import Control.OpponentEvent;
import Control.TicAction;
import Control.UserEvent;
import Model.MoveType;
import Serversynchronization.User;
import View.BasicJLabel;
import View.CellSize;
import View.GameBasicFrame;
import View.LevelPanel;
import View.MainPanel;
import View.ScorePanel;

@SuppressWarnings("serial")
public class MultiFrame extends GameBasicFrame implements MoveType, CellSize {

	static private MultiFrame multeviewcopy = null;

	private MultiFrame(User user,User opponet) {
		setLayout(new GridLayout(1, 2));

		JPanel userp = new JPanel(new BorderLayout(), true);
		JPanel opponentp = new JPanel(new BorderLayout(), true);

		userp.setOpaque(false);
		opponentp.setOpaque(false);

		MainPanel userpanel = new MainPanel();
		BasicJLabel userlabel = new BasicJLabel(user.getName(), Font.BOLD, 16);
		userp.add(userlabel, BorderLayout.NORTH);
		userp.add(userpanel, BorderLayout.CENTER);
		add(userp);

		MainPanel opponentpanel = new MainPanel();
		BasicJLabel opponentlabel = new BasicJLabel(user.getName(), Font.BOLD, 16);
		opponentp.add(opponentlabel, BorderLayout.NORTH);
		opponentp.add(opponentpanel, BorderLayout.CENTER);
		add(opponentp);

		ImagePrint userprint = new ImagePrint(userpanel);
		ImagePrint opponentprint = new ImagePrint(opponentpanel);

		this.pack();
		this.setLocationRelativeTo(null);

		time = new Timer(speed, new TicAction(userprint) {

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
		UserEvent usevent = new UserEvent();
		usevent.add((ScorePanel) userprint.getContmap().get(ScorePanel.class));
		usevent.add((LevelPanel) userprint.getContmap().get(LevelPanel.class));

		OpponentEvent opevent = OpponentEvent.createOpponentEvent();
		opevent.setPrint(opponentprint);
		opevent.add((ScorePanel) opponentprint.getContmap().get(ScorePanel.class));
		opevent.add((LevelPanel) opponentprint.getContmap().get(LevelPanel.class));
		this.addKeyListener(new KeyBoardEvent(userprint));
	}

	@Override
	public void update(Graphics g) {
		paintComponents(g);
	}

	public static MultiFrame createMulteFrame(User user,User opponet) {
		if (multeviewcopy == null) {
			multeviewcopy = new MultiFrame(user, opponet);
		}
		return multeviewcopy;
	}

	public static MultiFrame getMulteFrame() {
		return multeviewcopy;
	}
}
