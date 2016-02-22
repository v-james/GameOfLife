package bbc.gameoflifestub;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		try{
		String initialStateInput = getInitialState();
		Grid initialGrid = populateGrid(initialStateInput);
		System.out.println(initialGrid);
		} catch (Exception e) {
			e.getStackTrace();
		}

	}
	
	public static Grid populateGrid(String initialState){
		HashSet<Cell> cells = new HashSet<Cell>();
		Scanner scanner = new Scanner(initialState);

		int length = initialState.length();
		int i = 0;
		int j = 0;

		while (scanner.hasNext()) {
			String inputValue = scanner.next();
			
			if (inputValue.equals(".")) {
				Cell deadCell = new Cell(i, j, false);
				cells.add(deadCell);
				i++;
			} else if (inputValue.equals("*")) {
				Cell deadCell = new Cell(i, j, true);
				cells.add(deadCell);
				i++;
			}

			else if (inputValue.equals("/")) {
				j++;
				i=0;
			}

		}
		scanner.close();
		
		Grid initialGrid = new Grid(cells, i, j);
		return initialGrid;

	}
	
	public static String getInitialState() throws Exception {

		// prompt user to enter initial game of life state
		System.out.println("Please specifiy the initial state for the Game Of Life. Insert a / for a new line. Separate cells with a space.");
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
			}

		} catch (Exception e) {
			System.out.println("User input error: " + e);
			System.out.println("All cells assumed dead");
			s = ".";
		}
		
		
		
		return s;
	}

}
