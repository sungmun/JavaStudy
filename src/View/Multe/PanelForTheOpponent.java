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

public class PanelForTheOpponent extends JLayeredPane{
	/**
	 * 이 Panel은 상대방의 정보를 보여주는 패널로 sendDataPanel에서 이 패널을 기준으로 
	 * 클래스가 사용자인지 상대방인지 구분을 한다
	 * 이 패널은 레이어 Panel이며 게임의 진행을 보여주는 패널과 게임 오버를 보여주는 패널
	 * 그리고 두개의 패널사이에 게임오버시에 게임의 진행을 보여주는 패널에 불투명한 막을 쳐주는 Panel로 이루어져 있다
	 */
	private static final long serialVersionUID = 5347581434103257865L;

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
