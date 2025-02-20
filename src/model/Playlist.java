package model;

import java.util.ArrayList;

public class Playlist {
	
	private final String name;
	ArrayList<Song> playlistSongs = new ArrayList<>();
	
	public Playlist(String name) {
		this.name = name;
	}
	
	
}
