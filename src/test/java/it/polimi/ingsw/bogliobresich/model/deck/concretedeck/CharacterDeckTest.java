package it.polimi.ingsw.bogliobresich.model.deck.concretedeck;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.bogliobresich.model.cards.CharacterCard;
import it.polimi.ingsw.bogliobresich.model.deck.Deck;
import it.polimi.ingsw.bogliobresich.model.deck.DeckFactory;
import it.polimi.ingsw.bogliobresich.model.deck.MyDeckFactory;

public class CharacterDeckTest extends Deck {

    @Before
    public void setUp() throws Exception {
        DeckFactory factory = new MyDeckFactory();
        Deck characterDeck = factory.createCharacterDeck();
    }

    @After
    public void tearDown() throws Exception {
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
        Card card = new CharacterCard(Characters);
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

    @Test
    public void testSetReShuffle() {
        fail("Not yet implemented");
    }

}
