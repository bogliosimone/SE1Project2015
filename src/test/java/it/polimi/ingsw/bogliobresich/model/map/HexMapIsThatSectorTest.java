package it.polimi.ingsw.bogliobresich.model.map;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class HexMapIsThatSectorTest {

    @Parameters
    public static Collection<Object[]> data() {
        //Coordinate - porthole - safe - unsafe
        return Arrays.asList(new Object[][] {     
                { new Coordinate('B',2), true, false, false },
                { new Coordinate('B',13), true, false, false },
                { new Coordinate('V',2), true, false, false },
                { new Coordinate('V',13), true, false, false },
                
                { new Coordinate('L',6), false, false, false },
                { new Coordinate('L',8), false, false, false },
                
                { new Coordinate('H',3), false, true, false },
                { new Coordinate('H',4), false, false, true }
                
        });  
    }
    
    
    private HexMap mp = new HexMap();
    private Coordinate coordinate;
    private boolean isPorthole,isSafeSector,isUnsafeSector;
    
    public HexMapIsThatSectorTest(Coordinate coordinate, boolean isPorthole, boolean isSafeSector, boolean isUnsafeSector) {
        this.coordinate = coordinate;
        this.isPorthole = isPorthole;
        this.isSafeSector = isSafeSector;
        this.isUnsafeSector = isUnsafeSector;
    }
    
    @Test
    public void testIsPortholeSectorTest() {
        assertEquals(mp.coordinateIsPortholeSector(coordinate),isPorthole);
    }
    
    @Test
    public void testIsSafeSectorTest() {
        assertEquals(mp.coordinateIsSafeSector(coordinate),isSafeSector);
    }
    
    @Test
    public void testIsUnsafeSectorTest() {
        assertEquals(mp.coordinateIsUnsafeSector(coordinate),isUnsafeSector);
    }
}



