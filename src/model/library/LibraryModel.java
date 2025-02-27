package model.library;

import java.util.ArrayList;
import java.util.Collections;

import model.Playlist;
import model.Rating;
import model.Song;

public class LibraryModel extends StoreFront {

	private ArrayList<Playlist> allPlaylists = new ArrayList<Playlist>();
	
	public LibraryModel() {
		super();
	}

	public void setRating(Song song, Rating rate) {
		for(Song s : this.songList) {
			if(song.equals(s)){
				if(rate == Rating.FIVE)
					s.setFavorite();
				s.setRating(rate);
			}
		}
	}
	
	public void setFavorite(Song song) {
		for(Song s : this.songList) {
			if(s.equals(song))
				s.setFavorite();
		}
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
	public boolean createPlaylist(String playlistName) {
		for(Playlist playlist : this.allPlaylists) {
			if(playlist.getName().equals(playlistName))
				return false;
		}
		Playlist temp = new Playlist(playlistName);
		allPlaylists.add(temp);
		return true;
	}

	// TODO: Cameron implement this
	public boolean addSongToPlaylist(String playlistName, String title, String artist) {
		ArrayList<Song> songs = this.songList;
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
		ArrayList<Song> songs = this.songList;
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
		ArrayList<Song> arr = this.songList;
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
