package View;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import Control.TetrisControlManager;
import Model.CellSize;

@SuppressWarnings("serial")
public class NextPanel extends JPanel implements CellSize{
	private BlockBackGroudPanel nextblockbackground;
	private BasicJLabel title;
	public NextPanel(TetrisControlManager manager) {
		// TODO Auto-generated constructor stub
		setOpaque(false);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(width*5, height*5+18));
		
		title=new BasicJLabel("다음 블럭", Font.BOLD, 18);
		nextblockbackground=new BlockBackGroudPanel(new NextBlockPanel(manager));
		
		add(title);
		add(nextblockbackground);
	}
}
