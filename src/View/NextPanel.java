package View;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;

import Control.ImagePrint;
import View.BaseClass.BasicLabel;
import View.BaseClass.BasicPanel;

public class NextPanel extends BasicPanel {
	/**
	 * 레이블 하나와 다음블럭이 나오는 Panel을 포함하며
	 * 레이블에는 "다음블럭"을 표시를 해준다.
	 */
	private static final long serialVersionUID = -6148937966248971324L;

	public NextPanel() {
		super(true);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(ImagePrint.WIDTH * 5, ImagePrint.HEIGHT * 5 + 18));

		add(new BasicLabel("다음블럭", Font.BOLD, 18));
		add(new NextBlockPanel());
	}
}
