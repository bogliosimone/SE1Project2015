package it.polimi.ingsw.bogliobresich.model.map;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class HexMapIsValidCoordinateTest {
    
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {     
                { new Coordinate('A',0), false },
                { new Coordinate('Z',42), false },
                { new Coordinate('A',1), true },
                { new Coordinate('K',7), true },
                { new Coordinate('K',8), true },
                { new Coordinate('K',9), true },
        });  
    }
    
    
    private HexMap mp = new HexMap();
    private Coordinate coordinate;
    private boolean result;
    
    public HexMapIsValidCoordinateTest(Coordinate coordinate, boolean result) {
        this.coordinate = coordinate;
        this.result = result;
    }
    
    @Test
    public void testIsValidCoordinate() {
        assertEquals(mp.isValidCoordinate(coordinate),result);
    }

}
