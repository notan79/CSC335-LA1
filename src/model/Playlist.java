package model;

import java.util.ArrayList;

// TODO: Cameron, implement this class. Needs a name for the playlist and a list of songs.
// Client needs to be able to see all songs in a playlist, add, remove. Need to maintain the
// order they were added in. 
public class Playlist {
	
	private final String name;
	private ArrayList<Song> playlistSongs = new ArrayList<>();
	
	public Playlist(String name) {
		this.name = name;
	}
	
	
	
	
	public void addSong(Song song) { 
		Song tempSong = new Song(song);
		playlistSongs.add(tempSong);
	}
	
	public void removeSong(Song song) {
		for (int i = 0; i < playlistSongs.size(); i++) {
			if (playlistSongs.get(i).equals(song)) {
				// if song in the constructor has the same title and artist, remove it
				playlistSongs.remove(i);
			}
		}
	}
	
	
	// getters
		public String getName() { 
			return this.name;
		}
		
		public ArrayList<Song> getPlaylist() { 
			ArrayList<Song> tempPlaylist = new ArrayList<Song>();
			for (int i = 0; i < playlistSongs.size(); i++) {
				// iterates through original playlist, creating copy of each song and adding it to a copy of new arraylist.
				Song tempSong = new Song(playlistSongs.get(i));
				tempPlaylist.add(tempSong);
			}		
			return tempPlaylist;
		}
}
