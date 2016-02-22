package bbc.gameoflifestub;

import java.util.HashSet;
import java.util.Iterator;

public class Grid {
	HashSet<Cell> cells; //set containing all cells for this iteration of grid
	int i;
	int j;
	
	//Constructor takes set of cells, number of columns i and number of rows j
	public Grid(HashSet<Cell> cells, int i, int j){
		this.cells = cells;
		this.i = i;
		this.j = j;
	}
	
	public String toString(){
		int x;
		int y;
		
		System.out.println("i: "+i);
		System.out.println("j: "+j);
		
		Iterator<Cell> iterator = cells.iterator();
		for (x = 0; x <= i;) {
			
			HashSet<Cell> line = new HashSet<Cell>();
			
			for (y = 0; y <= j;) {
					
					Cell cell = iterator.next();
					
					if (cell.getX() == x && cell.getY() == y) {
						System.out.println("y: "+y);
						System.out.println("x: "+x);
						line.add(cell);
					}
					System.out.println("x outside if: "+x);
					System.out.println("y outside if: "+y);
					y++;
				
			
			}
			
			System.out.println("Current line is: "+line);
			x++;
		}
		return cells.toString();
	}
	
}
