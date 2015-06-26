package it.polimi.ingsw.bogliobresich.model.map.sector;

import static org.junit.Assert.*;

import org.junit.Test;

public class PortholeSectorTest {

    @Test
    public void test() {
        int number=1;
        char letter='b';
        int number2=1;
        PortholeSector sec = new PortholeSector(letter,number,number2);
        sec.setCrossable(false);
        assertEquals(sec.getCoordianteLetter(),letter);
        assertEquals(sec.getCoordinateNumber(),number);
        assertFalse(sec.isCrossable);
        assertEquals(sec.coordinate.toString()+" phNumb: "+sec.portholeNumber+" Crossable = "+sec.isCrossable,sec.toString());
        assertEquals(sec.getPortholeNumber(),number2);
    }

}
