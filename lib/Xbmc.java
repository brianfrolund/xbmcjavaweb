package lib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

public class Xbmc {
	private String apiPath = "";
	private String username = "";
	private String password = "";
	private ArrayList<Error> errorLog = new ArrayList<Error>();
	private boolean debug = false;
	//	private HttpURLConnection httpURLConnection;
	private int statusUpdateTimer;
	private int statusUpdateInterval = 1000;

	private Helper helper = new Helper();
	//	private JsonRpc jsonRpc = new JsonRpc();
	//	private System system = new System();
	//	private Status status = new Status();
	//	private Files files = new Files();
	//	private Player player = new Player();
	//	private Playlist playlist = new Playlist();
	//	private Library library = new Library();

	public Xbmc(String address, String port, String username, String password, boolean debug, int updateInterval) {
		this.apiPath = "http://" +address+ ":" +port+ "/jsonrpc";
		this.username= username;
		this.password = password;
		this.debug = debug;
		this.statusUpdateInterval = updateInterval;
		//	    TODO statusupdate thingy	
	}

	public String getApiPath() {
		return this.apiPath;
	}

	public Boolean isDebuggingENabled() {
		return this.debug;
	}

	public String log(String message, String level) {
		//		TODO log method
		return null;
	}

	public String post(String namespace, String method, ArrayList<Object> parameters, Integer id) {
		if (namespace == null || method == null) {
			this.errorLog.add(new Error("0", "No namespace or method specified"));
			return "false";
		}
		else {
			try {
				URL url = new URL(this.apiPath);

				URLConnection connection = url.openConnection();
				((HttpURLConnection)connection).setRequestMethod("POST");

				//connection.setDoInput(true);
				connection.setDoOutput(true);

				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());

				outputStreamWriter.write(helper.getJson(namespace, method, parameters, id));

				outputStreamWriter.flush();

				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

				String allLines = "";
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					allLines += line;
				}
				outputStreamWriter.close();
				bufferedReader.close();
				JSONObject jSONObject = new JSONObject(allLines);

				if (jSONObject.has("error") || allLines.equals("")) {
					if (jSONObject.has("error")) {
						this.errorLog.add(new Error(jSONObject.getJSONObject("error").getString("code"), jSONObject.getJSONObject("error").getString("message")));
					}
					else {
						this.errorLog.add(new Error("1", "No response from server"));
					}
					
					return "false";
				}
				
				else {
					return jSONObject.getString("result");
				}
		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				this.errorLog.add(new Error("2", "Couldn't connect to server"));
			}
		}
		return "hey!";
	}

	public String post(String namespace, String method, ArrayList<Object> parameters) {
		return this.post(namespace, method, parameters, 1);
	}

	public String post(String namespace, String method) {
		return this.post(namespace, method, null, 1);
	}


	public Boolean getResponse(String response) {
		if (response == null || response.equals("")) {
			return false;
		}
		else {
			if (response.equals("OK")) {
				return true;
			}
			else {
				return false;
			}
		}
	}

	public int getVolume() {
		return Integer.valueOf(this.post("XBMC", "GetVolume"));
	}

	public int setVolume(int volume) {
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(new Integer(volume));
		this.post("XBMC", "SetVolume", parameters);
		return 0;
	}

	public ArrayList<Error> getErrors() {
		return this.errorLog;
	}

	public Error getLastError() {
		return this.errorLog.get(errorLog.size()-1);
	}





}
