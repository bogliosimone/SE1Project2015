package it.polimi.ingsw.bogliobresich.model.map;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CoordinateTest {
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {     
                 { 'A', 1 , 1 , 1},
                 { 'B', 1 , 2 , 1},
                 { 'C', 1 , 3 , 1},
                 { 'D', 1 , 4 , 1},
                 { 'E', 1 , 5 , 1},
                 { 'F', 1 , 6 , 1},
                 { 'G', 1 , 7 , 1},
                 { 'H', 1 , 8 , 1},
                 { 'I', 1 , 9 , 1}, 
                 { 'J', 1 , 10 , 1}, 
                 { 'K', 1 , 11 , 1}, 
                 { 'L', 1 , 12 , 1}, 
                 { 'M', 1 , 13 , 1}, 
                 { 'N', 1 , 14 , 1}, 
                 { 'O', 1 , 15 , 1}, 
                 { 'P', 1 , 16 , 1}, 
                 { 'Q', 1 , 17 , 1}, 
                 { 'R', 1 , 18 , 1}, 
                 { 'S', 1 , 19 , 1}, 
                 { 'T', 1 , 20 , 1}, 
                 { 'U', 1 , 21 , 1}, 
                 { 'V', 1 , 22 , 1}, 
                 { 'W', 1 , 23 , 1}, 
                 { 'X', 1 , 24 , 1}, 
                 { 'Y', 1 , 25 , 1}, 
                 { 'Z', 1 , 26 , 1}, 
                 { 'Z', 2 , 26 , 2}, 
                 { 'Z', 3 , 26 , 3} 
           });  
    }
    
    private Coordinate c1,c2;
    private char letter;
    private int number;
    private int x;
    private int y;
    
    public CoordinateTest(char letter, int number, int x, int y) {
        this.letter = letter;
        this.number = number;
        this.x = x;
        this.y = y;
    }
    
    @Test
    public void testCoordinateConstructor() {
        c1 = new Coordinate(letter,number);
        assertEquals(letter, c1.getLetter());
        assertEquals(number, c1.getNumber());
        c2 = new Coordinate(x,y);
        assertEquals(x, c2.getX());
        assertEquals(y, c2.getY());
        assertEquals(c1,c2);
    }
    @Test
    public void testToString() {
        c1 = new Coordinate(letter,number);
        c2 = new Coordinate(x,y);
        String s;
        s = letter+""+number;
        assertEquals(c1.toString(),c2.toString());
        assertEquals(c1.toString(),s);
    }

}
