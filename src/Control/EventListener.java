package Control;

import org.json.simple.JSONObject;

public interface EventListener {
	public void onEvent(JSONObject event);
	public void methodCatch(JSONObject event);
}
