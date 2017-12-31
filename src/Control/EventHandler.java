package Control;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.simple.JSONObject;

public class EventHandler {

	private final int MAX_THREAD_POOL = 5;

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

	private synchronized void callEventByAsynch(final Class<?> caller, final JSONObject event) {

		for (final EventListener listener : listeners) {
			if (listener.getClass().getName().equals(caller.getName()))
				listener.onEvent(event);
		}

	}

	public synchronized void callEvent(final Class<?> caller, JSONObject msg1) {
		callEventByAsynch(caller, msg1);
	}

	public synchronized void quickCallEvent(final Class<?> caller, Object msg1) {
		quickCallEvent(caller, msg1, null);
	}

	public synchronized void quickCallEvent(final Class<?> caller, Object msg1, Object msg2) {
		quickCallEvent(caller, msg1, msg2, null);
	}

	public synchronized void quickCallEvent(final Class<?> caller, Object msg1, Object msg2, Object msg3) {
		quickCallEvent(caller, msg1, msg2, msg3, null);
	}

	public synchronized void quickCallEvent(final Class<?> caller, Object msg1, Object msg2, Object msg3, Object msg4) {
		quickCallEvent(caller, msg1, msg2, msg3, msg4, null);
	}

	public synchronized void quickCallEvent(final Class<?> caller, Object msg1, Object msg2, Object msg3, Object msg4,
			Object msg5) {
		JSONObject msg = new JSONObject();

		Object[] msgs = { msg1, msg2, msg3, msg4, msg5 };
		for (int i = 0; i < msgs.length; i++) {
			if (msgs[i] == null)
				break;
			msg.put(msgs[i].getClass().getName(), msgs[i]);
		}
		callEventByAsynch(caller, msg);
	}

}
