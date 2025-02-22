package model;

public final class Song {
	
	private final String title;
	private final String artist; 
	private Rating rating;
	private Album album;
	private boolean isFavorite = false;

	public Song(String title, String artist) {
		this.title = title;
		this.artist = artist; 
		this.rating = Rating.NONE;
	}
	
	public Song(Song song) {
		this.title = song.title;
		this.artist = song.artist; 
		this.rating = song.rating;
		this.album = new Album(song.album);
	}
	
	public static Song fromTitle(String title) {
		return new Song(title, null);
	}
	
	public static Song fromArtist(String artist) {
		return new Song(null, artist);
	}
	
	// Getters
	public String getTitle() {
		return title;
	}

	public String getArtist() {
		return artist;
	}
	
	public Rating getRating() {
		return this.rating; 
	}
	
	public Album getAlbum() {
		return new Album(this.album);
	}
	
	public boolean isFavorite() {
		return this.isFavorite;
	}
	
	// Setters
	public void setFavorite() {
		this.isFavorite = true;
	}
	
	public void setRating(Rating rate) {
		this.rating = rate;
	}
	
	// toString
	@Override
	public String toString() {
		return this.title + " by " + this.artist;
	}
	
	
}
