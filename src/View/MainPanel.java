package View;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class MainPanel extends JPanel {

	private TetrisMapPanel mappanel;

	private BlockBackGroudPanel blockbackground;
	private NextBlockPanel next_panel;

	public MainPanel(int width, int height) {
		JPanel etc_panel = new JPanel();
		mappanel = new TetrisMapPanel(width, height);

		blockbackground = BlockBackGroudPanel.createBlockBackGroudPanel(width, height);
		next_panel = NextBlockPanel.createNextBlockPanel(width, height);

		etc_panel.setLayout(null);
		etc_panel.setBounds(10 + width * 10, 6, 270, height * 20);
		etc_panel.setOpaque(false);

		etc_panel.setBorder(new TitledBorder(new LineBorder(Color.RED)));

		setLayout(null);
		setBounds(20, 20, width * 10 + 260, height * 20 + 10);
		setOpaque(false);

		add(mappanel);
		add(etc_panel);
	}

	public TetrisMapPanel getMappanel() {
		return mappanel;
	}

	public void setMappanel(TetrisMapPanel mappanel) {
		this.mappanel = mappanel;
	}

	public BlockBackGroudPanel getBlockbackground() {
		return blockbackground;
	}

	public void setBlockbackground(BlockBackGroudPanel blockbackground) {
		this.blockbackground = blockbackground;
	}

	public NextBlockPanel getNext_panel() {
		return next_panel;
	}

	public void setNext_panel(NextBlockPanel next_panel) {
		this.next_panel = next_panel;
	}

}
