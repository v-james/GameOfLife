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
        life = new Life(setOfCells, 2,1);
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
    public void testUnderpopulation()
    {
    	
        assertFalse(life.cellShouldSurvive(0, true));
        assertFalse(life.cellShouldSurvive(1, true));
    }
}
