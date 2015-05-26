package it.polimi.ingsw.bogliobresich.model.deck.concretedeck;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.bogliobresich.model.Characters;
import it.polimi.ingsw.bogliobresich.model.cards.Card;
import it.polimi.ingsw.bogliobresich.model.cards.CharacterCard;
import it.polimi.ingsw.bogliobresich.model.deck.Deck;
import it.polimi.ingsw.bogliobresich.model.deck.DeckFactory;
import it.polimi.ingsw.bogliobresich.model.deck.MyDeckFactory;
import it.polimi.ingsw.bogliobresich.model.deck.exception.CardFinishedException;
import it.polimi.ingsw.bogliobresich.model.deck.exception.NoReShuffleableException;

public class CharacterDeckTest extends Deck {
private DeckFactory factory;
private Deck characterDeck;
private Card captainCard;
private Card alienOneCard;
private Card alienTwoCard;
private Card pilotCard;

    @Before
    public void setUp() throws Exception {
        factory = new MyDeckFactory();
        characterDeck = factory.createCharacterDeck();
        captainCard = new CharacterCard(Characters.CAPTAIN);
        pilotCard = new CharacterCard(Characters.PILOT);
        alienOneCard = new CharacterCard(Characters.ALIENONE);
        alienTwoCard = new CharacterCard(Characters.ALIENTWO);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testShuffle() throws CardFinishedException {
        super.shuffle();
        assertEquals(super.isEmpty(),true);
        
        super.addCard(captainCard);
        super.shuffle();
        assertEquals(super.isEmpty(),false);
        assertEquals(super.drawCard(), new CharacterCard(Characters.CAPTAIN));
    }

    @Test(expected=NoReShuffleableException.class)
    public void testReShuffle() throws CardFinishedException {
        super.setReShuffle(false);
        super.addCard(captainCard);
        super.addCard(pilotCard);
        super.addCard(alienOneCard);
        super.addCard(alienTwoCard);
        super.reShuffle();
        
    }

    @Test
    public void testAddCard() {
        
        super.addCard(captainCard);
        assertEquals(super.isEmpty(),false);
    }

    @Test
    public void testIsEmpty() {
        assertEquals(super.isEmpty(),true);
        super.addCard(captainCard);
        assertEquals(super.isEmpty(),false);
    }

    @Test
    public void testIsDiscardedCardsEmpty() throws CardFinishedException {
        super.addCard(captainCard);
        assertEquals(super.isDiscardedCardsEmpty(),true);
        Card c = super.drawCard();
        super.discardCard(c);
        assertEquals(super.isDiscardedCardsEmpty(),false);
    }

    @Test(expected=CardFinishedException.class)
    public void testDrawCard() throws CardFinishedException {
        super.addCard(captainCard); 
        Card c = super.drawCard();
        assertEquals(c, new CharacterCard(Characters.CAPTAIN));
        c = super.drawCard();
    }

    @Test(expected=NoSuchElementException.class)
    public void testDiscardCard() {
        super.discardCard(null);
    }


}
