package it.polimi.ingsw.bogliobresich.model.match;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.bogliobresich.model.ConstantMatch;
import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.SpotlightItemCard;
import it.polimi.ingsw.bogliobresich.model.deck.exception.CardFinishedException;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.match.action.DiscardAction;
import it.polimi.ingsw.bogliobresich.model.match.action.PlayItemAction;
import it.polimi.ingsw.bogliobresich.model.match.state.HandFullState;

import org.junit.Before;
import org.junit.Test;

public class MatchHandFullTest {
    Match match;
    @Before
    public void init() {
        match = new Match(0,null);
        MatchTestUtil.initMatch(match, MatchTestUtil.generateUsers());
    }

    @Test
    public void testHandFull() {
        int cardInHand = match.getCurrentPlayer().getHand().getAllCard().size();
        ItemCard card = null;
        for(int i = 0; i < ConstantMatch.MAXCARDINHAND; i++) {
            try {
                card = (ItemCard) match.getItemDeck().drawCard();
                match.getCurrentPlayer().getHand().addCard(card);
            } catch (CardFinishedException e) { }
        }

        assertTrue(match.getCurrentPlayer().getHand().isFull());
        match.setState(new HandFullState());
        match.doAction(match.getCurrentPlayer(), new DiscardAction(card));
        assertFalse(match.getCurrentPlayer().getHand().isFull());
    }

    @Test
    public void testHandFullCannotDiscard() {
        int cardInHand = match.getCurrentPlayer().getHand().getAllCard().size();
        ItemCard card = null;
        for(int i = 0; i < ConstantMatch.MAXCARDINHAND; i++) {
            try {
                card = (ItemCard) match.getItemDeck().drawCard();
                match.getCurrentPlayer().getHand().addCard(card);
            } catch (CardFinishedException e) { }
        }
        try {
            card = (ItemCard) match.getItemDeck().drawCard();
        } catch (CardFinishedException e) {}
        assertTrue(match.getCurrentPlayer().getHand().isFull());
        match.setState(new HandFullState());
        match.doAction(match.getCurrentPlayer(), new DiscardAction(card));
        assertTrue(match.getCurrentPlayer().getHand().isFull());
    }
}
