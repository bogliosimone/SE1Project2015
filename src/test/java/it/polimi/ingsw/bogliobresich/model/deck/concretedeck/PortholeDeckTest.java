package it.polimi.ingsw.bogliobresich.model.deck.concretedeck;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.bogliobresich.model.cards.Card;
import it.polimi.ingsw.bogliobresich.model.cards.ConstantsCard;
import it.polimi.ingsw.bogliobresich.model.cards.PortholeCard;
import it.polimi.ingsw.bogliobresich.model.deck.Deck;
import it.polimi.ingsw.bogliobresich.model.deck.DeckFactory;
import it.polimi.ingsw.bogliobresich.model.deck.MyDeckFactory;
import it.polimi.ingsw.bogliobresich.model.deck.exception.CardFinishedException;
import it.polimi.ingsw.bogliobresich.model.deck.exception.NoReShuffleableException;

public class PortholeDeckTest extends Deck {

    private Card porthole1;
    private Card porthole2;
    private Card porthole3;
    private Card porthole4;
    
    @Before
    public void setUp() throws Exception {
        porthole1 = new PortholeCard(ConstantsCard.PORTHOLE_BROKEN);
        porthole2 = new PortholeCard(ConstantsCard.PORTHOLE_WORKS);
        porthole3 = new PortholeCard(ConstantsCard.PORTHOLE_BROKEN);
        porthole4 = new PortholeCard(ConstantsCard.PORTHOLE_WORKS);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testPortholeDeck() {
        DeckFactory factory = new MyDeckFactory();
        Deck portholeDeck = factory.createPortholeDeck();
        assertEquals(portholeDeck instanceof PortholeDeck,true);
    }

    @Test
    public void testShuffle() {
        fail("Not yet implemented");
    }

    @Test
    public void testReShuffle() {
        fail("Not yet implemented");
    }

    @Test
    public void testAddCard() {
        fail("Not yet implemented");
    }

    @Test
    public void testSize() {
        fail("Not yet implemented");
    }

    @Test
    public void testIsEmpty() {
        fail("Not yet implemented");
    }

    @Test
    public void testIsDiscardedCardsEmpty() {
        fail("Not yet implemented");
    }

    @Test
    public void testDrawCard() {
        fail("Not yet implemented");
    }

    @Test
    public void testDiscardCard() {
        fail("Not yet implemented");
    }

    @Test(expected=NoReShuffleableException.class)
    public void testSetReShuffle() throws CardFinishedException {
        super.setReShuffle(false);
        super.reShuffle();
    }

}
