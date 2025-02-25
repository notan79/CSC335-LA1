package model;

public final class Song {

	private final String title;
	private Rating rating;
	private Album album;
	private boolean isFavorite = false;

	public Song(String title, Album album) {
		this.title = title;
		this.rating = Rating.NONE;
		this.album = new Album(album);
	}

	public Song(Song song) {
		this.title = song.title;
		this.rating = song.rating;
		this.isFavorite = song.isFavorite;
		this.album = new Album(song.album);
	}

	private Song(String title) {
		this.title = title;
	}

	public static Song fromTitle(String title) {
		return new Song(title);
	}

	public static Song fromAlbum(Album album) {
		return new Song(null, new Album(album));
	}

	// Getters
	public String getTitle() {
		return title;
	}

	public String getArtist() {
		return this.album.getArtist();
	}

	public Rating getRating() {
		return this.rating;
	}

	public Album getAlbum() {
		if (this.album == null)
			return null;
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
		if (rate == Rating.FIVE) {
			this.setFavorite();
		}
		this.rating = rate;
	}

	// Override methods
	@Override
	public boolean equals(Object o) {
		if (o == null || this.album == null)
			return false;
		if (o.getClass() != this.getClass())
			return false;

		Song temp = (Song) o;
		if (temp.album == null)
			return false;

		return this.getArtist().equals(temp.getArtist()) && this.title.equals(temp.title);
	}

	@Override
	public String toString() {
		return this.title + " by " + this.getArtist();
	}

}
