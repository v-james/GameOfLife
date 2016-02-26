package bbc.gameoflifestub;

/**
 * Class representing a cell in the Game Of Life
 * @author vickyjames
 *
 */
public class Cell {
	private int x; //grid coordinate
	private int y; //grid coordinate
	boolean alive; 

	/**
	 * Constructor
	 * @param int x
	 * @param int y
	 * @param alive
	 */
	public Cell(int x, int y, boolean alive){
		this.x = x;
		this.y = y;		
		this.alive = alive;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public boolean isAlive(){
		
		if (alive){
			return true;
		}
		else return false;
	}
	
	public int hashCode(){
		return 31 * 31 * x + 31 * y;
	}
	
	public boolean equals(Object other){
		Cell otherCell = (Cell)other;
		return otherCell.x == x && otherCell.y == y; 
	}
	
	/**
	 * Generates the String representation of Cell object:
	 * alive = *
	 * dead = .
	 */
	public String toString(){
		String output= "";
		if (alive){
			output = "*";
		}
		if (!alive){
			output = ".";
		}
		return output;
		
	}
}
