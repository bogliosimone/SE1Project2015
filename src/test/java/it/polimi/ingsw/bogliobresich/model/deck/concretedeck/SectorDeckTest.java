package it.polimi.ingsw.bogliobresich.model.deck.concretedeck;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.bogliobresich.model.cards.Card;
import it.polimi.ingsw.bogliobresich.model.cards.ConstantsCard;
import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.PortholeCard;
import it.polimi.ingsw.bogliobresich.model.cards.SectorCard;
import it.polimi.ingsw.bogliobresich.model.deck.Deck;
import it.polimi.ingsw.bogliobresich.model.deck.DeckFactory;
import it.polimi.ingsw.bogliobresich.model.deck.MyDeckFactory;
import it.polimi.ingsw.bogliobresich.model.deck.exception.CardFinishedException;

public class SectorDeckTest extends Deck {

    private Card sectorcard1;
    private Card sectorcard2;
    private Card sectorcard3;
    private Card sectorcard4;
    private Card sectorcard5;
    private Card sectorcard6;
    private int lastId;
    
    private int generateId() { return lastId++; }
    @Before
    public void setUp() throws Exception {
        sectorcard1 = new SectorCard(ConstantsCard.NOISE_ANY_SECTOR,false);
        sectorcard2 = new SectorCard(ConstantsCard.NOISE_ANY_SECTOR,true);
        sectorcard3 = new SectorCard(ConstantsCard.NOISE_MY_SECTOR,false);
        sectorcard4 = new SectorCard(ConstantsCard.NOISE_MY_SECTOR,true);
        sectorcard5 = new SectorCard(ConstantsCard.SILENCE,false);
        sectorcard6 = new SectorCard(ConstantsCard.SILENCE,true);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSectorDeck() {
        DeckFactory factory = new MyDeckFactory();
        Deck sectorDeck = factory.createSectorDeck();
        assertEquals(sectorDeck instanceof SectorDeck,true);
    }
    
    @Test
    public void testShuffle() throws CardFinishedException {
        super.shuffle();
        assertEquals(super.isEmpty(),true);
        
        super.addCard(sectorcard1,generateId());
        super.shuffle();
        assertEquals(super.isEmpty(),false);
        assertEquals(super.drawCard(), new SectorCard(ConstantsCard.NOISE_ANY_SECTOR,false));
    }

    @Test
    public void testReShuffle() throws CardFinishedException {
        super.setReShuffle(true);
        super.addCard(sectorcard1,generateId());
        Card c = super.drawCard();
        super.discardCard(sectorcard1);
        super.reShuffle();
        c = super.drawCard();
        assertEquals(true,true);
        assertEquals(sectorcard1,c);
    }

    @Test
    public void testAddCard() {
        super.addCard(sectorcard1,generateId());
        assertEquals(super.isEmpty(),false);
    }

    @Test
    public void testSize() {
        assertEquals(super.size(),0);
        super.addCard(sectorcard1,generateId());
        super.addCard(sectorcard1,generateId());
        assertEquals(super.size(),2);
    }

    @Test
    public void testIsEmpty() {
        assertEquals(super.isEmpty(),true);
        super.addCard(sectorcard1,generateId());
        assertEquals(super.isEmpty(),false);
    }

    @Test
    public void testIsDiscardedCardsEmpty() throws CardFinishedException {
        super.addCard(sectorcard1,generateId());
        assertEquals(super.isDiscardedCardsEmpty(),true);
        Card c = super.drawCard();
        super.discardCard(c);
        assertEquals(super.isDiscardedCardsEmpty(),false);
    }

    @Test(expected=CardFinishedException.class)
    public void testDrawCard() throws CardFinishedException {
        super.addCard(sectorcard1,generateId()); 
        Card c = super.drawCard();
        assertEquals(c, sectorcard1);
        c = super.drawCard();
    }

    @Test(expected=NoSuchElementException.class)
    public void testDiscardCard() {
        super.discardCard(null);
    }

    @Test(expected=CardFinishedException.class)
    public void testSetReShuffle() throws CardFinishedException {
        super.setReShuffle(true);
        super.reShuffle();
        assertEquals(true,true);
    }
    
    @Test
    public void testEquals() {
        assertEquals(new SectorCard(ConstantsCard.NOISE_ANY_SECTOR,false),sectorcard1);
    }

}
