package lib;

public class Error {
	private String code;
	private String message;
	
	public Error(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public String toString() {
		return code+" - "+message;
	}
}
