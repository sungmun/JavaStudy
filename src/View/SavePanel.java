package View;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;

import Control.ImagePrint;
import View.BaseClass.BasicLabel;
import View.BaseClass.BasicPanel;

public class SavePanel extends BasicPanel {
	/**
	 * 레이블 하나와 저장된 블럭이 나오는 Panel을 포함하며
	 * 레이블에는 "저장된 블럭"을 표시를 해준다. 
	 */
	private static final long serialVersionUID = -8323785066524362139L;

	public SavePanel() {
		super(true);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(ImagePrint.WIDTH * 5, ImagePrint.HEIGHT * 5 + 18));

		add(new BasicLabel("저장된 블럭", Font.BOLD, 18));
		add(new SaveBlockPanel());
	}
}
