package View;

import java.awt.Dimension;

import javax.swing.BorderFactory;

import Control.ImagePrint;
import View.BaseClass.BasicPanel;

@SuppressWarnings("serial")
public class ETC_Panel extends BasicPanel {

	public ETC_Panel() {
		super(true);

		setPreferredSize(new Dimension(ImagePrint.WIDTH * 5 + 20, ImagePrint.HEIGHT * 20));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		add(new NextPanel());
		add(new SavePanel());
		add(new ScorePanel());
		add(new LevelPanel());
		add(new ExitButton(new Dimension(ImagePrint.WIDTH * 5, 35)));
	}
}
