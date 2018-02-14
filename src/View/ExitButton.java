package View;

import java.awt.Dimension;
import java.awt.Font;

import View.BaseClass.BasicButton;

public class ExitButton extends BasicButton {
	/**
	 * 게임을 종료시키는 이벤트와 연결된 버튼으로 
	 * 이 버튼을 클릭시, 프레임이 완전히 종료가된다 
	 */
	private static final long serialVersionUID = -7266153750431455156L;
	public ExitButton() {
		super("Exit");
		addActionListener(e->System.exit(0));
	}
	public ExitButton(Dimension size) {
		this();
		setFont(new Font("Exit", Font.BOLD, 23));
		setPreferredSize(size);
		
	}

}
