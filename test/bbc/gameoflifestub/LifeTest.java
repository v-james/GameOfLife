package bbc.gameoflifestub;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
	
public class LifeTest {

	protected Set<Cell> setOfCells;
	protected Life life;
	
	@Before
	public void setUp(){
    	setOfCells = new HashSet<Cell>();
    	setOfCells.add(new Cell(1,1, true));
    	setOfCells.add(new Cell(2,2, true));
    	try{
        life = new Life(setOfCells, 2,2);
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    }
	
	@Test
	public void testInitialisation()
	{
		assertEquals(2, life.getLiveCells().size());
	}

	@Test
	public void testNumNeighbours(){
		Cell cell00 = new Cell(0,0, false);
		assertEquals(1, life.getNumLiveNeighbours(cell00));
		
		Cell cell10 = new Cell(1,0, false);
		assertEquals(1, life.getNumLiveNeighbours(cell10));
		
		Cell cell20 = new Cell(2,0, false);
		assertEquals(1, life.getNumLiveNeighbours(cell20));
		
		Cell cell01 = new Cell(0,1, false);
		assertEquals(1, life.getNumLiveNeighbours(cell01));
		
		Cell cell11 = new Cell(1,1, true);
		assertEquals(1, life.getNumLiveNeighbours(cell11));
		
		Cell cell21 = new Cell(2,1, false);
		assertEquals(2, life.getNumLiveNeighbours(cell21));
		
		Cell cell02 = new Cell(0,2, false);
		assertEquals(1, life.getNumLiveNeighbours(cell02));
		
		Cell cell12 = new Cell(1,2, false);
		assertEquals(2, life.getNumLiveNeighbours(cell12));
		
		Cell cell22 = new Cell(2,2, true);
		assertEquals(1, life.getNumLiveNeighbours(cell22));
	}
	
    @Test
    public void testUnderpopulation()
    {
    	
        assertFalse(life.cellShouldSurvive(0, true));
        assertFalse(life.cellShouldSurvive(1, true));
        assertFalse(life.cellShouldSurvive(0, false));
        assertFalse(life.cellShouldSurvive(1, false));
    }
    
    @Test
    public void testOverpopulation(){
    	assertFalse(life.cellShouldSurvive(4, true));
        assertFalse(life.cellShouldSurvive(4, false));
    }
    
    @Test
    public void testCreationOfLife(){
    	assertFalse(life.cellShouldSurvive(0, false));
    	assertFalse(life.cellShouldSurvive(1, false));
    	assertFalse(life.cellShouldSurvive(2, false));
    	assertTrue(life.cellShouldSurvive(3, false)); //only dead cell with 3 neighbours should survive 
    	assertFalse(life.cellShouldSurvive(4, false));
    }
    
    @Test
    public void testSurvival(){
    	assertTrue(life.cellShouldSurvive(3, true));
    	assertTrue(life.cellShouldSurvive(2, true));
    }
}
