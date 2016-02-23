package bbc.gameoflifestub;

import java.util.Set;

public class Life {

	private Set<Cell> liveCells;
	int gridX;
	int gridY;

	public Life(Set<Cell> initialLiveCells, int gridX, int gridY) throws Exception
    {
		this.liveCells = initialLiveCells;
		this.gridX = gridX;
		this.gridY = gridY;
		
		if (gridX<initialLiveCells.size()){
			throw new Exception("grid is smaller than number of cells added");
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
    	
    	if(currentCellX == gridX){
    		upperXBound = currentCellX;
    	}
    	else{
    		lowerXBound = currentCellX -1;
    		upperXBound = currentCellX +1;
    	}
    	
    	if(currentCellY == 0){
    		lowerYBound = currentCellY;
    	}
    	if(currentCellY == gridY){
    		upperYBound = currentCellY;
    	}
    	else{
    		lowerYBound = currentCellY -1;
    		upperYBound = currentCellY +1;
    	}
    	int x;
    	int y;
    	
    	int numLiveCells=0;
    	
    	for (x=lowerXBound; x<=upperXBound; x++){
    		for (y=lowerYBound; y<=upperYBound; y++){
    			
    			Cell cellToCheck = new Cell(x, y, true);
    			if (liveCells.contains(cellToCheck)){
    				numLiveCells=+1;
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
