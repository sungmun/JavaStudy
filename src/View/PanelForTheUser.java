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
public class PanelForTheUser extends JLayeredPane  {
	/**
	 * 이 Panel은 사용자의 정보를 보여주는 패널로 sendDataPanel에서 이 패널을 기준으로 
	 * 클래스가 사용자인지 상대방인지 구분을 한다
	 * 이 패널은 레이어 Panel이며 게임의 진행을 보여주는 패널과 게임 오버를 보여주는 패널
	 * 그리고 두개의 패널사이에 게임오버시에 게임의 진행을 보여주는 패널에 불투명한 막을 쳐주는 Panel로 이루어져 있다
	 */
	private static final long serialVersionUID = -7798929311983373813L;
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
