package it.polimi.ingsw.bogliobresich.model.map;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CubeCoordTest {

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {     
                {new Coordinate(1,1), 1,-1,0 },
                {new Coordinate(2,1), 2,-2,0 },
                {new Coordinate(2,2), 2,-3,1 },
                {new Coordinate(3,1), 3,-2,-1},
                {new Coordinate(3,2), 3,-3,0 },
                {new Coordinate(3,3), 3,-4,1 }
        });  
    }

    private Coordinate coordinate;
    private CubeCoord cubeCoordinate;
    private CubeCoord cubeCoordinate1;
    private int x;
    private int y;
    private int z;

    public CubeCoordTest(Coordinate coordinate, int x, int y, int z) {
        this.coordinate = coordinate;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Test
    public void testCubeCoordContructor() {
        //by coordinate
        cubeCoordinate = new CubeCoord(coordinate);
        assertEquals(cubeCoordinate.getX(),x);
        assertEquals(cubeCoordinate.getY(),y);
        assertEquals(cubeCoordinate.getZ(),z);
        //by coordinate x y z
        cubeCoordinate1 = new CubeCoord(x,y,z);
        assertEquals(cubeCoordinate1.getX(),x);
        assertEquals(cubeCoordinate1.getY(),y);
        assertEquals(cubeCoordinate1.getZ(),z);
        
        assertEquals(cubeCoordinate,cubeCoordinate1);
        assertFalse(cubeCoordinate.equals(null));
        assertFalse(cubeCoordinate1.equals(null));
    }
    public void testToString() {
        assertEquals(cubeCoordinate.toString(),"Cube coordinate: X=" + x + " Y=" + y + " Z=" + z);
    }
}
