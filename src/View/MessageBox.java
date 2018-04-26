package View;

import java.awt.BorderLayout;

import View.BaseClass.BasicButton;
import View.BaseClass.BasicFrame;
import View.BaseClass.BasicLabel;

public class MessageBox extends BasicFrame {
	/**
	 * 간단한 메세지를 보여주는 프레임이다 메세지를 받고 그 메세지를 보여주는 역활을 하며 setVisible함수를 진행시, 문자열을 받아서 그
	 * 문자열을 보여주는 역활을 한다
	 */
	private static final long serialVersionUID = -6512678987801334384L;
	BasicLabel lable = null;

	public MessageBox() {
		super();
		setLayout(new BorderLayout());

		lable = new BasicLabel();

		BasicButton okBtn = new BasicButton("OK");
		okBtn.addActionListener(e -> dispose());

		add(lable, BorderLayout.CENTER);
		add(okBtn, BorderLayout.EAST);
	}

	public void setVisible(boolean b, String lableText) {
		super.setVisible(b);
		lable.setText(lableText);
	}

}
