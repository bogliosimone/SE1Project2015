package it.polimi.ingsw.bogliobresich.model.match;

import static org.junit.Assert.*;
import it.polimi.ingsw.bogliobresich.model.match.action.TimerEndTurnAction;
import it.polimi.ingsw.bogliobresich.model.match.state.EndTurnState;
import it.polimi.ingsw.bogliobresich.model.player.Player;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MatchTest {

    private Match match;
    @Before
    public void init() {
        match = new Match(1,null);
        MatchTestUtil.initMatch(match, MatchTestUtil.generateUsers());
    }

    @Test
    public void testCurrentPlayerNotNull() {
        Player p = match.getCurrentPlayer();
        assertNotNull(p);
    }
    
    @Test
    public void testPlayerTimeOut() {
        List<Player> players = match.getAllPlayer();
        for(Player p : players) {
            match.setState(new EndTurnState());
            match.doAction(match.getCurrentPlayer(), new TimerEndTurnAction());
        }
        players = match.getAllPlayer();
        for(Player p : players) {
            assertFalse(p.isConnected());
        }
        assertTrue(match.isEnd());
    }
}
