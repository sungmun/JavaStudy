package Control;

import java.util.ArrayList;

import Model.Observable;
import Model.OpponentTetrisControlManager;
import Model.ValueObject.Space;

public class OpponentEvent implements Observable {
	private static ArrayList<Observer> observers = new ArrayList<>();
	private static OpponentEvent opponentevent=null;
	OpponentTetrisControlManager manager=OpponentTetrisControlManager.getTetrisControlManager();
	ImagePrint print;
	
	public static OpponentEvent getOpponentEvent() {
		return opponentevent;
	}

	public static OpponentEvent createOpponentEvent() {
		if(opponentevent==null) {
			opponentevent=new OpponentEvent();
		}
		return opponentevent;
	}
	public void setPrint(ImagePrint print) {
		this.print=print;
	}
	public void managerSetRealtimemap(Space[][] realtimemap) {
		manager.setRealtimemap(realtimemap);
		print.TetrinoBlockPaint(manager);
	}
	public void managerSetScore(int score) {
		manager.info.setScore(score);
		notifyObserver("score", Integer.toString(score));
	}
	public void managerSetLevel(int level) {
		manager.info.setLevel(level);
		notifyObserver("level", Integer.toString(level));
	}
	public void managerSetNextBlock(int next_block) {
		manager.setNext_block(next_block);
		print.NextBlockPaint(manager);
	}
	public void managerSetSaveBlock(int next_block) {
		manager.setNext_block(next_block);
		print.SaveBlockPaint(manager);
	}
	
	@Override
	public void add(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void notifyObserver(String title, String source) {
		for (Observer observer : observers) {
			observer.update(title, source);
		}
	}
	
	

}
