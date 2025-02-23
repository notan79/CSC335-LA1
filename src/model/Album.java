package model;

import java.time.Year;
import java.util.ArrayList;


public class Album {

	private final String albumName;
	private final String artist;
	private final String genre; 
	private final Year year;
	private final ArrayList<Song> songs = new ArrayList<>();
	
	public Album(String albumName, String artist, String genre, Year year) {
		this.albumName = albumName;
		this.artist = artist; 
		this.genre = genre;
		this.year = year;
	}
	
	public Album(Album album) {
		this.albumName = album.albumName;
		this.artist = album.artist; 
		this.genre = album.genre;
		this.year = album.year;
		
		for(Song s : album.songs) {
			this.songs.add(new Song(s));
		}
	}
	
	public static Album fromAlbumName(String albumName) {
		return new Album(albumName, null, null, null);
	}
	
	public static Album fromArtist(String artist) {
		return new Album(null, artist, null, null);
	}
	
	public void addSong(Song song) {
		this.songs.add(new Song(song));
	}
	
	// Getters
	public String getAlbumName() {
		return this.albumName;
	}
	
	public String getArtist() {
		return this.artist;
	}
	
	public String getGenre() {
		return this.genre;
	}
	
	public Year getYear() {
		return this.year;
	}
	
	
	public ArrayList<Song> getSongs(){
		ArrayList<Song> temp = new ArrayList<>();
		for(Song s : this.songs) {
			temp.add(new Song(s));
		}
		return temp;
	}
	
	// Override methods
	@Override
	public boolean equals(Object o) {
		if(o == null) return false;
		if(o.getClass() != this.getClass()) return false;
		
		Album temp = (Album) o;
		return this.artist.equals(temp.artist) && this.albumName.equals(temp.albumName) && this.year.equals(temp.year);
	}
	@Override
	public String toString() {
		return this.albumName + "," + this.artist + "," + this.genre + "," + this.year;
	}
}
