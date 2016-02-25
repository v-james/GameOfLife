package bbc.gameoflifestub;

import java.util.HashSet;
import java.util.Set;
/**
 * Class representing the grid of alive and dead cells for a single iteration of the game of life
 * @author vickyjames
 *
 */
public class Life {

	private Set<Cell> liveCells;
	int gridX; //number of columns in grid (grid starts at x=0)
	int gridY; //number of rows in grid (starts at y=0)

	/** 
	 * Constructor method
	 * @param Set<Cell> initialLiveCells
	 * @param int gridX
	 * @param int gridY
	 */
	public Life(Set<Cell> initialLiveCells, int gridX, int gridY) 
    {
		this.liveCells = initialLiveCells;
		this.gridX = gridX;
		this.gridY = gridY;
	}
	
	/**
	 * Method that creates a new life object that represents the next iteration
	 * @return Life object
	 */
	public Life runIteration(){
		int x = 0;
		int y = 0;
		
		//set of live cells representing the next iteration
		HashSet<Cell> newLiveCells = new HashSet<Cell>();
		
		//loop through all positions on grid
		for (y = 0; y <= gridY; y++) {
			for (x = 0; x <= gridX; x++) {
				Cell currentCell = new Cell(x, y, true);
				int liveNeighbourCount = getNumLiveNeighbours(currentCell);
				
				//if cell is alive:
				if (liveCells.contains(currentCell)){
					
					if(cellShouldSurvive(liveNeighbourCount, true)){
						newLiveCells.add(currentCell);
					}
				}
				
				//if cell is dead: 
				if(cellShouldSurvive(liveNeighbourCount, false)){
					newLiveCells.add(currentCell);
				}
			}
		}
		return new Life(newLiveCells, gridX, gridY);
	}	
	
	
    /**
     * Read-only access to the game state
     * @return Set<Cell> liveCells
     */
    public Set<Cell> getLiveCells()
    {
        return this.liveCells;
    }

    /**
     * determine number of live neighbours for a given Cell
     * @param Cell cell
     * @return int number of live cells
     */
    
    public int getNumLiveNeighbours(Cell cell){
    	
    	int currentCellX = cell.getX();
    	int currentCellY = cell.getY();
    	
    	// initiate neighbours lower and upper coordinates values
    	int lowerXBound = 0;
    	int upperXBound;
    	int lowerYBound = 0;
    	int upperYBound;
    	
    	//if cell is on boundary of grid, set lower/upper bound as the grid boundary
    	if(currentCellX == 0) { lowerXBound = currentCellX; }
    	
    	//else lower bound is one less than current x value
    	else { lowerXBound = currentCellX -1; }
    	
    	if(currentCellX == gridX) { upperXBound = currentCellX;}
    	else { upperXBound = currentCellX +1; }
    	
    	if(currentCellY == 0){ lowerYBound = currentCellY; }
    	else { lowerYBound = currentCellY-1; }
    	
    	if(currentCellY == gridY) { upperYBound = currentCellY; }
    	else {upperYBound = currentCellY+1; }
    	
    	//initiate parameters for loop
    	int x;
    	int y;
    	//set counter
    	int numLiveCells=0;
    	
    	//loop through all neighbours
    	for (x=lowerXBound; x<=upperXBound; x++){
    		for (y=lowerYBound; y<=upperYBound; y++){
    			
    			Cell cellToCheck = new Cell(x, y, true);
    			Cell originalCell = new Cell(currentCellX, currentCellY, true);
    			
    			//neighbour cell is alive and is not the original cell coordinate:
    			if (liveCells.contains(cellToCheck) && !cellToCheck.equals(originalCell)){
    				numLiveCells = numLiveCells +1;
    			}
    		}
    	}
    	return numLiveCells;
    }
    
    /**
     * Determines if a cell should be alive on next iteration by implementing the game of life rules 
     * @param int numNeighbours
     * @param boolean alive
     * @return boolean alive on next iteration
     */
    public boolean cellShouldSurvive(int numNeighbours, boolean alive)
    {	
    	//cell is alive:
    	if (alive){
    		//cell has 2 or 3 live neighbours:
    		if (numNeighbours == 2 || numNeighbours == 3){
    			return true;
    		}
    		return false;
    	}
    	
    	//cell is dead and has 3 neighbours:
    	else if(numNeighbours == 3){
    		return true;
    	}
    	return false;
    }
    
    /**
     * to string method that displays a life object as a series of rows and columns of . and *
     * @return String
     */
    public String toString(){
    	
    	int x = 0; //column value
    	int y = gridY; //row value (highest row value should be printed first)
    	StringBuilder grid = new StringBuilder();
    	
    	for (y=gridY; y>=0; y = y-1){
    		
    		StringBuilder line = new StringBuilder();
    		
    		for (x=0; x<=gridX; x++){
    			Cell currentCell = new Cell(x,y, true);
    			//if current cell is live:
    			if (liveCells.contains(currentCell)){
    				line.append("*");
    			}
    			else{
    				line.append(".");
    			}
    		}
    		grid.append(line);
    		grid.append("\n");
    		
    	}
   
    	String gridString = grid.toString();
    	return gridString;
    }
}
