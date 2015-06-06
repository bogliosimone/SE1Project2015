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
import it.polimi.ingsw.bogliobresich.model.cards.SectorCard;
import it.polimi.ingsw.bogliobresich.model.deck.Deck;
import it.polimi.ingsw.bogliobresich.model.deck.DeckFactory;
import it.polimi.ingsw.bogliobresich.model.deck.MyDeckFactory;
import it.polimi.ingsw.bogliobresich.model.deck.exception.CardFinishedException;
import it.polimi.ingsw.bogliobresich.model.deck.exception.NoReShuffleableException;

public class CharacterDeckTest extends Deck {
    private Card captainCard;
    private Card alienOneCard;
    private Card alienTwoCard;
    private Card pilotCard;

    @Before
    public void setUp() throws Exception {
        captainCard = new CharacterCard(Characters.CAPTAIN);
        pilotCard = new CharacterCard(Characters.PILOT);
        alienOneCard = new CharacterCard(Characters.ALIENONE);
        alienTwoCard = new CharacterCard(Characters.ALIENTWO);
    }

    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void CharacterDeck() {
        DeckFactory factory = new MyDeckFactory();
        Deck characterDeck = factory.createCharacterDeck();
        assertEquals(characterDeck instanceof CharacterDeck,true);
    }

    @Test
    public void testShuffle() throws CardFinishedException {
        super.shuffle();
        assertEquals(super.isEmpty(),true);
        
        super.addCard(captainCard,1);
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
        assertEquals(true,true);
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
        assertEquals(c, captainCard);
        c = super.drawCard();
    }

    @Test(expected=NoSuchElementException.class)
    public void testDiscardCard() {
        super.discardCard(null);
    }
    
    @Test
    public void testSize() {
        assertEquals(super.size(),0);
        super.addCard(captainCard);
        super.addCard(captainCard);
        assertEquals(super.size(),2);
    }
    
    @Test
    public void testEquals() {
        assertEquals(new CharacterCard(Characters.CAPTAIN),captainCard);
    }


}
