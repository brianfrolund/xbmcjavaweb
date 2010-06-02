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
			return new JSONObject(xbmc.post(namespace, "GetActivePlayers"));
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

	public boolean stop(PlayerType playerType) {
		return Helper.getHelper().getBoolResponse(xbmc.post(playerType.name(), "Stop"));
	}

	public boolean skipPrevious(PlayerType playerType) {
		return Helper.getHelper().getBoolResponse(xbmc.post(playerType.name(), "SkipPrevious"));
	}

	public boolean skipNext(PlayerType playerType) {
		return Helper.getHelper().getBoolResponse( xbmc.post(playerType.name(), "SkipNext"));
	}

	public boolean bigSkipBackward(PlayerType playerType) {
		return Helper.getHelper().getBoolResponse(xbmc.post(playerType.name(), "BigSkipBackward"));
	}

	public boolean bigSkipForward(PlayerType playerType) {
		return Helper.getHelper().getBoolResponse(xbmc.post(playerType.name(), "BigSkipForward"));
	}

	public boolean smallSkipBackward(PlayerType playerType) {
		return Helper.getHelper().getBoolResponse(xbmc.post(playerType.name(), "SmallSkipBackward"));
	}

	public boolean smallSkipForward(PlayerType playerType) {
		return Helper.getHelper().getBoolResponse(xbmc.post(playerType.name(), "SmallSkipForward"));
	}

	public boolean rewind(PlayerType playerType) {
		return Helper.getHelper().getBoolResponse(xbmc.post(playerType.name(), "Rewind"));
	}

	public boolean forward(PlayerType playerType) {
		return Helper.getHelper().getBoolResponse(xbmc.post(playerType.name(), "Forward"));
	}

	public JSONObject getTime(PlayerType playerType) {
		try {
			return new JSONObject(xbmc.post(playerType.name(), "GetTime"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public JSONObject getTimeMS(PlayerType playerType) {
		try {
			return new JSONObject(xbmc.post(playerType.name(), "GetTimeMS"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int getPercentage(PlayerType playerType) {
		return Integer.valueOf(xbmc.post(playerType.name(), "GetPercentage"));
	}

	public boolean seekTime(PlayerType playerType, int seconds) {
		return Helper.getHelper().getBoolResponse(xbmc.post(playerType.name(), "SeekTime", seconds));
	}

	public boolean seekPercentage(PlayerType playerType, int percentage) {
		if(percentage >= 0 && percentage <= 100) {
			return Helper.getHelper().getBoolResponse(xbmc.post(playerType.name(), "SeekPercentage", percentage));
		}
		else {
			return false;
		}
	}
	public VideoPlayer getVideoPlayer() {
		return videoPlayer;
	}

	public AudioPlayer getAudioPlayer() {
		return audioPlayer;
	}

	public PicturePlayer getPicturePlayer() {
		return picturePlayer;
	}

	public boolean isPictureShowing() {
		// TODO Auto-generated method stub
		return false;
	}

	public static boolean isAudioPlaying() {
		// TODO Auto-generated method stub
		return false;
	}


}
