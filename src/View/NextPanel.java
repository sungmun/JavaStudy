package View;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import Model.CellSize;
import Model.TetrisControlManager;

@SuppressWarnings("serial")
public class NextPanel extends JPanel implements CellSize{
	private BlockBackGroudPanel nextblockbackground;
	private BasicJLabel title;
	public NextPanel(TetrisControlManager manager) {
		super(true);
		setOpaque(false);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(width*5, height*5+18));
		
		title=new BasicJLabel("���� ��", Font.BOLD, 18);
		nextblockbackground=new BlockBackGroudPanel(new NextBlockPanel(manager));
		
		add(title);
		add(nextblockbackground);
	}
}
