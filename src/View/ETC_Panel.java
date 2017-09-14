package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Control.TetrisControlManager;

@SuppressWarnings("serial")
public class ETC_Panel extends JPanel {

	private NextBlockPanel next_block_panel;
	private SaveBlockPanel save_block_panel;

	private BlockBackGroudPanel next_block_background;
	private BlockBackGroudPanel save_block_background;
	
	
	public ETC_Panel(int width, int height) {

		setOpaque(false);
		setPreferredSize(new Dimension(width * 5 + 20, height * 20 + 60));

		JPanel nextpanel = new JPanel();
		JPanel savepanel = new JPanel();
		JPanel score_panel=new JPanel();
		JPanel level_panel=new JPanel();
		
		nextpanel.setOpaque(false);
		savepanel.setOpaque(false);
		score_panel.setOpaque(false);
		level_panel.setOpaque(false);

		nextpanel.setLayout(new BoxLayout(nextpanel, BoxLayout.Y_AXIS));
		savepanel.setLayout(new BoxLayout(savepanel, BoxLayout.Y_AXIS));
		score_panel.setLayout(new BoxLayout(score_panel, BoxLayout.Y_AXIS));
		level_panel.setLayout(new BoxLayout(level_panel, BoxLayout.Y_AXIS));

		next_block_panel = NextBlockPanel.createNextBlockPanel(width, height);
		save_block_panel = SaveBlockPanel.createSaveBlockPanel(width, height);

		next_block_background = BlockBackGroudPanel.createNextBlockBackGroudPanel(width, height, next_block_panel);
		save_block_background = BlockBackGroudPanel.createSaveBlockBackGroudPanel(width, height, save_block_panel);

		
		
		JLabel lbl_next = new JLabel("다음 블럭", SwingConstants.CENTER);
		JLabel lbl_save = new JLabel("저장된 블럭", SwingConstants.CENTER);
		JLabel lbl_score_title = new JLabel("점수", SwingConstants.CENTER);
		JLabel lbl_level_title = new JLabel("레벨", SwingConstants.CENTER);
		JLabel lbl_score = new JLabel(Integer.toString(TetrisControlManager.getTetrisControlManager().getScore()));
		JLabel lbl_level = new JLabel(Integer.toString(TetrisControlManager.getTetrisControlManager().getLevel()));
		
		
		lbl_score_title.setFont(new Font("고딕체", Font.BOLD, 23));
		lbl_score.setFont(new Font("고딕체", Font.BOLD, 23));
		lbl_level_title.setFont(new Font("고딕체", Font.BOLD, 23));
		lbl_level.setFont(new Font("고딕체", Font.BOLD, 23));
		lbl_next.setFont(new Font(lbl_next.getFont().getFontName(), lbl_next.getFont().getStyle(), 18));
		lbl_save.setFont(new Font(lbl_save.getFont().getFontName(), lbl_save.getFont().getStyle(), 18));

		lbl_score_title.setForeground(Color.WHITE);
		lbl_score.setForeground(Color.WHITE);
		lbl_level_title.setForeground(Color.WHITE);
		lbl_level.setForeground(Color.WHITE);
		
		lbl_next.setForeground(Color.WHITE);
		lbl_save.setForeground(Color.WHITE);

	
		score_panel.add(lbl_score_title);
		score_panel.add(lbl_score);
		
		level_panel.add(lbl_level_title);
		level_panel.add(lbl_level);
		
		nextpanel.add(lbl_next);
		savepanel.add(lbl_save);

		nextpanel.add(next_block_background);
		savepanel.add(save_block_background);

		nextpanel.setPreferredSize(new Dimension(width*5, height*5+18));
		savepanel.setPreferredSize(new Dimension(width*5, height*5+18));
		score_panel.setPreferredSize(new Dimension(width*5, 70));
		level_panel.setPreferredSize(new Dimension(width*5, 70));
		
		add(nextpanel);
		add(savepanel);
		add(score_panel);
		add(level_panel);
	}
}
