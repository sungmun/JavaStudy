package View;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class LevelPanel extends JPanel {
	
	private static LevelPanel levelpanel;
	
	BasicJLabel title;
	BasicJLabel level;
	
	int height;
	
	public LevelPanel(int width) {
		
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
	
	public void setLevel(int level) {
		this.level.setText(Integer.toString(level));
		repaint();
	}
	public static LevelPanel getLevelPanel() {
		return levelpanel;
	}

}
