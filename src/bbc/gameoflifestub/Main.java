package bbc.gameoflifestub;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		try{
		String initialStateInput = getInitialState();
		Life initialLife = generateLife(initialStateInput);
		System.out.println("1: ");
		System.out.println(initialLife);
		
		Life iteration2 = initialLife.runIteration();
		System.out.println("2: ");
		System.out.println(iteration2);
		
		Life iteration3 = iteration2.runIteration();
		System.out.println("3: ");
		System.out.println(iteration3);
		} catch (Exception e) {
			e.getStackTrace();
		}

	}
	
	public static Life generateLife(String initialState) throws Exception{
		HashSet<Cell> liveCells = new HashSet<Cell>();
		Scanner scanner = new Scanner(initialState);
		
		int i = 0;
		int j = 0;
		while (scanner.hasNext()){
			String inputValue = scanner.next();
			
			if (inputValue.equals(".")) {
				i++;
				
			} else if (inputValue.equals("*")) {
				Cell liveCell = new Cell(i, j, true);
				liveCells.add(liveCell);
				i++;
			}

			else if (inputValue.equals("/")) {
				j++;
				i=0;
			}
		}
		scanner.close();
		
		
		Life life = new Life(liveCells, i-1,j); //need to do 1 less here for i since i has increased by one (waiting for next cell)
		return life;
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
