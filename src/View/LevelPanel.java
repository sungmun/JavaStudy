package View;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

import Control.TetrisControlManager;
import Model.CellSize;

@SuppressWarnings("serial")
public class LevelPanel extends JPanel implements CellSize{
	
	private static LevelPanel levelpanel;
	
	TetrisControlManager manager;
	
	BasicJLabel title;
	BasicJLabel level;
	
	int height;
	
	public LevelPanel(TetrisControlManager manager) {
		
		height=70;
		
		setOpaque(false);
		setPreferredSize(new Dimension(width*5, height+10));
		
		title=new BasicJLabel("·¹º§", Font.BOLD, 23);
		level=new BasicJLabel("1", Font.BOLD, 23);

		title.setPreferredSize(new Dimension(width*5,height/2));
		level.setPreferredSize(new Dimension(width*5,height/2));
		
		add(title);
		add(level);
		
		levelpanel=this;
	}
	
	public static LevelPanel getLevelPanel() {
		return levelpanel;
	}
	@Override
	public void repaint() {
		level.setText(Integer.toString(manager.getLevel()));
		super.repaint();
	}

}
