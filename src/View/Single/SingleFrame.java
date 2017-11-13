package View.Single;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import Control.ImagePrint;
import Control.KeyBoardEvent;
import Control.Tic;
import Control.TicAction;
import Control.UserEvent;
import View.CellSize;
import View.GameBasicFrame;
import View.GameOverPanel;
import View.LevelPanel;
import View.MainPanel;
import View.ScorePanel;

@SuppressWarnings("serial")
public class SingleFrame extends GameBasicFrame implements CellSize {

	static private SingleFrame singleviewcopy = null;

	public SingleFrame() {
		MainPanel mainpanel = new MainPanel();
		Rectangle basebounds=new Rectangle(mainpanel.getPreferredSize());
		mainpanel.setBounds(basebounds);

		JPanel game_over = new JPanel();
		game_over.setBackground(Color.BLACK);
		game_over.setLayout(new BorderLayout(5, 5));
		game_over.setBounds(basebounds);
		
		GameOverPanel gameoverview=new GameOverPanel();
		Dimension temp=gameoverview.getPreferredSize();
		gameoverview.setBounds((int)((basebounds.getWidth()/2)-(temp.getWidth()/2)), (int)((basebounds.getHeight()/2)-(temp.getHeight()/2)), (int)temp.getWidth()+50, (int)temp.getHeight()+10);
		
		JLayeredPane layer = new JLayeredPane();
		layer.setPreferredSize(mainpanel.getPreferredSize());

		layer.add(mainpanel);
		layer.add(game_over);
		layer.add(gameoverview);

		
		ImagePrint print = new ImagePrint(mainpanel);
		UserEvent event = new UserEvent();
		event.add((ScorePanel) print.getContmap().get(ScorePanel.class));
		event.add((LevelPanel) print.getContmap().get(LevelPanel.class));
		event.add(gameoverview);
		
		this.add(layer);

		this.setUndecorated(true);
		this.pack();
		this.setLocationRelativeTo(null);
		TicAction action = new TicAction(print) {
			@Override
			public void timeStop() {
				time.stop();
			}

			@Override
			public void speedChange() {
				int delay = (int) (500 * Math.pow(0.999, manager.info.getLevel() - 1));
				if (delay > 10) {
					time.setDelay(delay);
				}
			}
		};
		time = new Tic(speed, action) {
			@Override
			public void timerstop() {
				game_over.setBackground(new Color(0, 0, 0, 122));
				gameoverview.initInfo();
				layer.moveToFront(game_over);
				layer.moveToFront(gameoverview);
				layer.moveToFront(gameoverview);
			}
		};

		time.start();
		this.addKeyListener(new KeyBoardEvent(print));
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
