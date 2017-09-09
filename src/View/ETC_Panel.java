package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
		
		nextpanel.setOpaque(false);
		savepanel.setOpaque(false);

		nextpanel.setLayout(new BoxLayout(nextpanel, BoxLayout.Y_AXIS));
		savepanel.setLayout(new BoxLayout(savepanel, BoxLayout.Y_AXIS));

		next_block_panel = NextBlockPanel.createNextBlockPanel(width, height);
		save_block_panel = SaveBlockPanel.createSaveBlockPanel(width, height);

		next_block_background = BlockBackGroudPanel.createNextBlockBackGroudPanel(width, height, next_block_panel);
		save_block_background = BlockBackGroudPanel.createSaveBlockBackGroudPanel(width, height, save_block_panel);

		JLabel lbl_next = new JLabel("���� ��", SwingConstants.CENTER);
		JLabel lbl_save = new JLabel("����� ��", SwingConstants.CENTER);

		lbl_next.setFont(new Font(lbl_next.getFont().getFontName(), lbl_next.getFont().getStyle(), 18));
		lbl_save.setFont(new Font(lbl_save.getFont().getFontName(), lbl_save.getFont().getStyle(), 18));

		lbl_next.setForeground(Color.WHITE);
		lbl_save.setForeground(Color.WHITE);

		nextpanel.add(lbl_next);
		savepanel.add(lbl_save);

		nextpanel.add(next_block_background);
		savepanel.add(save_block_background);

		nextpanel.setPreferredSize(new Dimension(width*5, height*5+18));
		savepanel.setPreferredSize(new Dimension(width*5, height*5+18));
		
		add(nextpanel);
		add(savepanel);
	}
}
