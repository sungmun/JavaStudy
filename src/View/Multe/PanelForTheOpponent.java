package View.Multe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import Control.UserControl;
import View.GameOverPanel;
import View.MainPanel;
import View.BaseClass.BasicLabel;
import View.BaseClass.BasicPanel;

@SuppressWarnings("serial")
public class PanelForTheOpponent extends JLayeredPane{
	public PanelForTheOpponent() {
		BasicPanel basePanel = new BasicPanel(new BorderLayout(), true);

		MainPanel mainPanel = new MainPanel();
		BasicLabel label = new BasicLabel(UserControl.users.getOpplayer().getName(), Font.BOLD, 16);
		basePanel.add(label, BorderLayout.NORTH);
		basePanel.add(mainPanel, BorderLayout.CENTER);
		
		Rectangle bound = new Rectangle(basePanel.getPreferredSize());
		basePanel.setBounds(bound);

		JPanel gameOverPanel = new JPanel();
		gameOverPanel.setBackground(Color.BLACK);
		gameOverPanel.setBounds(bound);

		GameOverPanel gameOverViewPanel = new GameOverPanel();
		Dimension temp = gameOverViewPanel.getPreferredSize();
		gameOverViewPanel.setBounds((int) ((mainPanel.getPreferredSize().getWidth() / 2) - (temp.getWidth() / 2)),
				(int) ((mainPanel.getPreferredSize().getHeight() / 2) - (temp.getHeight() / 2)),
				(int) temp.getWidth() + 50, (int) temp.getHeight() + 10);

		this.setPreferredSize(basePanel.getPreferredSize());

		this.add(basePanel);
		this.add(gameOverPanel);
		this.add(gameOverViewPanel);
	}
}
