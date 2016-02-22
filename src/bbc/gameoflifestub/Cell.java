package bbc.gameoflifestub;

public class Cell {
	private int x;
	private int y;
	boolean alive;

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
	
	public int hashCode(){
		return 31 * 31 * x + 31 * y;
	}
	
	public boolean equals(Object other){
		Cell otherCell = (Cell)other;
		return otherCell.x == x && otherCell.y == y; 
	}
	
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
