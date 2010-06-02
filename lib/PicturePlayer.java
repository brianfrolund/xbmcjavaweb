package lib;

import lib.Player.PlayerType;

public class PicturePlayer {
	private Player player;
	private PlayerType playerType = PlayerType.PicturePlayer;
	private int minZoom = 1;
	private int maxZoom = 9;
	
	public PicturePlayer(Player player) {
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}

	public PlayerType getPlayerType() {
		return playerType;
	}

	public int getMinZoom() {
		return minZoom;
	}

	public void setMinZoom(int minZoom) {
		this.minZoom = minZoom;
	}

	public int getMaxZoom() {
		return maxZoom;
	}

	public void setMaxZoom(int maxZoom) {
		this.maxZoom = maxZoom;
	}

	public boolean playPause() {
		return player.playPause(PlayerType.PicturePlayer);
	}
	
	public boolean stop() {
		return player.stop(PlayerType.PicturePlayer);
	}
	
	public boolean skipPrevious() {
		return player.skipPrevious(PlayerType.PicturePlayer);
	}
	
	public boolean skipNext() {
		return player.skipNext(PlayerType.PicturePlayer);
	}
	
	public boolean moveLeft() {
		if(!player.isPictureShowing()) {
			return false;
		}
		return Helper.getHelper().getBoolResponse(player.getXbmc().post(playerType.name(), "MoveLeft"));		
	}
	
	public boolean moveRight() {
		if(!player.isPictureShowing()) {
			return false;
		}
		return Helper.getHelper().getBoolResponse(player.getXbmc().post(playerType.name(), "MoveRight"));		
	}
	
	public boolean moveDown() {
		if(!player.isPictureShowing()) {
			return false;
		}
		return Helper.getHelper().getBoolResponse(player.getXbmc().post(playerType.name(), "MoveDown"));		
	}
	
	public boolean moveUp() {
		if(!player.isPictureShowing()) {
			return false;
		}
		return Helper.getHelper().getBoolResponse(player.getXbmc().post(playerType.name(), "MoveUp"));		
	}
	
	public boolean zoomOut() {
		if(!player.isPictureShowing()) {
			return false;
		}
		return Helper.getHelper().getBoolResponse(player.getXbmc().post(playerType.name(), "ZoomOut"));		
	}
	
	public boolean zoomIn() {
		if(!player.isPictureShowing()) {
			return false;
		}
		return Helper.getHelper().getBoolResponse(player.getXbmc().post(playerType.name(), "ZoomIn"));		
	}
	
	public boolean zoom(int level) {
		if(level > maxZoom || level < minZoom || !player.isPictureShowing()) {
			return false;
		}
		return Helper.getHelper().getBoolResponse(player.getXbmc().post(playerType.name(), "Zoom", level));		
	}
	
	public boolean rotate() {
		if(!player.isPictureShowing()) {
			return false;
		}
		return Helper.getHelper().getBoolResponse(player.getXbmc().post(playerType.name(), "Rotate"));		
	}
}
