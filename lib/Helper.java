package lib;

import java.util.ArrayList;

import org.json.JSONObject;

public class Helper {
	private static Helper helper = null;

	public static Helper getHelper() {
		if (helper == null) {
			helper = new Helper();
		}
		return helper;
	}
	//	{"message": "foo", "level": "info"}
	public String getJson(String namespace, String method, Object parameters, Integer id) {

		String parameterString = "";

		if (parameters != null) {
			if (parameters instanceof JSONObject) {
				parameterString = ",\"params\":" + parameters.toString();
			}
			else if (parameters instanceof Integer) {
				parameterString = ",\"params\":" + parameters.toString();
			}
			else {
				parameterString = ",\"params\":\"" +parameters.toString()+"\"";

			}			 
		}
		return "{\"jsonrpc\":\"2.0\",\"method\":\"" +namespace+ "." +method+ "\"" +parameterString+ ",\"id\":" +id+ "}";
	}


}
