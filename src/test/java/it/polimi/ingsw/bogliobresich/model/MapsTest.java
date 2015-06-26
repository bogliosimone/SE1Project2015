package it.polimi.ingsw.bogliobresich.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class MapsTest {

    @Test
    public void test() {
        for(Maps tmpMap:Maps.values()){
            assertNotNull(tmpMap.getFileNameMap());
            assertNotNull(tmpMap.getNameMap());
            assertNotNull(tmpMap.getNumberMap());
            assertNotNull(tmpMap.getNumberPlayerMap());
        }
    }

}
