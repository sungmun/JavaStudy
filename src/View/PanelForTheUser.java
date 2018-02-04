package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import Control.Tic;
import Control.TicAction;
import Control.UserControl;
import View.BaseClass.BasicLabel;
import View.BaseClass.BasicPanel;

@SuppressWarnings("serial")
public class PanelForTheUser extends JLayeredPane  {
	GameOverPanel gameOverViewPanel;
	JPanel gameOverPanel;

	public PanelForTheUser() {
		super();

		BasicPanel basePanel = new BasicPanel(new BorderLayout(), true);

		MainPanel mainPanel = new MainPanel();
		BasicLabel label = new BasicLabel(UserControl.users.getPlayer().getName(), Font.BOLD, 16);
		basePanel.add(label, BorderLayout.NORTH);
		basePanel.add(mainPanel, BorderLayout.CENTER);

		Rectangle bound = new Rectangle(basePanel.getPreferredSize());
		basePanel.setBounds(bound);

		gameOverPanel = new JPanel();
		gameOverPanel.setBackground(Color.BLACK);
		gameOverPanel.setLayout(new BorderLayout(5, 5));
		gameOverPanel.setBounds(bound);

		gameOverViewPanel = new GameOverPanel();
		Dimension temp = gameOverViewPanel.getPreferredSize();
		gameOverViewPanel.setBounds((int) ((bound.getWidth() / 2) - (temp.getWidth() / 2)),
				(int) ((bound.getHeight() / 2) - (temp.getHeight() / 2)), (int) temp.getWidth() + 50,
				(int) temp.getHeight() + 10);

		this.setPreferredSize(basePanel.getPreferredSize());

		this.add(basePanel);
		this.add(gameOverPanel);
		this.add(gameOverViewPanel);
	}

	public Tic getTimer() {
		return new Tic(500, new TicAction()) {

			@Override
			public void timerstop() {
				gameOverViewPanel.initInfo();
				gameOverPanel.setBackground(new Color(0, 0, 0, 122));
				moveToFront(gameOverPanel);
				moveToFront(gameOverViewPanel);
				moveToFront(gameOverViewPanel);
			}
		};
	}

}
