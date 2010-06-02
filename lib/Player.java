package lib;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

public class Player {
	
	
	private VideoPlayer videoPlayer = new VideoPlayer(this);
	private AudioPlayer audioPlayer = new AudioPlayer(this);
	private PicturePlayer picturePlayer = new PicturePlayer(this);
	private XBMC xbmc;
	private String namespace = "Player";
	public enum PlayerType { AudioPlayer, VideoPlayer, PicturePlayer };
	
	public Player(XBMC xbmc) {
		this.xbmc = xbmc;
	}
	
	public XBMC getXbmc() {
		return xbmc;
	}

	public JSONObject getActivePlayers() {
		try {
			return new JSONObject(xbmc.post("Player", "GetActivePlayers"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param mediaPlayer can be AudioPlayer, PicturePlayer or VideoPlayer.
	 * @return true if playing false if paused.
	 */
	public boolean playPause(PlayerType mediaPlayer) {
		JSONObject response;
		try {
			response = new JSONObject(xbmc.post(mediaPlayer.name(), "PlayPause"));
			return !response.getBoolean("paused");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;		
	}
	
	public boolean stop(PlayerType mediaPlayer) {
		return Helper.getHelper().getBoolResponse(xbmc.post(mediaPlayer.name(), "Stop"));
	}
	
	public boolean skipPrevious(PlayerType mediaPlayer) {
		return Helper.getHelper().getBoolResponse(xbmc.post(mediaPlayer.name(), "SkipPrevious"));
	}
	
	public boolean skipNext(PlayerType mediaPlayer) {
		return Helper.getHelper().getBoolResponse( xbmc.post(mediaPlayer.name(), "SkipNext"));
	}
	
	public boolean bigSkipBackward(PlayerType mediaPlayer) {
		return Helper.getHelper().getBoolResponse(xbmc.post(mediaPlayer.name(), "BigSkipBackward"));
	}
	
	public boolean bigSkipForward(PlayerType mediaPlayer) {
		return Helper.getHelper().getBoolResponse(xbmc.post(mediaPlayer.name(), "BigSkipForward"));
	}
	
	public boolean smallSkipBackward(PlayerType mediaPlayer) {
		return Helper.getHelper().getBoolResponse(xbmc.post(mediaPlayer.name(), "SmallSkipBackward"));
	}
	
	public boolean smallSkipForward(PlayerType mediaPlayer) {
		return Helper.getHelper().getBoolResponse(xbmc.post(mediaPlayer.name(), "SmallSkipForward"));
	}
	
	public boolean rewind(PlayerType mediaPlayer) {
		return Helper.getHelper().getBoolResponse(xbmc.post(mediaPlayer.name(), "Rewind"));
	}
	
	public boolean forward(PlayerType mediaPlayer) {
		return Helper.getHelper().getBoolResponse(xbmc.post(mediaPlayer.name(), "Forward"));
	}

	public boolean isPictureShowing() {
		// TODO Auto-generated method stub
		return false;
	}

}
