package lib;

import java.util.ArrayList;

public class Helper {
	private static Helper helper = null;
	
	public static Helper getHelper() {
		if (helper == null) {
			helper = new Helper();
		}
		return helper;
	}
	
	public String getJson(String namespace, String method, ArrayList<Object> parameters, Integer id) {
		
		String parameterString = "";
		
		if (parameters != null) {
			for (Object object : parameters) {
				if (object instanceof Integer) {
					parameterString += ",\"params\":"+object.toString();
				}
			}
		}
		return "{\"jsonrpc\":\"2.0\",\"method\":\"" +namespace+ "." +method+ "\"" +parameterString+ ",\"id\":" +id+ "}";
	}
	
	
}
