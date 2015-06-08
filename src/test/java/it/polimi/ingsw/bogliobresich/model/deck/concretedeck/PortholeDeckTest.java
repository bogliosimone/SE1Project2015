package it.polimi.ingsw.bogliobresich.model.deck.concretedeck;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.bogliobresich.model.Characters;
import it.polimi.ingsw.bogliobresich.model.cards.Card;
import it.polimi.ingsw.bogliobresich.model.cards.CharacterCard;
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
    private int lastId;
    
    private int generateId() { return lastId++; }
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
    public void testShuffle() throws CardFinishedException {
        super.shuffle();
        assertEquals(super.isEmpty(),true);
        
        super.addCard(porthole1,generateId());
        super.shuffle();
        assertEquals(super.isEmpty(),false);
        assertEquals(super.drawCard(), new PortholeCard(ConstantsCard.PORTHOLE_BROKEN));
    }

    @Test(expected=NoReShuffleableException.class)
    public void testReShuffle() throws CardFinishedException {
        super.setReShuffle(false);
        super.addCard(porthole1,generateId());
        super.addCard(porthole2,generateId());
        super.addCard(porthole3,generateId());
        super.addCard(porthole4,generateId());
        super.reShuffle();
        assertEquals(true,true);
    }

    @Test
    public void testAddCard() {
        super.addCard(porthole1,generateId());
        assertEquals(super.isEmpty(),false);
    }

    @Test
    public void testSize() {
        assertEquals(super.size(),0);
        super.addCard(porthole1,generateId());
        super.addCard(porthole2,generateId());
        assertEquals(super.size(),2);
    }

    @Test
    public void testIsEmpty() {
        assertEquals(super.isEmpty(),true);
        super.addCard(porthole1,generateId());
        assertEquals(super.isEmpty(),false);
    }

    @Test
    public void testIsDiscardedCardsEmpty() throws CardFinishedException {
        super.addCard(porthole1,generateId());
        assertEquals(super.isDiscardedCardsEmpty(),true);
        Card c = super.drawCard();
        super.discardCard(c);
        assertEquals(super.isDiscardedCardsEmpty(),false);
    }

    @Test(expected=CardFinishedException.class)
    public void testDrawCard() throws CardFinishedException {
        super.addCard(porthole1,generateId()); 
        Card c = super.drawCard();
        assertEquals(c, porthole1);
        c = super.drawCard();
    }

    @Test(expected=NoSuchElementException.class)
    public void testDiscardCard() {
        super.discardCard(null);
    }

    @Test(expected=NoReShuffleableException.class)
    public void testSetReShuffle() throws CardFinishedException {
        super.setReShuffle(false);
        super.reShuffle();
    }
    
    @Test
    public void testEquals() {
        assertEquals(new PortholeCard(ConstantsCard.PORTHOLE_BROKEN),porthole1);
    }

}
