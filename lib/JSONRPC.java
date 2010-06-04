package lib;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONRPC {
	private XBMC xbmc;
	private String namespace = "JSONRPC";

	public JSONRPC(XBMC xbmc) {
		this.xbmc = xbmc;
	}
	
	public JSONObject introspect(boolean getdescriptions, boolean getpermissions, boolean filterbytransport) {
		JSONObject parameters = new JSONObject();
		try {
			parameters.append("getdescriptions", getdescriptions);
			parameters.append("getpermissions", getdescriptions);
			parameters.append("filterbytransport", getdescriptions);
			return new JSONObject(xbmc.post(namespace, "Introspect", parameters));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String version() {
		try {
			JSONObject response = new JSONObject(xbmc.post(namespace, "Version"));
			return response.getString("version");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public JSONArray permission() {
		try {
			JSONObject response = new JSONObject(xbmc.post(namespace, "Permission"));
			return response.getJSONArray("permission");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String ping() {
		return xbmc.post(namespace, "Ping");
	}
	
	public boolean announce(String sender, String message, String data) {
		JSONObject parameters = new JSONObject();
		try {
			parameters.append("sender", sender);
			parameters.append("message", message);
			if(!data.equals("") && data != null) {
			parameters.append("data", data);
			}
			return Helper.getHelper().getBoolResponse(xbmc.post(namespace, "Announce", parameters));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean announce(String sender, String message) {
		return announce(sender, message, null);
	}
}
