package it.polimi.ingsw.bogliobresich.model.match;

import static org.junit.Assert.*;
import it.polimi.ingsw.bogliobresich.model.match.action.PortholeAction;
import it.polimi.ingsw.bogliobresich.model.match.state.EndPhaseTurnState;
import it.polimi.ingsw.bogliobresich.model.match.state.PortholePhaseTurnState;
import it.polimi.ingsw.bogliobresich.model.player.Player;

import org.junit.Before;
import org.junit.Test;

public class MatchPortholeTest {
    Match match;
    @Before
    public void init() {
        match = new Match(0,null);
        MatchTestUtil.initMatch(match, MatchTestUtil.generateUsers());
    }
    
    
    @Test
    public void testDrawPorthole() {
        int size = match.getPortholeDeck().size();
        Player cPlayer = match.getCurrentPlayer();
        match.setState(new PortholePhaseTurnState());
        match.doAction(match.getCurrentPlayer(), new PortholeAction());
        assertEquals(size-1, match.getPortholeDeck().size());
    }
    
    @Test
    public void testDrawPortholeAndWin() {
        Player cPlayer = match.getCurrentPlayer();
        match.setState(new PortholePhaseTurnState());
        match.doAction(match.getCurrentPlayer(), new PortholeAction());
        if(cPlayer.equals(match.getCurrentPlayer())) {
            assertFalse(cPlayer.isWinner());
        }
    }
    
    @Test
    public void testDrawPortholeAndLoose() {
        Player cPlayer = match.getCurrentPlayer();
        match.setState(new PortholePhaseTurnState());
        match.doAction(match.getCurrentPlayer(), new PortholeAction());
        if(!cPlayer.equals(match.getCurrentPlayer())) {
            assertTrue(cPlayer.isWinner());
        }
    }
}
