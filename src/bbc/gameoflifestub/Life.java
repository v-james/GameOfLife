package bbc.gameoflifestub;

import java.util.Set;

public class Life {

	private Set<Cell> liveCells;
	int gridX; //first x value in grid = 0
	int gridY; //first y value in grid = 0

	public Life(Set<Cell> initialLiveCells, int gridX, int gridY) throws Exception
    {
		this.liveCells = initialLiveCells;
		this.gridX = gridX;
		this.gridY = gridY;
		
		if (gridX<initialLiveCells.size()){
			throw new Exception("grid does not enclose all cells");
		}
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
}
