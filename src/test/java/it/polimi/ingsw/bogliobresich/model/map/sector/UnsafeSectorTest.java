package it.polimi.ingsw.bogliobresich.model.map.sector;

import static org.junit.Assert.*;

import org.junit.Test;

public class UnsafeSectorTest {

    @Test
    public void test() {
        int number=0;
        char letter='b';
        Sector sec = new UnsafeSector(letter,number);
        assertEquals(sec.getCoordianteLetter(),letter);
        assertEquals(sec.getCoordinateNumber(),number);
    }

}
