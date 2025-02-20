package model;

public final class Song {
	
	private final String name;
	private final String artist; 
	private Rating rating;

	public Song(String name, String artist) {
		this.name = name;
		this.artist = artist; 
		this.rating = Rating.NONE;
	}
	
	public Song(Song song) {
		this.name = song.name;
		this.artist = song.artist; 
	}
	
	// Getters
	public String getName() {
		return name;
	}

	public String getArtist() {
		return artist;
	}
	
	public Rating getRating() {
		return this.rating; 
	}
	
	// toString
	@Override
	public String toString() {
		return this.name + "_" + this.artist;
	}
	
	
}
