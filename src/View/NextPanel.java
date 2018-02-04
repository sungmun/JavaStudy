package View;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;

import Control.ImagePrint;
import View.BaseClass.BasicLabel;
import View.BaseClass.BasicPanel;

@SuppressWarnings("serial")
public class NextPanel extends BasicPanel{
	public NextPanel( ) {
		super(true);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(ImagePrint.WIDTH*5, ImagePrint.HEIGHT*5+18));
		
		add(new BasicLabel("다음블럭", Font.BOLD, 18));
		add(new NextBlockPanel());
	}
}
