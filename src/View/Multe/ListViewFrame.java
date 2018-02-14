package View.Multe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import Control.FrameChangeAction;
import Control.MulitePlayAction;
import View.StartFrame;
import View.BaseClass.BasicButton;
import View.BaseClass.BasicFrame;
import View.BaseClass.BasicPanel;

public class ListViewFrame extends BasicFrame{
	/**
	 * 다른사람과 같이 게임을 할 떄 나오는 Frame으로 서버에 접속하여
	 * 같이 게임이 가능한 유저의 목록을 보여준다. 그리고 같이 게임을 하기 위해 
	 * 선택을 할시 상대방에개 같이 게임을 할것인지 게임여부를 알려준다  
	 */
	private static final long serialVersionUID = 3904446234633812536L;
	ListView list;
	BasicButton mulite_play_btn, back_frame_btn, random_multie_play_btn;

	public ListViewFrame() {
		super("접속자 목록");
		
		setLayout(new BorderLayout());
		list = new ListView();

		JPanel etc = new BasicPanel(new GridLayout(3, 1));
		etc.setBackground(Color.BLACK);

		mulite_play_btn = new BasicButton("Start",new MulitePlayAction());
		etc.add(mulite_play_btn);

		random_multie_play_btn = new BasicButton("RandomStart",new MulitePlayAction());
		etc.add(random_multie_play_btn);

		back_frame_btn = new BasicButton("Back",new FrameChangeAction(new StartFrame().getClass(), this.getClass()));
		etc.add(back_frame_btn);
		
		add(list, BorderLayout.WEST);
		add(etc, BorderLayout.EAST);

	}
}
