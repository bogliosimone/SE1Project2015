package it.polimi.ingsw.bogliobresich.model.player;

import static org.junit.Assert.*;
import it.polimi.ingsw.bogliobresich.model.match.User;

import org.junit.Test;

public class AlienPlayerTest {

    @Test
    public void test() {
        User u= new User (1,"pippo","pwd");
        AlienPlayer p= new AlienPlayer(u,null,null);
        assertFalse(p.isFeed);
        p.feed();
        assertTrue(p.isFeed);
    }

}
