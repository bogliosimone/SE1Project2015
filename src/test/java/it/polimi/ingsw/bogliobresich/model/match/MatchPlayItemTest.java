package it.polimi.ingsw.bogliobresich.model.match;

import static org.junit.Assert.*;
import it.polimi.ingsw.bogliobresich.model.cards.AdrenalineItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.AttackItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.SedativesItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.SpotlightItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.TeleportItemCard;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.match.action.EndPhaseAction;
import it.polimi.ingsw.bogliobresich.model.match.action.EndTurnAction;
import it.polimi.ingsw.bogliobresich.model.match.action.MovementAction;
import it.polimi.ingsw.bogliobresich.model.player.Player;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class MatchPlayItemTest {
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {     
                { new AdrenalineItemCard(0) },
                { new AttackItemCard(0) },
                { new SedativesItemCard(0) },
                { new SpotlightItemCard(0) },
                { new TeleportItemCard(0) }
        });  
    }

    private ItemCard forcedCard;
    private Match match;
    private Player p;

    public MatchPlayItemTest(ItemCard card) {
        this.forcedCard = card;
        match = new Match(0,null);
        MatchTestUtil.initMatch(match, MatchTestUtil.generateUsers());
        List <Player> players = match.getAllPlayer();
        while(!match.playerIsHuman(match.getCurrentPlayer())) {
            match.doAction(match.getCurrentPlayer(), new MovementAction(new Coordinate('K',5)));
            match.doAction(match.getCurrentPlayer(), new EndPhaseAction());
            match.doAction(match.getCurrentPlayer(), new EndTurnAction());
        }
        this.p = match.getCurrentPlayer();
    }

    @Test
    public void testPlayItemCardInInitPhase() {
        if(forcedCard.isPlayableInitPhase()) {
            if(forcedCard instanceof SpotlightItemCard) {
                ((SpotlightItemCard) forcedCard).setCoordToLight(new Coordinate('K',5));
                forcedCard.play(match, p);
                assertNotNull(forcedCard.getInfo());
            }
            else {
                forcedCard.play(match, p);

                if(forcedCard instanceof AdrenalineItemCard) {
                    assertEquals(p.getMovementStep(),2);
                }
                else if(forcedCard instanceof AttackItemCard) {
                    assertTrue(p.canAttack());
                }
                else if(forcedCard instanceof SedativesItemCard) {
                    assertFalse(p.canDrawSectorCard());
                }

                else if(forcedCard instanceof TeleportItemCard) {
                    assertEquals(p.getCoordinate(), match.getGameMap().getCoordinateHumanBase());
                }
            }
        }
    }

    @Test
    public void testPlayItemCardInMovePhase() {
        if(forcedCard.isPlayableMovePhase()) {
            match.doAction(match.getCurrentPlayer(), new MovementAction(new Coordinate('M',8)));
            if(forcedCard instanceof SpotlightItemCard) {
                ((SpotlightItemCard) forcedCard).setCoordToLight(new Coordinate('K',5));
                forcedCard.play(match, p);
                assertNotNull(forcedCard.getInfo());
            }
            else {
                forcedCard.play(match, p);

                if(forcedCard instanceof AdrenalineItemCard) {
                    assertEquals(p.getMovementStep(),2);
                }
                else if(forcedCard instanceof AttackItemCard) {
                    assertTrue(p.canAttack());
                }
                else if(forcedCard instanceof SedativesItemCard) {
                    assertFalse(p.canDrawSectorCard());
                }

                else if(forcedCard instanceof TeleportItemCard) {
                    assertEquals(p.getCoordinate(), match.getGameMap().getCoordinateHumanBase());
                }
            }
        }
    }

    @Test
    public void testPlayItemCardInEndPhase() {
        if(forcedCard.isPlayableEndPhase()) {
            match.doAction(match.getCurrentPlayer(), new MovementAction(new Coordinate('M',8)));
            match.doAction(match.getCurrentPlayer(), new EndPhaseAction());
            if(forcedCard instanceof SpotlightItemCard) {
                ((SpotlightItemCard) forcedCard).setCoordToLight(new Coordinate('K',5));
                forcedCard.play(match, p);
                assertNotNull(forcedCard.getInfo());
            }
            else {
                forcedCard.play(match, p);

                if(forcedCard instanceof AdrenalineItemCard) {
                    assertEquals(p.getMovementStep(),2);
                }
                else if(forcedCard instanceof AttackItemCard) {
                    assertTrue(p.canAttack());
                }
                else if(forcedCard instanceof SedativesItemCard) {
                    assertFalse(p.canDrawSectorCard());
                }

                else if(forcedCard instanceof TeleportItemCard) {
                    assertEquals(p.getCoordinate(), match.getGameMap().getCoordinateHumanBase());
                }
            }
        }
    }
}
