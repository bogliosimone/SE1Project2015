package it.polimi.ingsw.bogliobresich.model.match;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.bogliobresich.model.deck.concretedeck.SectorDeck;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.match.action.DrawSectorAction;
import it.polimi.ingsw.bogliobresich.model.match.action.EndPhaseAction;
import it.polimi.ingsw.bogliobresich.model.match.action.EndTurnAction;
import it.polimi.ingsw.bogliobresich.model.match.action.MovementAction;
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
        assertTrue(match.isEnd());
    }

    @Test
    public void testTheAlienHighlander() {
        List<Player> players = match.getAllPlayer();
        for(int i = 0; i < players.size(); i++ ) {
            match.setState(new EndTurnState());
            match.doAction(match.getCurrentPlayer(), new TimerEndTurnAction());
        }
        Player p = match.getCurrentPlayer();
        if(match.playerIsAlien(p)) {
            assertTrue(match.isEnd());
        }
    }

    @Test
    public void testTheHumanHighlander() {
        List<Player> players = match.getAllPlayer();
        for(int i = 0; i < players.size(); i++ ) {
            match.setState(new EndTurnState());
            match.doAction(match.getCurrentPlayer(), new TimerEndTurnAction());
        }
        Player p = match.getCurrentPlayer();
        if (match.playerIsHuman(p)) {
            match.doAction(p, new MovementAction(new Coordinate('K',9)));
            match.doAction(p, new EndPhaseAction());
            match.doAction(p, new EndTurnAction());
            assertTrue(match.isEnd());
        }
    }

    @Test
    public void testDrawSectorCard() {
        Player player = match.getCurrentPlayer();
        SectorDeck deck = (SectorDeck) match.getSectorDeck();
        int originalSize = deck.size();
        if(match.playerIsHuman(player)) {
            match.doAction(player,new MovementAction(new Coordinate('K',8)));
        } else if (match.playerIsAlien(player)) {
            match.doAction(player,new MovementAction(new Coordinate('K',6)));
        }
        match.doAction(player,new DrawSectorAction());
        assertEquals(originalSize-1,match.getSectorDeck().size());
    }
}
