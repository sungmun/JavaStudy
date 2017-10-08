package View;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SavePanel extends JPanel implements CellSize {
	private BlockBackGroudPanel saveblockbackground;
	private BasicJLabel title;

	public SavePanel() {
		super(true);
		setOpaque(false);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(width * 5, height * 5 + 18));

		title = new BasicJLabel("저장된 블럭", Font.BOLD, 18);
		saveblockbackground = new BlockBackGroudPanel(new SaveBlockPanel());

		add(title);
		add(saveblockbackground);
	}
}
