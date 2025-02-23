package model;

import java.util.ArrayList;

public class MusicStore extends StoreFront{
	
	public MusicStore() {
		super();
		this.parseFiles();
	}
	
	// TODO: Cameron Implement
	private void parseFiles() {
		this.parseAlbums(this.parseMainFile());
	}
	
	// TODO: Cameron Implement. Extract all the albums from the albums.txt file.
	private ArrayList<Album> parseMainFile() {
		ArrayList<Album> temp = new ArrayList<>();
		String fname = "albums.txt";
		
		return temp;
	}
	
	// TODO: Cameron Implement. For each album, add all the songs from its respective file. 
	private void parseAlbums(ArrayList<Album> albums) {
		return;
	}
}
