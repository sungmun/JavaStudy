package Serversynchronization;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

public class TotalJsonObject {
	/*-----------------------------------------------------------------------------------------------
	 * 이 class는 SimpleJson, Gson 이 두가지 openSource를 사용하기 쉽게 만들어 놓은것이다
	 * Gson openSource는 많은 장점이 있으나 serialization이 되지 않는 class는 save가 아예 않된다는 단점이 있고,
	 * SimpleJson openSource는 Object를 JSON화 시키지 못한다는 단점이 있다
	 * 그래서 기본적인 사용은 SimpleJson을 이용하여 json을 만들고 
	 * object를 server에 json으로 전달하는 경우에만 Gson을 이용하여, message를 전달하도록 하였다
	 *----------------------------------------------------------------------------------------------- */
	private JSONObject object = new JSONObject();

	public TotalJsonObject() {	}

	public TotalJsonObject(String json) {
		jsonParser(json);
	}

	public void addStringProperty(String key,Object value) {
		object.put(key, value.toString());
	}
	public void addStringProperty(String key, String value) {
		addProperty(key, value);
	}
	public void addProperty(String key, Object value) {
		object.put(key, value);
	}


	public void removeValue(Object key) {
		object.remove(key);
	}

	public static <T> T GsonConverter(String gson, Class<T> classOfT) {
		return new Gson().fromJson(gson, classOfT);
	}

	public static String GsonConverter(Object obj) {
		return new Gson().toJson(obj);
	}

	public void jsonParser(String str) {
		JSONParser parser = new JSONParser();
		try {
			object = (JSONObject) parser.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Object get(String key) {
		return object.get(key);
	}

	public String getAsString() {
		return toString();
	}
	public String toString() {
		return object.toJSONString();
	}
}
