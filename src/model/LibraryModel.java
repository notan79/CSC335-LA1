package model;

import java.util.ArrayList;
import java.util.HashSet; 
import java.util.Comparator;
import java.util.Collections;

public class LibraryModel {
	
	private ArrayList<Song> songList = new ArrayList<>();
	private ArrayList<Playlist> playlists = new ArrayList<>();
	
	public void setSongToFavorite(String title) {
		for(Song song : this.songList) {
			if(song.getTitle().equals(title)) song.setFavorite();
		}
	}
	
	public void rateSong(String title, Rating rate) {
		for(Song song : this.songList) {
			if(song.getTitle().equals(title)) {
				song.setRating(rate);
				
				if(rate == Rating.FIVE)
					song.setFavorite();
			}
		}
	}
	
	public ArrayList<String> getTitles(){
		ArrayList<String> temp = new ArrayList<>();
		for(Song song : this.songList) {
			temp.add(song.getTitle());
		}
		
		// Alphabetical
		Collections.sort(temp);
		return temp;
	}
	
	public ArrayList<String> getArtists(){
		HashSet<String> temp = new HashSet<>();
		for(Song song : this.songList) {
			temp.add(song.getArtist());
		}
		
		// Alphabetical
		ArrayList<String> arr = new ArrayList<>(temp);
		Collections.sort(arr);
		return arr;
	}
	
	public ArrayList<String> getAlbums(){
		HashSet<String> temp = new HashSet<>();
		for(Song song : this.songList) {
			temp.add(song.getAlbum().getAlbumName());
		}
		
		// Alphabetical
		ArrayList<String> arr = new ArrayList<>(temp);
		Collections.sort(arr);
		return arr;
	}
	
	// TODO: Waiting for playlist implementation
	public ArrayList<String> getPlaylists(){
		ArrayList<String> temp = new ArrayList<>();
//		for(Song song : this.songList) {
//			if(!temp.contains(song.getAlbum().getAlbumName())) {
//				temp.add(song.getAlbum().getAlbumName());
//			}
//		}
//		
		return temp;
	}
	
	public ArrayList<String> getFavorites(){
		ArrayList<String> temp = new ArrayList<>();
		for(Song song : this.songList) {
			if(song.isFavorite()) {
				temp.add(song.getAlbum().getAlbumName());
			}
		}
		
		// Alphabetical
		Collections.sort(temp);
		return temp;
	}
	
	public ArrayList<Song> findSongByTitle(String title) {
		return this.findSongsComparator(Song.fromTitle(title), new Compare.CompareSongByTitle());
	}
	
	public ArrayList<Song> findSongByArtist(String artist){
		return this.findSongsComparator(Song.fromArtist(artist), new Compare.CompareSongByArtist());
	}
	
	public ArrayList<Album> findAlbumByTitle(String title){
		return this.findAlbumsComparator(Album.fromAlbumName(title), new Compare.CompareAlbumByTitle());
	}
	
	
	public ArrayList<Album> findAlbumByArtist(String artist) {
		return this.findAlbumsComparator(Album.fromArtist(artist), new Compare.CompareAlbumByArtist());
	}
	
	private ArrayList<Song> findSongsComparator(Song inp, Comparator<Song> c){
		ArrayList<Song> temp = new ArrayList<>();
		for(Song song : songList) {
			if(c.compare(song, inp) == 0) temp.add(new Song(song));
		}
		return temp;
	}
	
	private ArrayList<Album> findAlbumsComparator(Album inp, Comparator<Album> c){
		ArrayList<Album> temp = new ArrayList<>();
		for(Song song : songList) {
			if(c.compare(song.getAlbum(), inp) == 0) temp.add(new Album(song.getAlbum()));
		}
		return temp;
	}
}
