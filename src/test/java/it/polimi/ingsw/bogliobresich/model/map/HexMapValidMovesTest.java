package it.polimi.ingsw.bogliobresich.model.map;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
@RunWith(Parameterized.class)
public class HexMapValidMovesTest {
    
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {     
                { new Coordinate('K',8), new Coordinate('J',8), 1, true },
                { new Coordinate('K',8), new Coordinate('K',9), 1, true },
                { new Coordinate('K',8), new Coordinate('K',7), 1, false },
                { new Coordinate('K',8), new Coordinate('L',8), 1, false },
                
                
                { new Coordinate('L',6), new Coordinate('J',6), 1, false },
                { new Coordinate('L',6), new Coordinate('J',6), 2, true },
                { new Coordinate('L',6), new Coordinate('J',5), 2, true },
                { new Coordinate('L',6), new Coordinate('K',5), 2, true },
                { new Coordinate('L',6), new Coordinate('L',4), 2, true },
                { new Coordinate('L',6), new Coordinate('M',5), 2, true },
                { new Coordinate('L',6), new Coordinate('N',5), 2, true },
                { new Coordinate('L',6), new Coordinate('N',6), 2, true },
        });  
    }
    
    
    private HexMap mp = new HexMap();
    private Coordinate start, end;
    private int range;
    private boolean result;
    
    public HexMapValidMovesTest(Coordinate start, Coordinate end, int range, boolean result) {
        this.start = start;
        this.end = end;
        this.range = range;
        this.result = result;
    }
    
    @Test
    public void testIsValidMove() {
        assertTrue(mp.isValidCoordinate(start));
        assertTrue(mp.isValidCoordinate(end));
        assertEquals(mp.isValidMove(start, end, range),result);
    }

}
