package Control;

import javax.swing.JFrame;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FrameControl implements EventListener {
	public FrameControl() {
		MVC_Connect.ModelToControl.addListener(this);
		MVC_Connect.ViewToControl.addListener(this);
	}

	public void FrameChange(Object fropen, Object frclose) {
		
		JSONObject open = new JSONObject();
		open.put("method", "setVisible");
		open.put("boolean", true);

		JSONObject close = new JSONObject();
		close.put("method", "dispose");

		MVC_Connect.ControlToView.callEvent(fropen.getClass(), open);
		MVC_Connect.ControlToView.callEvent(frclose.getClass(), close);
	}

	public void FrameClose(JFrame frclose) {

	}

	@Override
	public void onEvent(String event) {
		JSONObject json;
		try {
			json = (JSONObject) new JSONParser().parse(event);

			if (json.get("method") != null) {
				String method = json.get("method").toString();
				methodCatch(json);
				if (method == "FrameChange") {
					FrameChange(json.get("firstFrame"), json.get("secondFrame"));
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private void methodCatch(JSONObject event) {

	}
}
