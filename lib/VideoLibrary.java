package lib;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import lib.Library.LibraryType;
import model.Episode;
import model.Movie;
import model.Season;
import model.TVShow;

public class VideoLibrary {
	private Library library;
	private LibraryType libraryType = LibraryType.VideoLibrary;

	public VideoLibrary(Library library) {
		this.library = library;
	}

	private int getMediaCount(String method) {
		try {
			JSONObject response = new JSONObject(library.getXbmc().post(libraryType.name(), method));
			return response.getInt("total");

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public ArrayList<Movie> getMovies(String fields, String sortmethod, String sortorder, Integer start, Integer end) {
		ArrayList<Movie> movies = new ArrayList<Movie>();
		JSONObject parameters = new JSONObject();
		try {
			if(fields != null) parameters.append("fields", fields);
			if(sortmethod != null) parameters.append("sortmethod", sortmethod);
			if(sortorder != null) parameters.append("sortorder", sortorder);
			if(start != null) parameters.append("start", start);
			if(end != null) parameters.append("end", end);
			JSONObject response = new JSONObject(library.getXbmc().post(libraryType.name(), "GetMovies", parameters));
			for (int i = 0; i < response.getJSONArray("movies").length(); i++) {				
				String file = response.getJSONArray("movies").getJSONObject(i).get("file").toString();
				String label = response.getJSONArray("movies").getJSONObject(i).get("label").toString();
				int id = response.getJSONArray("movies").getJSONObject(i).getInt("movieid");
				movies.add(new Movie(file, label, id));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}		
		return movies;
	}

	public ArrayList<Movie> getMovies() {
		return getMovies(null, null, null, null, null);
	}

	public int getMovieCount() {
		return getMediaCount("GetMovies");
	}

	public ArrayList<TVShow> getTVShows(String fields, String sortmethod, String sortorder, Integer start, Integer end) {
		ArrayList<TVShow> tvshows = new ArrayList<TVShow>();
		JSONObject parameters = new JSONObject();
		try {
			if(fields != null) parameters.append("fields", fields);
			if(sortmethod != null) parameters.append("sortmethod", sortmethod);
			if(sortorder != null) parameters.append("sortorder", sortorder);
			if(start != null) parameters.append("start", start);
			if(end != null) parameters.append("end", end);
			JSONObject response = new JSONObject(library.getXbmc().post(libraryType.name(), "GetTVShows", parameters));
			for (int i = 0; i < response.getJSONArray("tvshows").length(); i++) {				
				String file = response.getJSONArray("tvshows").getJSONObject(i).get("file").toString();
				String label = response.getJSONArray("tvshows").getJSONObject(i).get("label").toString();
				int id = response.getJSONArray("tvshows").getJSONObject(i).getInt("tvshowid");
				tvshows.add(new TVShow(file, label, id));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}		
		return tvshows;
	}

	public ArrayList<TVShow> getTVShows() {
		return getTVShows(null, null, null, null, null);
	}

	public int getTVShowCount() {
		return getMediaCount("GetTVShows");
	}

	public ArrayList<Season> getSeasons(int tvshowid, String fields, String sortmethod, String sortorder, Integer start, Integer end) {
		ArrayList<Season> seasons = new ArrayList<Season>();
		JSONObject parameters = new JSONObject();
		try {
			parameters.append("tvshowid", tvshowid);
			if(fields != null) parameters.append("fields", fields);
			if(sortmethod != null) parameters.append("sortmethod", sortmethod);
			if(sortorder != null) parameters.append("sortorder", sortorder);
			if(start != null) parameters.append("start", start);
			if(end != null) parameters.append("end", end);
			JSONObject response = new JSONObject(library.getXbmc().post(libraryType.name(), "GetSeasons", parameters));
			for (int i = 0; i < response.getJSONArray("seasons").length(); i++) {				
				String thumbnail = response.getJSONArray("seasons").getJSONObject(i).get("thumbnail").toString();
				String label = response.getJSONArray("seasons").getJSONObject(i).get("label").toString();
				seasons.add(new Season(label, thumbnail));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}		
		return seasons;
	}
	
	public ArrayList<Season> getSeasons(int tvshowid) {
		return getSeasons(tvshowid, null, null, null, null, null);
	}
	
	public int getSeasonCount(int tvshowid) {
		JSONObject parameters = new JSONObject();
		try {
			parameters.append("tvshowid", tvshowid);
			JSONObject response = new JSONObject(library.getXbmc().post(libraryType.name(), "GetSeasons", parameters));
			return response.getInt("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}		
		return -1;
	}

	public ArrayList<Episode> getEpisodes(int tvshowid, int season, String fields, String sortmethod, String sortorder, Integer start, Integer end) {
		ArrayList<Episode> episodes = new ArrayList<Episode>();
		JSONObject parameters = new JSONObject();
		try {
			parameters.append("tvshowid", tvshowid);
			parameters.append("season", season);
			if(fields != null) parameters.append("fields", fields);
			if(sortmethod != null) parameters.append("sortmethod", sortmethod);
			if(sortorder != null) parameters.append("sortorder", sortorder);
			if(start != null) parameters.append("start", start);
			if(end != null) parameters.append("end", end);
			JSONObject response = new JSONObject(library.getXbmc().post(libraryType.name(), "GetEpisodes", parameters));
			for (int i = 0; i < response.getJSONArray("episodes").length(); i++) {				
				String thumbnail = response.getJSONArray("episodes").getJSONObject(i).get("thumbnail").toString();
				String label = response.getJSONArray("episodes").getJSONObject(i).get("label").toString();
				String file = response.getJSONArray("episodes").getJSONObject(i).get("file").toString();
				int id = response.getJSONArray("episodes").getJSONObject(i).getInt("episodeid");
				episodes.add(new Episode(label, thumbnail, file, id));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}		
		return episodes;
	}

	public ArrayList<Episode> getEpisodes(int tvshowid, int season) {
		return getEpisodes(tvshowid, season, null, null, null, null, null);
	}
	public int getEpisodeCount(int tvshowid, int season) {
		JSONObject parameters = new JSONObject();
		try {
			parameters.append("tvshowid", tvshowid);
			parameters.append("season", season);
			JSONObject response = new JSONObject(library.getXbmc().post(libraryType.name(), "GetEpisodes", parameters));
			return response.getInt("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}		
		return -1;
	}

	public String getMusicVideoAlbums() {
		return library.getXbmc().post(libraryType.name(), "GetMusicVideoAlbums");
	}

	public int getMusicVideoAlbumCount() {
		return getMediaCount("GetEpisodes");
	}


}
