package Control;

import java.util.ArrayList;

import Model.Observable;

public class UserEvent implements Observable {
	private static ArrayList<Observer> observers=new ArrayList<>();
	
	public void add(Observer observer) {
		observers.add(observer);
	}

	public void notifyObserver(String title, String source) {
		for (Observer observer : observers) {
			observer.update(title, source);
		}
	}
	
}
