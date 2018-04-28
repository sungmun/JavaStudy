package Control;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class EventHandler {

	private List<EventListener> listeners = new CopyOnWriteArrayList<EventListener>();

	public synchronized List<EventListener> getListeners() {
		return listeners;
	}

	public synchronized void addListener(EventListener eventListener) {
		if (getListeners().indexOf(eventListener) == -1) {
			listeners.add(eventListener);
		}
	}

	public synchronized void removeListener(EventListener eventListener) {
		if (getListeners().indexOf(eventListener) != -1) {
			listeners.remove(eventListener);
		}
	}

	private synchronized void callEventByAsynch(final Class<?> caller, final CallBackEvent event) {

		for (final EventListener listener : listeners) {
			if (listener.getClass().getName().equals(caller.getName())) {
				listener.onEvent(event);
			}
		}

	}

	public synchronized void callEvent(final Class<?> caller, CallBackEvent msg1) {
		callEventByAsynch(caller, msg1);
	}


}
