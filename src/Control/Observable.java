package Control;

public interface Observable {
	public void add(Observer observer) ;
	public void notifyObserver(String title, String source) ;
}
