package bbc.gameoflifestub;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		try{
		String initialStateInput = getInitialState();
		HashSet<Cell> initialGrid = populateGrid(initialStateInput);
		System.out.println(initialGrid);
		} catch (Exception e) {
			e.getStackTrace();
		}

	}
	
	public static HashSet<Cell> populateGrid(String initialState){
		HashSet<Cell> initialGrid = new HashSet<Cell>();
		Scanner scanner = new Scanner(initialState);
		
		while (scanner.hasNext()){
			if (scanner.next().equals(".")){
				Cell deadCell = new Cell(0,0);
				initialGrid.add(deadCell);
			}
		}
		scanner.close();
		return initialGrid;
		
	}
	
	public static String getInitialState() throws Exception {

		// prompt user to enter initial game of life state
		System.out.println("Please specifiy the initial state for the Game Of Life. Insert a / for a new line");
		InputStreamReader r = new InputStreamReader(System.in);

		// new buffered reader object from users input, r.
		BufferedReader b = new BufferedReader(r);

		// initiate variable for users input
		String s;

		try {
			s = b.readLine();

			
			if (s.isEmpty()) {
				System.out.println("No user input found, all cells are assumed dead.");
				s = ".";
				System.out.println("Initial state for this Game of Life is:");
				System.out.println(s);
			}

		} catch (Exception e) {
			System.out.println("User input error: " + e);
			System.out.println("All cells assumed dead");
			s = ".";
			System.out.println("Initial state for this Game of Life is:");
			System.out.println(s);
		}
		
		System.out.println("Initial state for this Game of Life is:");
		System.out.println(s);
		
		return s;
	}

}
