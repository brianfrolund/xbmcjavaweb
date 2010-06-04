package model;

public class Episode {
	private String label;
	private String thumbnail;
	private int id;
	private String file;
	
	public Episode(String label, String thumbnail, String file, int id) {
		super();
		this.label = label;
		this.thumbnail = thumbnail;
		this.file = file; 
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public String getThumbnail() {
		return thumbnail;
	}
	
	public String toString() {
		return label;
	}

}
