package lib;

public class Library {
	private XBMC xbmc;
	private VideoLibrary videoLibrary = new VideoLibrary(this);
	private AudioLibrary audioLibrary = new AudioLibrary(this);
	public enum LibraryType { AudioLibrary, VideoLibrary };	
	
	public Library(XBMC xbmc) {
		this.xbmc = xbmc;
	}
		
	public XBMC getXbmc() {
		return xbmc;
	}

	public VideoLibrary getVideoLibrary() {
		return videoLibrary;
	}

	public AudioLibrary getAudioLibrary() {
		return audioLibrary;
	}

	public boolean scanForContent(LibraryType libraryType) {
		return Helper.getHelper().getBoolResponse(xbmc.post(libraryType.name(), "ScanForContent"));
	}
}
