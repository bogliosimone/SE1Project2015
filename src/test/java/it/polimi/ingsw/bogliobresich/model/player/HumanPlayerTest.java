package it.polimi.ingsw.bogliobresich.model.player;

import static org.junit.Assert.*;
import it.polimi.ingsw.bogliobresich.model.match.User;

import org.junit.Test;

public class HumanPlayerTest {

    @Test
    public void test() {
        User u= new User (1,"pippo","pwd");
        HumanPlayer p = new HumanPlayer (u,null,null);
        p.setCanAttack(true);
        p.setCanDrawSectorCard(false);
        p.setMovementStep(3);
        assertTrue(p.canAttack);
        assertFalse(p.canDrawSectorCard);
        assertEquals(p.getMovementStep(),3);
    }

}
