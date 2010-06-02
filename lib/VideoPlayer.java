package lib;

import org.json.JSONObject;

import lib.Player.PlayerType;

public class VideoPlayer {
	private Player player;
	private PlayerType playerType = PlayerType.VideoPlayer;
	
	public VideoPlayer(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public PlayerType getPlayerType() {
		return playerType;
	}
	
	public boolean playPause() {
		return player.playPause(playerType);
	}
	
	public boolean stop() {
		return player.stop(playerType);
	}
	
	public boolean skipPrevious() {
		return player.skipPrevious(playerType);
	}
	
	public boolean skipNext() {
		return player.skipNext(playerType);
	}
	
	public boolean bigSkipBackward() {
		return player.bigSkipBackward(playerType);
	}
	
	public boolean bigSkipForward() {
		return player.bigSkipForward(playerType);
	}
	
	public boolean smallSkipBackWard() {
		return player.smallSkipBackward(playerType);
	}
	
	public boolean smallSkipForward() {
		return player.smallSkipBackward(playerType);
	}
	
	public boolean rewind() {
		return player.rewind(playerType);
	}
	
	public boolean forward() {
		return player.forward(playerType);
	}
	
	public JSONObject getTime() {
		return player.getTime(playerType);
	}
	
	public JSONObject getTimeMS() {
		return player.getTimeMS(playerType);
	}
	
	public int getPercentage() {
		return player.getPercentage(playerType);
	}
	
	public boolean seekTime(int seconds) {
		return player.seekTime(playerType, seconds);
	}
	
	public boolean seekPercentage(int percentage) {
		return player.seekPercentage(playerType, percentage);
	}
}
