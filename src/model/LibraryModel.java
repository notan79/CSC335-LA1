package model;

import java.util.ArrayList;
import java.util.Collections;

public class LibraryModel extends StoreFront {

	private ArrayList<Playlist> allPlaylists = new ArrayList<Playlist>();
	
	public LibraryModel() {
		super();
	}

	public void setSongToFavorite(Song song) {
		super.setFavorite(song);
	}

	public void rateSong(Song song, Rating rate) {
		super.setRating(song, rate);
	}

	// TODO: Cameron, implement this after Playlist class is made. Return a string
	// of playlist names.
	public ArrayList<String> getPlaylists() {
		ArrayList<String> temp = new ArrayList<>();
		for(Playlist playlist : this.allPlaylists)
			temp.add(playlist.toString());
		
		Collections.sort(temp);
		return temp;
	}

	// TODO: Cameron implement this.
	public ArrayList<String> getSongsFromPlaylist(String playlistName) {
		ArrayList<String> temp = new ArrayList<>();
		Playlist tempPlaylist = null;
		ArrayList<Song> songs;
		
		for (int i = 0; i < allPlaylists.size(); i++) {
			if (allPlaylists.get(i).getName().equals(playlistName)) {
				tempPlaylist = allPlaylists.get(i);
			}
		}
		
		if (tempPlaylist == null) {
			return new ArrayList<String>();
		}
	
		songs = tempPlaylist.getPlaylist();
		
		for (int i = 0; i < songs.size(); i++) {
			temp.add(songs.get(i).toString());
		}
	
		return temp;
	}

	// TODO: Cameron implement this
	public void createPlaylist(String playlistName) {
		Playlist temp = new Playlist(playlistName);
		allPlaylists.add(temp);
	}

	// TODO: Cameron implement this
	public boolean addSongToPlaylist(String playlistName, String title, String artist) {
		ArrayList<Song> songs = super.getSongList();
		Playlist tempPlaylist = null;
		boolean flag = false;
		
		// gets the proper playlist
		for (int j = 0; j < allPlaylists.size(); j++) {
			if (allPlaylists.get(j).getName().equals(playlistName)) {
				tempPlaylist = allPlaylists.get(j);
			}
		}
		if (tempPlaylist == null) {
			return false;
		}
		
		// adds the proper playlist
		for (int i = 0; i < songs.size(); i++) {
			if (songs.get(i).getTitle().equals(title) && songs.get(i).getArtist().equals(artist)) {
				tempPlaylist.addSong(new Song(songs.get(i)));
				flag = true;
			}
		}	
		return flag;
	}

	// TODO: Cameron implement this
	public boolean removeSongFromPlaylist(String playlistName, String title, String artist) {
		ArrayList<Song> songs = super.getSongList();
		Playlist tempPlaylist = null;
		
		// gets the proper playlist
		for (int j = 0; j < allPlaylists.size(); j++) {
			if (allPlaylists.get(j).getName().equals(playlistName)) {
				tempPlaylist = allPlaylists.get(j);
			}
		}
		if (tempPlaylist == null) {
			return false;
		}
		
		// removes the song from the playlist
		for (int i = 0; i < songs.size(); i++) {
			if (songs.get(i).getTitle().equals(title) && songs.get(i).getArtist().equals(artist)) {
				tempPlaylist.removeSong(new Song(songs.get(i)));
			}
		}	
		return true;
	}

	public ArrayList<String> getFavorites() {
		ArrayList<String> temp = new ArrayList<>();
		ArrayList<Song> arr = super.getSongList();
		for (Song song : arr) {
			if (song.isFavorite()) {
				temp.add(song.toString());
			}
		}

		// Alphabetical
		Collections.sort(temp);
		return temp;
	}
}
