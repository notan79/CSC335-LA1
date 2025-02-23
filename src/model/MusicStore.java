package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


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
		String fname = "albums.txt";
		
		Scanner scanner = new Scanner(new File(fname));	

		while (scanner.hasNextLine()) {
			String tempString = scanner.nextLine();
			String[] line = tempString.split(",");
			String title = line[0]; String artist = line[1];
			temp.add(getFileNameFormat(title, artist));
		}
		scanner.close();
		return temp;
	}
	
	// TODO: Cameron Implement. For each album, add all the songs from its respective file. 
	private ArrayList<Album> parseAlbums(ArrayList<String> titles) {
		ArrayList<Album> temp = new ArrayList<Album>();
		
		return temp;
	}
	
	
	
	public String getFileNameFormat(String title, String artist) {
		// moved method from album, 
		return title + "_" + artist + ".txt"; 
	}
}
