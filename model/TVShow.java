package model;

public class TVShow {
	private String file;
	private String label;
	private int id;

	public TVShow(String file, String label, int id) {
		super();
		this.file = file;
		this.label = label;
		this.id = id;
	}

	public String getFile() {
		return file;
	}

	public String getLabel() {
		return label;
	}

	public int getId() {
		return id;
	}
	
	public String toString() {
		return label;
	}
}
