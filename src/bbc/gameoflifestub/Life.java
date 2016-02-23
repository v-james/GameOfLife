package bbc.gameoflifestub;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Life {

	private Set<Cell> liveCells;
	int gridX; //first x value in grid = 0
	int gridY; //first y value in grid = 0

	public Life(Set<Cell> initialLiveCells, int gridX, int gridY) 
    {
		this.liveCells = initialLiveCells;
		this.gridX = gridX;
		this.gridY = gridY;
		
		
	}
	
	public Life runIteration() throws Exception{
		int x = 0;
		int y = 0;
		
		HashSet<Cell> newLiveCells = new HashSet<Cell>();
		
		//loop through all positions on grid
		for (y = 0; y <= gridY; y++) {
			for (x = 0; x <= gridX; x++) {
				Cell currentCell = new Cell(x, y, true);
				int liveNeighbourCount = getNumLiveNeighbours(currentCell);
				
				//cell is alive
				if (liveCells.contains(currentCell)){
					
					//check if it should stay alive
					if(cellShouldSurvive(liveNeighbourCount, true)){
						//add live cell in this position to new live cell set
						newLiveCells.add(currentCell);
					}
					
				}
				//check if cell should become alive
				if(cellShouldSurvive(liveNeighbourCount, false)){
					newLiveCells.add(currentCell);
				}
			}
		}
		return new Life(newLiveCells, gridX, gridY);
	}	
    // Read-only access to the game state
    public Set<Cell> getLiveCells()
    {
        return this.liveCells;
    }

    //determine number of live neighbours for a specific cell
    public int getNumLiveNeighbours(Cell cell){
    	int currentCellX = cell.getX();
    	int currentCellY = cell.getY();
    	int lowerXBound = 0;
    	int upperXBound;
    	int lowerYBound = 0;
    	int upperYBound;
    	
    	if(currentCellX == 0){
    		lowerXBound = currentCellX;
    	}
    	else{
    		lowerXBound = currentCellX -1;
    	}
    	
    	if(currentCellX == gridX){
    		upperXBound = currentCellX;
    	}
    	else{
    		upperXBound = currentCellX +1;
    	}
    	
    	if(currentCellY == 0){
    		lowerYBound = currentCellY;
    	}
    	else{
    		lowerYBound = currentCellY-1;
    	}
    	if(currentCellY == gridY){
    		upperYBound = currentCellY;
    	}
    	else{
    		upperYBound = currentCellY+1;
    	}
    	int x;
    	int y;
    	
    	int numLiveCells=0;
    	
    	for (x=lowerXBound; x<=upperXBound; x++){
    		for (y=lowerYBound; y<=upperYBound; y++){
    			
    			Cell cellToCheck = new Cell(x, y, true);
    			Cell originalCell = new Cell(currentCellX, currentCellY, true);
    			if (liveCells.contains(cellToCheck) && !cellToCheck.equals(originalCell)){
    				numLiveCells = numLiveCells +1;
    			}
    		}
    	}
    	
    	return numLiveCells;
    	
    }
    
    public boolean cellShouldSurvive(int numNeighbours, boolean alive)
    {
    	if (alive){
    		if (numNeighbours == 2 || numNeighbours == 3){
    			return true;
    		}
    		return false;
    	}
    	
    	else if(numNeighbours == 3){
    		return true;
    	}
    	return false;
    }
    
    public String toString(){
    	int x=0;
    	int y=0;
    	StringBuilder grid = new StringBuilder();
    	
    	for (y=0; y<=gridY; y++){
    		
    		StringBuilder line = new StringBuilder();
    		
    		for (x=0; x<=gridX; x++){
    			Cell currentCell = new Cell(x,y, true);
    			if (liveCells.contains(currentCell)){
    				line.append("*");
    			}
    			else{
    				line.append(".");
    			}
    		}
    		grid.append(line);
    		
    	}
   
    	String gridString = grid.toString();
    	return gridString;
    }
}
