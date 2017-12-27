package Control;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.gson.Gson;

import Serversynchronization.SocketMessage;

public class EventHandler {

	private final int MAX_THREAD_POOL = 5;

	private List<EventListener> listeners = new CopyOnWriteArrayList<EventListener>();

	private synchronized List<EventListener> getListeners() {
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

	public synchronized void callEvent(final Class<?> caller, SocketMessage event) {
		callEvent(caller, event);
	}

	private synchronized void callEventByAsynch(final Class<?> caller, final SocketMessage event) {
		ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREAD_POOL);

		for (final EventListener listener : listeners) {
			executorService.execute(new Runnable() {
				public void run() {
					if (!listener.getClass().getName().equals(caller.getName())) 
						listener.onEvent(new Gson().toJson(event)));
				}
			});
		}

		executorService.shutdown();
	}

}
