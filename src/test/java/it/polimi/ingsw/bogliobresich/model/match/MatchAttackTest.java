package it.polimi.ingsw.bogliobresich.model.match;

import static org.junit.Assert.*;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.match.action.AttackAction;
import it.polimi.ingsw.bogliobresich.model.match.action.EndPhaseAction;
import it.polimi.ingsw.bogliobresich.model.match.action.EndTurnAction;
import it.polimi.ingsw.bogliobresich.model.match.action.MovementAction;
import it.polimi.ingsw.bogliobresich.model.player.AlienPlayer;
import it.polimi.ingsw.bogliobresich.model.player.HumanPlayer;
import it.polimi.ingsw.bogliobresich.model.player.Player;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MatchAttackTest {

    private Match match;
    @Before
    public void init() {
        match = new Match(1,null);
        MatchTestUtil.initMatch(match, MatchTestUtil.generateUsers());
    }

    @Test
    public void testAttackKillingHuman() {
        List <Player> players = match.getAllPlayer();
        boolean lastIsAlien = false;
        for(Player player : players) {
            if(match.playerIsHuman(match.getCurrentPlayer())) {
                match.doAction(match.getCurrentPlayer(), new MovementAction(new Coordinate('L',9)));
                match.doAction(match.getCurrentPlayer(), new EndPhaseAction());
                match.doAction(match.getCurrentPlayer(), new EndTurnAction());
                lastIsAlien = false;
            }
            if(match.playerIsAlien(match.getCurrentPlayer())) {
                match.getCurrentPlayer().setCoordinate(new Coordinate('K',9));
                match.doAction(match.getCurrentPlayer(), new MovementAction(new Coordinate('L',9)));
                match.doAction(match.getCurrentPlayer(), new AttackAction());
                match.doAction(match.getCurrentPlayer(), new EndTurnAction());
                lastIsAlien = true;
            }
        }
        if(lastIsAlien == true) {
            for(Player player : players) {
                if(match.playerIsHuman(player)) {
                    assertFalse(player.isAlive());
                }
            }
        } else {
            assertTrue(match.atLeastOneHumaAlive());
        }
    }
}
