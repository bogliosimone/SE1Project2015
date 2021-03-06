package it.polimi.ingsw.bogliobresich.model.map.sector;

import static org.junit.Assert.*;

import org.junit.Test;

public class HumanBaseSectorTest {

    @Test
    public void test() {
        int number=0;
        char letter='b';
        Sector sec = new HumanBaseSector(letter,number);
        assertEquals(sec.getCoordianteLetter(),letter);
        assertEquals(sec.getCoordinateNumber(),number);
    }

    @Test
    public void test2() {
        int number=0;
        int number2=1;
        Sector sec = new HumanBaseSector(number,number2);
        assertFalse(sec.isCrossable());
    }
    
}
