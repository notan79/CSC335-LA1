package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.Year;

public class MusicStore extends StoreFront {

	public MusicStore() {
		super();
		this.parseFiles();
	}

	// TODO: Cameron Implement
	private void parseFiles() {
		try {
			this.parseAlbums(this.parseMainFile());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("File not found");
			System.exit(1);
		}
	}

	// TODO: Cameron Implement. Extract all the albums from the albums.txt file.
	private ArrayList<String> parseMainFile() throws FileNotFoundException {
		ArrayList<String> temp = new ArrayList<>();
		String fname = "albums/albums.txt";

		Scanner scanner = new Scanner(new File(fname));

		while (scanner.hasNextLine()) {
			String tempString = scanner.nextLine();
			String[] line = tempString.split(",");
			String title = line[0];
			String artist = line[1];
			temp.add(getFileNameFormat(title, artist));
		}
		scanner.close();
		return temp;
	}

	// TODO: Cameron Implement. For each album, add all the songs from its
	// respective file.
	private ArrayList<Album> parseAlbums(ArrayList<String> titles) throws FileNotFoundException {
		ArrayList<Album> temp = new ArrayList<Album>();
		ArrayList<Song> songs = new ArrayList<>();

		for (int i = 0; i < titles.size(); i++) {
			Scanner scanner = new Scanner(new File(titles.get(i)));
			boolean flag = true;

			while (scanner.hasNext()) {
				String tempString = scanner.nextLine();
				if (flag) {
					String[] line = tempString.split(",");
					String albumName = line[0];
					String artist = line[1];
					String genre = line[2];
					String year = line[3];
					temp.add(new Album(albumName, artist, genre, Year.parse(year)));
					flag = false;
				} else {
					Song s = new Song(tempString, temp.get(temp.size() - 1));
					songs.add(s);
					// is this gigachad way? ↓↓↓
					temp.get(temp.size() - 1).addSong(s);
					// this gets the Album that was created in the if statement
				}
			}

			// Each song needs the FINISHED album before being added to instance variables
			for (Song song : songs) {
				int index = temp.indexOf(song.getAlbum());
				super.addSong(new Song(song.getTitle(), temp.get(index)));
			}

		}
		return temp;
	}

	public String getFileNameFormat(String title, String artist) {
		// moved method from album,
		return "albums/" + title + "_" + artist + ".txt";
	}
}
