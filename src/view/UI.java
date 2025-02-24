package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.Album;
import model.LibraryModel;
import model.MusicStore;
import model.Rating;
import model.Song;
import model.StoreFront;

public class UI {
	private static Scanner scanner;
	private static MusicStore ms;
	private static LibraryModel lib;

	public static void run() {
		scanner = new Scanner(System.in);

		ms = new MusicStore();
		lib = new LibraryModel();
		
		mainPrompt();
		scanner.close();
	}

	private static void mainPrompt() {
		System.out.print("Choose an action (1-3):\n" + "1. Search Music Store.\n" + "2. Access User Library.\n"
				+ "3. Exit.\n\n" + "Enter action: ");

		String inpString = runScannerOptions(3);

		if (inpString.equals("1"))
			searchPrompt(ms);
		else if (inpString.equals("2"))
			libraryPrompt();
		else
			System.out.println("\nEnding program.");
	}

	private static void searchPrompt(StoreFront s) {

		// Same search actions in both user library and music store.
		String tempString = "user library";
		if (s.getClass() == MusicStore.class)
			tempString = "music store";

		System.out.printf("\nYou are searching in %s. Choose an action (1-5):\n" + "1. Search for song by title.\n"
				+ "2. Search for song by artist.\n" + "3. Search for album by title.\n"
				+ "4. Search for album by artist.\n" + "5. Exit search.\n\n" + "Enter action: ", tempString);
		String inpString = runScannerOptions(5);

		if (inpString.equals("1")) {
			System.out.print("Enter song title: ");
			inpString = scanner.nextLine().strip();
			ArrayList<Song> tempArrayList = s.findSongByTitle(inpString);
			if (tempArrayList.size() > 0)
				System.out.printf("\nFound these songs with title: %s\n", inpString);
			else
				System.out.printf("\nFound no songs with title: %s\n", inpString);

			for (Song song : tempArrayList)
				System.out.println("- " + song);
		} else if (inpString.equals("2")) {
			System.out.print("Enter artist: ");
			inpString = scanner.nextLine().strip();
			ArrayList<Song> tempArrayList = s.findSongByArtist(inpString);
			if (tempArrayList.size() > 0)
				System.out.printf("\nFound these songs with artist: %s\n", inpString);
			else
				System.out.printf("\nFound no songs with artist: %s\n", inpString);

			for (Song song : tempArrayList)
				System.out.println("- " + song);
		} else if (inpString.equals("3")) {
			System.out.print("Enter album title: ");
			inpString = scanner.nextLine().strip();
			ArrayList<Album> tempArrayList = s.findAlbumByTitle(inpString);
			if (tempArrayList.size() > 0)
				System.out.printf("\nFound these albums with title: %s\n", inpString);
			else
				System.out.printf("\nFound no albums with title: %s\n", inpString);

			for (Album album : tempArrayList)
				System.out.println("- " + album);
		} else if (inpString.equals("4")) {
			System.out.print("Enter artist: ");
			inpString = scanner.nextLine().strip();
			ArrayList<Album> tempArrayList = s.findAlbumByArtist(inpString);
			if (tempArrayList.size() > 0)
				System.out.printf("\nFound these albums with artist: %s\n", inpString);
			else
				System.out.printf("\nFound no albums with artist: %s\n", inpString);

			for (Album album : tempArrayList)
				System.out.println("- " + album);
		}

		// Exit the music store
		else {
			System.out.printf("\nLeaving %s.\n\n", tempString);
			if (s.getClass() == MusicStore.class)
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
		System.out.printf("\nYou are in user library. Choose an action (1-5):\n" + "1. Search for songs or albums.\n"
				+ "2. Add song or album to library.\n" + "3. Rate a song.\n" + "4. Favorite a song.\n"
				+ "5. Get information for library.\n" + "6. Create playlist.\n"
				+ "7. Add or remove song from playlist.\n" + "8. Exit User Library.\n\n" + "Enter action: ");
		String inpString = runScannerOptions(8);
		if (inpString.equals("1")) {
			searchPrompt(lib);
		} else if (inpString.equals("2")) {
			addToLibrary();
		} else if (inpString.equals("3")) {
			rateSongInLibrary();
		} else if (inpString.equals("4")) {
			favoriteSong();
		} else if (inpString.equals("5")) {
			getLibraryInformation();
		} else if (inpString.equals("6")) {
			createPlaylist();
		} else if (inpString.equals("7")) {
			addRemoveFromPlaylist();
		}
		// If input is 8, exit library
		else {
			System.out.printf("\nLeaving User Library.\n\n");
			mainPrompt();
			return;
		}
		libraryPrompt();
	}

	private static void getLibraryInformation() {
		String inpString;
		System.out.printf("\nYou are in User library. Choose an action (1-5):\n" + "1. Show song titles.\n"
				+ "2. Show artists.\n" + "3. Show albums.\n" + "4. Show playlists.\n" + "5. Show favorited songs.\n\n"
				+ "Enter action: ");
		inpString = runScannerOptions(5);
		if (inpString.equals("1")) {
			ArrayList<Song> arrayList = lib.getSongList();
			for (Song song : arrayList) {
				System.out.println("- " + song);
			}
		} else if (inpString.equals("2")) {
			ArrayList<String> arrayList = lib.getArtists();
			for (String s : arrayList) {
				System.out.println("- " + s);
			}
		} else if (inpString.equals("3")) {
			ArrayList<String> arrayList = lib.getAlbums();
			for (String s : arrayList) {
				System.out.println("- " + s);
			}
		} else if (inpString.equals("4")) {
			ArrayList<String> arrayList = lib.getPlaylists();
			for (String s : arrayList) {
				System.out.println("- " + s);
			}
		} else {
			ArrayList<String> arrayList = lib.getFavorites();
			for (String s : arrayList) {
				System.out.println("- " + s);
			}
		}
	}

	private static void addToLibrary() {
		String inpString;
		System.out.printf("\nYou are adding from music store to user library. Choose an action (1-3):\n"
				+ "1. Add song.\n" + "2. Add album.\n" + "3. Exit.\n\n" + "Enter action: ");
		inpString = runScannerOptions(3);
		if (inpString.equals("1")) {
			System.out.print("\nEnter song title to add: ");
			inpString = scanner.nextLine().strip();
			ArrayList<Song> arr = ms.findSongByTitle(inpString);
			if (arr.size() == 0) {
				System.out.printf("No songs in music store with title: %s", inpString);
			} else {
				for (Song song : arr) {
					System.out.printf("Add %s? (Enter Y for yes): ", song);
					inpString = scanner.nextLine().strip();
					if (inpString.equals("Y"))
						lib.addSong(song);
				}
			}
		} else if (inpString.equals("2")) {
			System.out.print("\nEnter album title to add: ");
			inpString = scanner.nextLine().strip();
			ArrayList<Album> arr = ms.findAlbumByTitle(inpString);
			if (arr.size() == 0) {
				System.out.printf("No songs in music store with title: %s", inpString);
			} else {
				for (Album album : arr) {
					System.out.printf("Add %s by %s (%s)? (Enter Y for yes): ", album.getAlbumName(), album.getArtist(),
							album.getYear());
					inpString = scanner.nextLine().strip();
					if (inpString.equals("Y")) {
						for (Song song : album.getSongs())
							lib.addSong(song);
					}
				}
			}
		}
	}

	private static void rateSongInLibrary() {
		String inpString;

		System.out.println("Valid ratings are 1-5. Anything else will be interpreted as no rating.");
		System.out.print("\nEnter song title to rate: ");
		System.out.println("\nValid ratings are 1-5. Anything else will be interpreted as no rating.");
		inpString = scanner.nextLine().strip();
		ArrayList<Song> arr = ms.findSongByTitle(inpString);
		if (arr.size() == 0) {
			System.out.printf("No songs in library with title: %s", inpString);
		} else {
			for (Song song : arr) {
				System.out.printf("Rate %s: ", song);
				inpString = scanner.nextLine().strip();
				if (inpString.equals("1"))
					lib.rateSong(song.getTitle(), Rating.ONE);
				else if (inpString.equals("2"))
					lib.rateSong(song.getTitle(), Rating.TWO);
				else if (inpString.equals("3"))
					lib.rateSong(song.getTitle(), Rating.THREE);
				else if (inpString.equals("4"))
					lib.rateSong(song.getTitle(), Rating.FOUR);
				else if (inpString.equals("5"))
					lib.rateSong(song.getTitle(), Rating.FIVE);
				else
					lib.rateSong(song.getTitle(), Rating.NONE);
			}
		}
	}

	private static void favoriteSong() {
		String inpString;
		System.out.print("\nEnter song title to favorite: ");
		inpString = scanner.nextLine().strip();
		ArrayList<Song> arr = ms.findSongByTitle(inpString);
		if (arr.size() == 0) {
			System.out.printf("No songs in library with title: %s", inpString);
		} else {
			for (Song song : arr) {
				System.out.printf("Favorite %s? (Enter Y for yes): ", song);
				if (inpString.equals("Y"))
					lib.setSongToFavorite(song.getTitle());
			}
		}
	}

	private static void createPlaylist() {
		String inpString;
		System.out.print("Enter a name for your playlist: ");
		inpString = scanner.nextLine().strip();
		ArrayList<String> arr = lib.getPlaylists();
		if (arr.contains(inpString))
			System.out.printf("Already a playlist with name: %s\n", inpString);
		else {
			lib.createPlaylist(inpString);
			System.out.printf("Created new playlist: %s\n", inpString);
		}
	}

	private static void addRemoveFromPlaylist() {
		String inpString;

		System.out.print("Choose playlist: ");
		String playlist = scanner.nextLine().strip();
		System.out.printf("\n:\n" + "1. Add song.\n" + "2. Remove song.\n\n" + "Enter action: ");
		inpString = scanner.nextLine().strip();
		if (inpString.equals("1")) {
			System.out.print("Choose song to add: ");
			inpString = scanner.nextLine().strip();
			lib.addSongToPlaylist(playlist, inpString);
		} else if (inpString.equals("2")) {
			System.out.print("Choose song to remove: ");
			inpString = scanner.nextLine().strip();
			lib.removeSongFromPlaylist(playlist, inpString);
		} else
			System.out.println("Invalid input.");
	}

	private static boolean promptValidity(String s, int options) {
		try {
			String.valueOf(s);
		} catch (NumberFormatException e) {
			return false;
		}

		for (int i = 1; i < options + 1; ++i) {
			if (s.equals(String.valueOf(i))) {
				return true;
			}
		}
		return false;
	}

	private static String runScannerOptions(int options) {
		String inpString = "";
		while (scanner.hasNextLine()) {
			inpString = scanner.nextLine().strip();
			if (promptValidity(inpString, options))
				break;
			else
				System.out.printf("Invalid action: %s. Enter valid action: ", inpString);
		}
		return inpString;
	}
}
