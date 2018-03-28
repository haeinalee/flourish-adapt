import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
	// cin = console in
	static Scanner cin = new Scanner(System.in);
    // cout = console out
	static PrintStream cout = new PrintStream(System.out);
	
	static String player_name = "";
	static int funding = 0;
	static String instr = "\nPress (P) for player info; (O) to open inbox; (D) [word] for definition; (M) for climate metrics; (X) to end";
	static Map<String, String> dictionary = new HashMap<String, String>();
	static List<String> climate_metrics = new ArrayList<String>();
	
	public static void main(String[] args) {
		loadGame();
		cout.println("What is your name? ");
		player_name = cin.nextLine();
		
		// Print introduction.
		try {
			printIntro();
		} catch (FileNotFoundException e) {
			cout.print(e.getMessage());
			exitGame();
		}
		
		cout.println(instr);
		String command = cin.nextLine();
		while (!command.equalsIgnoreCase("S")) {
			String[] parsedCommand = command.split(" ");
			// Define a word
			if (command.startsWith("D ") || command.startsWith("d ")) {
				String term = command.substring(2).trim();
				if (dictionary.containsKey(term)) {
					cout.println(term + ":" + dictionary.get(term));
				} else {
					cout.println("No definition found for " + parsedCommand[1] + ".");
				}
				
			// Print out player info
			} else if (command.startsWith("P") || command.startsWith("p")) {
				cout.println("Player " + player_name + " Stats:");
				cout.print("\tfunding (" + funding + "/50):");
				for (int i = 0; i < 50; i++) {
					cout.print("+");
				}
				cout.println();
				
			}
			cout.println(instr);
			command = cin.nextLine();
		}
	}

	public static void printIntro() throws FileNotFoundException {
		Scanner readIntro = new Scanner(new File("resources/intro.txt"));
		String line = readIntro.nextLine();
		String[] splitline = line.split("_");
		print(splitline[0]);
		print(player_name);
		print(splitline[2]);
		System.out.println();
		while (readIntro.hasNextLine()) {
			String s = readIntro.nextLine();
			print(s);
			System.out.println();
		}
	}
	
	public static void print(String s) {
		for (int i = 0; i < s.length(); i++) {
			cout.print(s.charAt(i));
			try {
				TimeUnit.MILLISECONDS.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
				exitGame();
			}
		}
	}
	
	public static void loadGame() {
		// Populate climate_metrics
		try {
			Scanner readClimateMetrics = new Scanner(new File("resources/climate_metrics.txt"));
			while (readClimateMetrics.hasNextLine()) {
				String s = readClimateMetrics.nextLine();
				String[] splitLine = s.split(":");
				climate_metrics.add(splitLine[0]);
				dictionary.put(splitLine[0], splitLine[1]);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			exitGame();
		}
		funding = 50;
	}
	
	public static void endGame() {
		print("Thanks for playing!");
	}
	
	public static void exitGame() {
		cout.println("An unexpected error occurred. Ending game...");
		while (true) {}
	}
}
