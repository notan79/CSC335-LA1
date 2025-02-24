package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.Album;
import model.LibraryModel;
import model.MusicStore;
import model.Song;
import model.StoreFront;

public class UI {
	private static Scanner scanner;
	private static MusicStore ms;
	private static LibraryModel lib;


	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		
		ms = new MusicStore();

//		ArrayList<String> albums = mStore.getAlbums();
//		ArrayList<String> artists = mStore.getArtists();
//		ArrayList<String> songTitles = mStore.getTitles();

		mainPrompt();
		
		scanner.close();
	}
	
	private static void mainPrompt() {
		System.out.print("Choose an action (1-3):\n"
				+ "1. Search Music Store.\n"
				+ "2. Access User Library.\n"
				+ "3. Exit.\n\n"
				+ "Enter action: ");
		
		String inpString = runScannerOptions(3);
		
		if(inpString.equals("1")) 
			searchPrompt(ms);
		else if(inpString.equals("2")) 
			libraryPrompt();
		else
			System.out.println("\nEnding program.");
	}
	
	private static void searchPrompt(StoreFront s) {
		
		// Same search actions in both user library and music store.
		String tempString = "user library";
		if(s.getClass() == MusicStore.class) tempString = "music store";
		
		System.out.printf("\nYou are searching in %s. Choose an action (1-5):\n"
			+ "1. Search for song by title.\n"
			+ "2. Search for song by artist.\n"
			+ "3. Search for album by title.\n"
			+ "4. Search for album by artist.\n"
			+ "5. Exit search.\n\n"
			+ "Enter action: ", tempString);
		String inpString = runScannerOptions(5);
		
		if(inpString.equals("1")) {
			System.out.print("Enter song title: ");
			inpString = scanner.nextLine().strip();
			ArrayList<Song> tempArrayList = s.findSongByTitle(inpString);
			if(tempArrayList.size() > 0)
				System.out.printf("\nFound these songs with title: %s\n", inpString);
			else 
				System.out.printf("\nFound no songs with title: %s\n", inpString);
			
			for(Song song : tempArrayList) 
				System.out.println("- " + song);
		}
		else if(inpString.equals("2")) {
			System.out.print("Enter artist: ");
			inpString = scanner.nextLine().strip();
			ArrayList<Song> tempArrayList = s.findSongByArtist(inpString);
			if(tempArrayList.size() > 0)
				System.out.printf("\nFound these songs with artist: %s\n", inpString);
			else 
				System.out.printf("\nFound no songs with artist: %s\n", inpString);
			
			for(Song song : tempArrayList) 
				System.out.println("- " + song);
		}
		else if(inpString.equals("3")) {
			System.out.print("Enter album title: ");
			inpString = scanner.nextLine().strip();
			ArrayList<Album> tempArrayList = s.findAlbumByTitle(inpString);
			if(tempArrayList.size() > 0)
				System.out.printf("\nFound these albums with title: %s\n", inpString);
			else 
				System.out.printf("\nFound no albums with title: %s\n", inpString);
			
			for(Album album : tempArrayList) 
				System.out.println("- " + album);
		}
		else if(inpString.equals("4")) {
			System.out.print("Enter artist: ");
			inpString = scanner.nextLine().strip();
			ArrayList<Album> tempArrayList = s.findAlbumByArtist(inpString);
			if(tempArrayList.size() > 0)
				System.out.printf("\nFound these albums with artist: %s\n", inpString);
			else 
				System.out.printf("\nFound no albums with artist: %s\n", inpString);
			
			for(Album album : tempArrayList) 
				System.out.println("- " + album);
		}
		
		// Exit the music store 
		else {
			System.out.printf("\nLeaving %s.\n\n", tempString);
			if(s.getClass() == MusicStore.class)
				mainPrompt();
			else 
				libraryPrompt();
			return; 
		}
		
		// Stay in the search mode
		searchPrompt(s);
	}
	
	// TODO: Nate implement
	private static void libraryPrompt() {
		System.out.printf("\nYou are in user library. Choose an action (1-5):\n"
				+ "1. Search for songs or albums.\n"
				+ "2. Add song or album to library.\n"
				+ "3. Rate a song.\n"
				+ "4. Favorite a song.\n"
				+ "5. Get information for library.\n"
				+ "6. Create playlist.\n"
				+ "7. Add or remove song from playlist.\n"
				+ "8. Exit User Library.\n\n"
				+ "Enter action: ");
		String inpString = runScannerOptions(8);
		if(inpString.equals("1")) {
			searchPrompt(lib);
		} 
		// If input is 8, exit library
		else {
			System.out.printf("\nLeaving User Library.\n\n");
			mainPrompt();
			return;
		}
		libraryPrompt();
	}
	
	private static boolean promptValidity(String s, int options) {
		try{
			String.valueOf(s);
		}catch(NumberFormatException e){
			return false;
		}
		
		for(int i = 1; i < options+1; ++i) {
			if(s.equals(String.valueOf(i))) {
				return true;
			}
		}
		return false; 
	}
	
	private static String runScannerOptions(int options) {
		String inpString = "";
		while(scanner.hasNextLine()) {
			inpString = scanner.nextLine().strip();
			if(promptValidity(inpString, options)) break;
			else System.out.printf("Invalid action: %s. Enter valid action: ", inpString);
		}
		return inpString;
	}
}
