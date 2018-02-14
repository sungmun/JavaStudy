package View;

import java.awt.Dimension;

import javax.swing.BorderFactory;

import Control.ImagePrint;
import View.BaseClass.BasicPanel;

public class ETC_Panel extends BasicPanel {

	/**
	 * EtcPanel은 게임에서 블럭이 내려오는 Panel을 제외한 그외의 Panel이며
	 * NextPanel, SavePanel, ScorePanel, LevelPanel을 포함하며, 
	 * ExitButton도 포함된다.
	 */
	private static final long serialVersionUID = -3306255840049170421L;

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
