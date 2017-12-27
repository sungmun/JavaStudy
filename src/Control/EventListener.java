package Control;

import org.json.simple.JSONObject;

public interface EventListener {
	public void onEvent(String event);
	public void methodCatch(JSONObject event);
}
