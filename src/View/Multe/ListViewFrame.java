package View.Multe;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Serversynchronization.MessageType;
import View.FrameMoveAction;
import View.StartFrame;

@SuppressWarnings("serial")
public class ListViewFrame extends JFrame implements MessageType{
	ListView list;
	JButton  mulite_play_btn,back_frame_btn,refreash_btn,random_multie_play_btn;
	public ListViewFrame(){
		list=new ListView();
		JPanel etc=new JPanel();
		mulite_play_btn=new JButton("������ ���� �����ϱ�");
		random_multie_play_btn=new JButton("������ ���� �����ϱ�");
		back_frame_btn=new JButton("����ȭ������ ����");
		
		mulite_play_btn.addActionListener(new MultePlayAction(list));
		random_multie_play_btn.addActionListener(new RandomMultiePlayAction());
		back_frame_btn.addActionListener(new FrameMoveAction(new StartFrame(), this));
		
		etc.add(mulite_play_btn);
		etc.add(random_multie_play_btn);
		etc.add(back_frame_btn);
		
		add(list);
	}
}
