package View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Control.Point;
import Control.TetrisControlManager;
import Model.MoveType;

public class MainView extends JFrame implements MoveType{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	TetrisMapPanel mappanel;
	TetrinoBlockPanel nowmapblockpanel;
	TetrisControlManager manager=TetrisControlManager.createTetrisControlManager();
	static private Timer time;
	double speed=0.1;

	public MainView(int width,int height) {
		super("Tetris");
		int cellwidth=width*10;
		int cellheight=height*20;
		setSize(cellwidth+300, cellheight+50);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainpanel=new JPanel(); 
		mappanel = new TetrisMapPanel(width,height);
		nowmapblockpanel=TetrinoBlockPanel.createTetrinoBlockPanel(width, height);
		
		mappanel.setLayout(null);
		nowmapblockpanel.setLayout(null);
		mainpanel.setLayout(null);
		
		mappanel.setBounds(6, 6, cellwidth,cellheight);
		nowmapblockpanel.setBounds(1, 1, cellwidth-2,cellheight-3);
		mainpanel.setBounds(20, 20, cellwidth+260, cellheight+10);
		
		nowmapblockpanel.setBorder(new TitledBorder(new LineBorder(Color.RED)));
		
		mainpanel.setOpaque(false);
		nowmapblockpanel.setOpaque(false);
		mappanel.setOpaque(false);
		
		getContentPane().setBackground(Color.BLACK);
		
		mappanel.add(nowmapblockpanel);
		mainpanel.add(mappanel);
		add(mainpanel);
		
		time=new Timer((int) (1000*speed), new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(manager.getNowTetrino()==null){
					manager.createBlock();
				}else{
					if(!manager.TetrinoBlockMove(DOWN)){
						Point nowpos=manager.tetrino.getFlowTetrino();
						if(manager.gameOverCheack(nowpos)){
							getTime().stop();
						}
						manager.setNowTetrino(null);
					}
				}
				blockMoveRePaint();
			}
		});
		time.start();
		blockMoveRePaint();
		
		addKeyListener(new KeyBoardEvent());
		
		setVisible(true);
	}
	public MainView() {
		// TODO Auto-generated constructor stub
	}
	public static Timer getTime() {
		return time;
	}
	public void blockMoveRePaint(){
		invalidate();
		repaint();
	}
	public TetrisMapPanel getTetrisMapPanel(){
		return mappanel;
	}
	public TetrinoBlockPanel getTetrinoBlockPanel(){
		return nowmapblockpanel;
	}
	public void speedUp() {
		speed+=0.1;
	}
}
