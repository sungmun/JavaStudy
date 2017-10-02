package View;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import Control.TetrisControlManager;
import Model.CellSize;

@SuppressWarnings("serial")
public class SavePanel extends JPanel implements CellSize{
	private BlockBackGroudPanel saveblockbackground;
	private BasicJLabel title;
	public SavePanel(TetrisControlManager manager) {
		super(true);
		setOpaque(false);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(width*5, height*5+18));
		
		title=new BasicJLabel("����� ��", Font.BOLD, 18);
		saveblockbackground=new BlockBackGroudPanel(new SaveBlockPanel(manager));
		
		add(title);
		add(saveblockbackground);
	}
}
