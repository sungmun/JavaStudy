package Control;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import Serversynchronization.TotalJsonObject;


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

	private synchronized void callEventByAsynch(final Class<?> caller, final Object event) {

		for (final EventListener listener : listeners) {
			if (listener.getClass().getName().equals(caller.getName())) {
				listener.onEvent(event);
			}
		}

	}

	public synchronized void callEvent(final Class<?> caller, Object msg1) {
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
		TotalJsonObject msg=new TotalJsonObject();
		Object[] msgs = { msg1, msg2, msg3, msg4, msg5 };
		for (int i = 0; i < msgs.length; i++) {
			if (msgs[i] == null)
				break;
			msg.addProperty(msgs[i].getClass().getName(), msgs[i].toString());
		}
		callEventByAsynch(caller, msg.toString());
	}

}
