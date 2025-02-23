package model;

import java.util.ArrayList;
import java.util.Collections;

public class LibraryModel extends StoreFront{
		
	public LibraryModel() {
		super();
	}
	
	public void setSongToFavorite(String title) {
		ArrayList<Song> arr = super.getSongList();
		for(Song song : arr) {
			if(song.getTitle().equals(title)) song.setFavorite();
		}
	}
	
	public void rateSong(String title, Rating rate) {
		ArrayList<Song> arr = super.getSongList();
		for(Song song : arr) {
			if(song.getTitle().equals(title)) {
				song.setRating(rate);
			}
		}
	}
	
	// TODO: Cameron, implement this after Playlist class is made. Return a string of playlist names.
	public ArrayList<String> getPlaylists(){
		ArrayList<String> temp = new ArrayList<>();
	
		Collections.sort(temp);
		return temp;
	}
	
	// TODO: Cameron implement this.
	public ArrayList<String> getSongsFromPlaylist(String playlistName){
		ArrayList<String> temp = new ArrayList<>();
		return temp;
	}
	
	public ArrayList<String> getFavorites(){
		ArrayList<String> temp = new ArrayList<>();
		ArrayList<Song> arr = super.getSongList();
		
		for(Song song : arr) {
			if(song.isFavorite()) {
				temp.add(song.getAlbum().getAlbumName());
			}
		}
		
		// Alphabetical
		Collections.sort(temp);
		return temp;
	}
}
