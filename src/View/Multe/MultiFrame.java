package View.Multe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import Control.KeyBoardEvent;
import Control.Tic;
import Control.TicAction;
import Control.User;
import View.BasicJLabel;
import View.CellSize;
import View.GameBasicFrame;
import View.GameOverPanel;
import View.MainPanel;

@SuppressWarnings("serial")
public class MultiFrame extends GameBasicFrame implements CellSize {

	static private MultiFrame multeviewcopy = null;

	private MultiFrame(User user, User opponet) {
		setLayout(new GridLayout(1, 2));
		/*--------------------------------------------------------------------*/
		JPanel userp = new JPanel(new BorderLayout(), true);

		MainPanel usermainpanel = new MainPanel();
		BasicJLabel userlabel = new BasicJLabel(user.getName(), Font.BOLD, 16);
		userp.add(userlabel, BorderLayout.NORTH);
		userp.add(usermainpanel, BorderLayout.CENTER);
		userp.setOpaque(false);

		Rectangle basebounds = new Rectangle(userp.getPreferredSize());
		userp.setBounds(basebounds);

		JPanel usergameover = new JPanel();
		usergameover.setBackground(Color.BLACK);
		usergameover.setLayout(new BorderLayout(5, 5));
		usergameover.setBounds(basebounds);

		GameOverPanel usergameoverview = new GameOverPanel();
		Dimension temp = usergameoverview.getPreferredSize();
		usergameoverview.setBounds((int) ((basebounds.getWidth() / 2) - (temp.getWidth() / 2)),
				(int) ((basebounds.getHeight() / 2) - (temp.getHeight() / 2)), (int) temp.getWidth() + 50,
				(int) temp.getHeight() + 10);

		JLayeredPane userlayer = new JLayeredPane();
		userlayer.setPreferredSize(userp.getPreferredSize());

		userlayer.add(userp);
		userlayer.add(usergameover);
		userlayer.add(usergameoverview);

		this.add(userlayer);

		/*----------------------------------------유저-------------------------------------------*/
		JPanel opppanel = new JPanel(new BorderLayout(), true);

		MainPanel oppmainpanel = new MainPanel();
		BasicJLabel opplabel = new BasicJLabel(user.getName(), Font.BOLD, 16);
		opppanel.add(opplabel, BorderLayout.NORTH);
		opppanel.add(oppmainpanel, BorderLayout.CENTER);
		opppanel.setOpaque(false);

		basebounds = new Rectangle(opppanel.getPreferredSize());
		opppanel.setBounds(basebounds);

		JPanel oppgameover = new JPanel();
		oppgameover.setBackground(Color.BLACK);
		oppgameover.setBounds(basebounds);

		GameOverPanel oppgameoverview = new GameOverPanel();
		temp = oppgameoverview.getPreferredSize();
		oppgameoverview.setBounds((int) ((oppmainpanel.getPreferredSize().getWidth() / 2) - (temp.getWidth() / 2)),
				(int) ((oppmainpanel.getPreferredSize().getHeight() / 2) - (temp.getHeight() / 2)),
				(int) temp.getWidth() + 50, (int) temp.getHeight() + 10);

		JLayeredPane opplayer = new JLayeredPane();
		opplayer.setPreferredSize(opppanel.getPreferredSize());

		opplayer.add(opppanel);
		opplayer.add(oppgameover);
		opplayer.add(oppgameoverview);

		this.add(opplayer);
		/*----------------------------------------상대방유저-------------------------------------------*/

		this.setUndecorated(true);
		this.pack();
		this.setLocationRelativeTo(null);

		TicAction action = new TicAction();
		time = new Tic(speed, action) {
			@Override
			public void timerstop() {
				usergameoverview.initInfo();
				usergameover.setBackground(new Color(0, 0, 0, 122));
				userlayer.moveToFront(usergameover);
				userlayer.moveToFront(usergameoverview);
				userlayer.moveToFront(usergameoverview);
			}
		};
		time.start();

		this.addKeyListener(new KeyBoardEvent());
	}

	@Override
	public void update(Graphics g) {
		paintComponents(g);
	}

	public static MultiFrame createMulteFrame(User user, User opponet) {
		if (multeviewcopy == null) {
			multeviewcopy = new MultiFrame(user, opponet);
		}
		return multeviewcopy;
	}

	public static MultiFrame getMulteFrame() {
		return multeviewcopy;
	}
}
