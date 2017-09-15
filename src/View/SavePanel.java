package View;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SavePanel extends JPanel{
	private BlockBackGroudPanel saveblockbackground;
	private BasicJLabel title;
	public SavePanel(int width, int height) {
		// TODO Auto-generated constructor stub
		setOpaque(false);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(width*5, height*5+18));
		
		title=new BasicJLabel("����� ��", Font.BOLD, 18);
		saveblockbackground=new BlockBackGroudPanel(width, height, SaveBlockPanel.createSaveBlockPanel(width, height));
		
		add(title);
		add(saveblockbackground);
	}
}
