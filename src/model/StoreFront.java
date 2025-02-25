package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

public class StoreFront {

	private ArrayList<Song> songList = new ArrayList<>();

	public void addSong(Song song) {
		if(!songList.contains(song))
			songList.add(new Song(song));
	}

	public ArrayList<Song> getSongList() {
		ArrayList<Song> temp = new ArrayList<>();
		for (Song song : this.songList) {
			temp.add(new Song(song));
		}
		return temp;
	}

	public ArrayList<String> getTitles() {
		ArrayList<String> temp = new ArrayList<>();
		for (Song song : this.songList) {
			temp.add(song.getTitle());
		}
		return temp;
	}

	public ArrayList<String> getArtists() {
		HashSet<String> temp = new HashSet<>();
		for (Song song : this.songList) {
			temp.add(song.getArtist());
		}
		return new ArrayList<>(temp);
	}

	public ArrayList<String> getAlbums() {
		HashSet<String> temp = new HashSet<>();
		for (Song song : this.songList) {
			temp.add(song.getAlbum().getAlbumName());
		}
		return new ArrayList<>(temp);
	}
	
	public void setRating(Song song, Rating rate) {
		for(Song s : this.songList) {
			if(song.equals(s)){
				if(rate == Rating.FIVE)
					s.setFavorite();
				s.setRating(rate);
			}
		}
	}
	
	public void setFavorite(Song song) {
		for(Song s : this.songList) {
			if(s.equals(song))
				s.setFavorite();
		}
	}
	
	public ArrayList<Song> findSongByTitle(String title) {
		return this.findSongsComparator(Song.fromTitle(title), new Compare.CompareSongByTitle());
	}

	public ArrayList<Song> findSongByArtist(String artist) {
		return this.findSongsComparator(Song.fromAlbum(Album.fromArtist(artist)), new Compare.CompareSongByArtist());
	}

	public ArrayList<Album> findAlbumByTitle(String title) {
		return this.findAlbumsComparator(Album.fromAlbumName(title), new Compare.CompareAlbumByTitle());
	}

	public ArrayList<Album> findAlbumByArtist(String artist) {
		return this.findAlbumsComparator(Album.fromArtist(artist), new Compare.CompareAlbumByArtist());
	}

	private ArrayList<Song> findSongsComparator(Song inp, Comparator<Song> c) {
		ArrayList<Song> temp = new ArrayList<>();
		for (Song song : songList) {
			if (c.compare(song, inp) == 0 && !temp.contains(song))
				temp.add(new Song(song));
		}
		return temp;
	}

	private ArrayList<Album> findAlbumsComparator(Album inp, Comparator<Album> c) {
		ArrayList<Album> temp = new ArrayList<>();
		for (Song song : songList) {
			if (c.compare(song.getAlbum(), inp) == 0 && !temp.contains(song.getAlbum()))
				temp.add(new Album(song.getAlbum()));
		}
		return temp;
	}
}
