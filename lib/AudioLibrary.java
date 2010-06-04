package lib;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import lib.Library.LibraryType;
import model.Movie;

public class AudioLibrary {
	private Library library;
	private LibraryType libraryType = LibraryType.AudioLibrary;
	
	public AudioLibrary(Library library) {
		this.library = library;
	}
	
	public ArrayList<Movie> getArtists() {
		ArrayList<Movie> artists = new ArrayList<Movie>();
		try {
			JSONObject response = new JSONObject(library.getXbmc().post(libraryType.name(), "GetArtists"));
			for (int i = 0; i < response.getJSONArray("artists").length(); i++) {				
				String file = response.getJSONArray("artists").getJSONObject(i).get("file").toString();
				String label = response.getJSONArray("artists").getJSONObject(i).get("label").toString();
				int id = response.getJSONArray("artists").getJSONObject(i).getInt("artistid");
				artists.add(new Movie(file, label, id));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return artists;
	}
	
	public int getArtistCount() {
		try {
			JSONObject response = new JSONObject(library.getXbmc().post(libraryType.name(), "GetArtists"));
			return response.getInt("total");
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public ArrayList<Movie> getAlbums() {
		ArrayList<Movie> albums = new ArrayList<Movie>();
		try {
			JSONObject response = new JSONObject(library.getXbmc().post(libraryType.name(), "GetAlbums"));
			for (int i = 0; i < response.getJSONArray("albums").length(); i++) {				
				String file = response.getJSONArray("albums").getJSONObject(i).get("file").toString();
				String label = response.getJSONArray("albums").getJSONObject(i).get("label").toString();
				int id = response.getJSONArray("albums").getJSONObject(i).getInt("albumid");
				albums.add(new Movie(file, label, id));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return albums;
	}
	
	public int getAlbumCount() {
		try {
			JSONObject response = new JSONObject(library.getXbmc().post(libraryType.name(), "GetAlbums"));
			return response.getInt("total");
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public ArrayList<Movie> getSongs() {
		ArrayList<Movie> songs = new ArrayList<Movie>();
		try {
			JSONObject response = new JSONObject(library.getXbmc().post(libraryType.name(), "GetSongs"));
			for (int i = 0; i < response.getJSONArray("songs").length(); i++) {				
				String file = response.getJSONArray("songs").getJSONObject(i).get("file").toString();
				String label = response.getJSONArray("songs").getJSONObject(i).get("label").toString();
				int id = response.getJSONArray("songs").getJSONObject(i).getInt("songid");
				songs.add(new Movie(file, label, id));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return songs;
	}
	
	public int getSongCount() {
		try {
			JSONObject response = new JSONObject(library.getXbmc().post(libraryType.name(), "GetSongs"));
			return response.getInt("total");
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public boolean scanForContent() {
		return library.scanForContent(libraryType);
	}
}
