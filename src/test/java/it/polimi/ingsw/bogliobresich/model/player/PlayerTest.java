package it.polimi.ingsw.bogliobresich.model.player;

import static org.junit.Assert.*;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.match.User;

import org.junit.Test;

public class PlayerTest {

    @Test
    public void test() {
        User u= new User (1,"pippo","pwd");
        Player p = new Player (u,null,null);
        Player p2 = new Player (u,null,null);
        Coordinate coord =new Coordinate(1,1);
        p.SetIsAlive(true);
        assertEquals(p.getIdPlayer(),1);
        assertFalse(p.isYourTurn);
        p.setIsYourTurn(true);
        assertTrue(p.isYourTurn);
        assertTrue(p.isAlive);
        assertTrue(p.canPlayObject);
        assertTrue(p.canDrawSectorCard);
        assertNotNull(p.getHand());
        p.setCoordinate(coord);
        assertEquals(p.getCoordinate(),coord);
        assertNotNull(p.hashCode());
        assertEquals(p.getCharacterCard(),null);
        assertTrue(p.equals(p2));
    }

}
