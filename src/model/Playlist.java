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
	
	
}
