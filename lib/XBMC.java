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

public class XBMC {
	private String apiPath = "";
	private String username = "";
	private String password = "";
	private ArrayList<Error> errorLog = new ArrayList<Error>();
	private boolean debug = false;
	//	private HttpURLConnection httpURLConnection;
	private int statusUpdateTimer;
	private int statusUpdateInterval = 1000;
	private String namespace = "XBMC";

	private JSONRPC jsonrpc = new JSONRPC(this);
	//	private System system = new System();
	//	private Status status = new Status();
	//	private Files files = new Files();
	private Player player = new Player(this);
	//	private Playlist playlist = new Playlist();
	private Library library = new Library(this);

	public XBMC(String address, String port, String username, String password, boolean debug, int updateInterval) {
		this.apiPath = "http://" +address+ ":" +port+ "/jsonrpc";
		this.username = username;
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
	
	public Library getLibrary() {
		return this.library;
	}
	public Player getPlayer() {
		return this.player;
	}
	
	public JSONRPC getJSONRPC() {
		return this.jsonrpc;
	}

	public String post(String namespace, String method, Object parameters, Integer id) {
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
				System.out.println("post"+Helper.getHelper().getJson(namespace, method, parameters, id));
				outputStreamWriter.write(Helper.getHelper().getJson(namespace, method, parameters, id));

				outputStreamWriter.flush();

				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

				String allLines = "";
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					allLines += line;
				}
				outputStreamWriter.close();
				bufferedReader.close();
				JSONObject response = new JSONObject(allLines);
				System.out.println("response"+response);
				if (response.has("error") || allLines.equals("")) {
					if (response.has("error")) {
						this.errorLog.add(new Error(response.getJSONObject("error").getString("code"), response.getJSONObject("error").getString("message")));
					}
					else {
						this.errorLog.add(new Error("1", "No response from server"));
					}
					
					return "false";
				}
				
				else {
					return response.getString("result");
				}
		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				this.errorLog.add(new Error("2", "Couldn't connect to server"));
			}
		}
		return "false";
	}

	public String post(String namespace, String method, Object parameters) {
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
	
	public String log(String message, String level) {
		JSONObject parameters = new JSONObject();
		try {
			parameters.append("message", message);
			parameters.append("level", level);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.post(this.namespace, "Log", parameters);
	}
	
	public int getVolume() {
		return Integer.valueOf(this.post(this.namespace, "GetVolume"));
	}

	public String setVolume(int volume) {
		return this.post(this.namespace, "SetVolume", volume);
	}

	public ArrayList<Error> getErrors() {
		return this.errorLog;
	}

	public Error getLastError() {
		return this.errorLog.get(errorLog.size()-1);
	}





}
