package bbc.gameoflifestub;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;
/**
 * Main class that obtains initial grid set up from user and runs iterations for the Game Of Life
 * @author vickyjames
 *
 */
public class Main {

	public static void main(String[] args) {
		try {
			//obtain user input for initial grid state
			String initialStateInput = getInitialState();
			Life initialLife = generateLife(initialStateInput);
			System.out.println("Initial state: \n" + initialLife);

			//obtain iteration number from user
			String iterationNumString = getIterationNumber();
			int iterationNum = Integer.parseInt(iterationNumString);
			
			int i = 1;
			Life iteration = initialLife;
			
			//loop through iterations
			for (i = 1; i <= iterationNum; i++) {
				System.out.println("iteration number " + i + ":");
				
				//obtain next life and print
				Life nextLife = iteration.runIteration();
				System.out.println(nextLife);
				
				iteration = nextLife;
			}
		} catch (Exception e) {
			System.out.println(e);
			System.out.println(e.getStackTrace());
		}

	}
	
	/**
	 * 
	 * @param initialState
	 * @return
	 * @throws Exception
	 */
	public static Life generateLife(String initialState) throws Exception{
		HashSet<Cell> liveCells = new HashSet<Cell>();
		Scanner scanner = new Scanner(initialState);
		
		//i and j are counters that represent the grid values 
		int i = 0; //column
		int j = 0; //row
		
		int iMax = 0;
		
		while (scanner.hasNext()){
			String inputValue = scanner.next();
			
			//create new live cell when * is read
			if (inputValue.equals("*")) {
				
				Cell liveCell = new Cell(i, j, true);
				liveCells.add(liveCell);
				i++;
			}
			
			//make new line when  / is read
			else if (inputValue.equals("/")) {
				//for all lines except the first check that it has same number of elements as previous line
				if (j != 0 && i != iMax){
					throw new Exception("The input given has unbalanced lines");
				}
				j++;
				iMax = i;
				i=0;
			
			// for any other input increase column no by 1
			} else  {
				i++;
					
			}
			
			//check last line has correct number of elements
			if(!scanner.hasNext() && i != iMax){
				throw new Exception("The input given has unbalanced lines");
			}
		}
		
		scanner.close();
		
		//create Life from set of live cells
		Life life = new Life(liveCells, i-1,j); //final column number is i-1
		return life;
	}
	
	public static String getIterationNumber() throws IOException{
		
		//prompt user to enter iteration number
		System.out.println("Please enter the number of iterations you wish for this Game of Life");
		InputStreamReader r = new InputStreamReader(System.in);

		BufferedReader b = new BufferedReader(r);

		String s;
		
		s = b.readLine();
		
		return s;
	}

	public static String getInitialState() throws Exception {

		// prompt user to enter initial game of life state
		System.out.println("Please specifiy the initial state for the Game Of Life. Insert a / for a new line. Separate cells with a space.");
		System.out.println("(For example: . . . / * * * / . . .)");
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
