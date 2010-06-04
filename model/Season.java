package model;

public class Season {
	private String label;
	private String thumbnail;
	
	public Season(String label, String thumbnail) {
		super();
		this.label = label;
		this.thumbnail = thumbnail;
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
