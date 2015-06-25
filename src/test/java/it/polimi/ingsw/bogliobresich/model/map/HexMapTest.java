package it.polimi.ingsw.bogliobresich.model.map;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HexMapTest {

    
    private HexMap mp = new HexMap();
    
    @Test
    public void testGetHumanBase() {
        assertEquals(mp.getCoordinateHumanBase(),new Coordinate('L',8));
    }
    @Test
    public void testGetAlienBase() {
        assertEquals(mp.getCoordinateAlienBase(),new Coordinate('L',6));
    }
}
