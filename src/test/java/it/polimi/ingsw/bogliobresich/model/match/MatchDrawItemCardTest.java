package it.polimi.ingsw.bogliobresich.model.match;

import static org.junit.Assert.*;
import it.polimi.ingsw.bogliobresich.model.match.action.DrawItemCardAction;
import it.polimi.ingsw.bogliobresich.model.match.state.DrawItemPhaseTurnState;
import it.polimi.ingsw.bogliobresich.model.player.ItemHand;
import it.polimi.ingsw.bogliobresich.model.player.Player;

import org.junit.Before;
import org.junit.Test;

public class MatchDrawItemCardTest {
    
    Match match;
    @Before
    public void init() {
        match = new Match(0,null);
        MatchTestUtil.initMatch(match, MatchTestUtil.generateUsers());
    }
    
    @Test
    public void testDrawItemCard() {
        Player player = match.getCurrentPlayer();
        ItemHand hand = player.getHand();
        int deckSize = match.getItemDeck().size();
        assertTrue(hand.isEmpty());
        match.setState(new DrawItemPhaseTurnState());
        match.doAction(player, new DrawItemCardAction());
        assertFalse(hand.isEmpty());
        assertEquals(deckSize-1,match.getItemDeck().size());        
    }
}
