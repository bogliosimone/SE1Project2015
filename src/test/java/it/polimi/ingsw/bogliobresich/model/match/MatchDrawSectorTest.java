package it.polimi.ingsw.bogliobresich.model.match;

import static org.junit.Assert.*;
import it.polimi.ingsw.bogliobresich.model.match.action.DrawSectorAction;
import it.polimi.ingsw.bogliobresich.model.match.state.DrawSectorPhaseState;

import org.junit.Before;
import org.junit.Test;

public class MatchDrawSectorTest {
    Match match;
    @Before
    public void init() {
        match = new Match(0,null);
        MatchTestUtil.initMatch(match, MatchTestUtil.generateUsers());
    }
    
    @Test
    public void testDrawSector() {
        int size = match.getSectorDeck().size();
        match.setState(new DrawSectorPhaseState());
        match.doAction(match.getCurrentPlayer(), new DrawSectorAction());
        assertEquals(size-1,match.getSectorDeck().size());
    }

}
