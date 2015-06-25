package it.polimi.ingsw.bogliobresich.model.map.sector;

import static org.junit.Assert.*;

import org.junit.Test;

public class HumanBaseSectorTest {

    @Test
    public void test() {
        int number=0;
        char letter='b';
        Sector sec = new AlienBaseSector(letter,number);
        assertFalse(sec.isCrossable());
    }

    @Test
    public void test2() {
        int number=0;
        int numner=1;
        Sector sec = new AlienBaseSector(number,number);
        assertFalse(sec.isCrossable());
    }
    
}
