package it.polimi.ingsw.bogliobresich.model.deck.concretedeck;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.bogliobresich.model.cards.Card;
import it.polimi.ingsw.bogliobresich.model.cards.ConstantsCard;
import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.SectorCard;
import it.polimi.ingsw.bogliobresich.model.deck.Deck;
import it.polimi.ingsw.bogliobresich.model.deck.DeckFactory;
import it.polimi.ingsw.bogliobresich.model.deck.MyDeckFactory;
import it.polimi.ingsw.bogliobresich.model.deck.exception.CardFinishedException;

public class ItemDeckTest extends Deck {

    private Card itemcard1;
    private Card itemcard2;
    private Card itemcard3;
    private Card itemcard4;
    private Card itemcard5;
    @Before
    public void setUp() throws Exception {
        itemcard1 = new ItemCard(ConstantsCard.ADRENALINE);
        itemcard2 = new ItemCard(ConstantsCard.DEFENCE);
        itemcard3 = new ItemCard(ConstantsCard.ATTACK);
        itemcard4 = new ItemCard(ConstantsCard.SEDATIVES);
        itemcard5 = new ItemCard(ConstantsCard.TELEPORT);
        
    }

    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void testItemDeck() {
        DeckFactory factory = new MyDeckFactory();
        Deck itemDeck = factory.createItemDeck();
        assertEquals(itemDeck instanceof ItemDeck,true);
    }

    @Test
    public void testShuffle() throws CardFinishedException {
        super.shuffle();
        assertEquals(super.isEmpty(),true);
        
        super.addCard(itemcard1);
        super.shuffle();
        assertEquals(super.isEmpty(),false);
        assertEquals(super.drawCard(), new ItemCard(ConstantsCard.ADRENALINE));
    }

    @Test
    public void testReShuffle() throws CardFinishedException {
        super.setReShuffle(true);
        super.addCard(itemcard1);
        super.addCard(itemcard2);
        super.addCard(itemcard3);
        super.addCard(itemcard4);
        super.reShuffle();
        assertEquals(true,true);
    }

    @Test
    public void testAddCard() {
        super.addCard(itemcard1);
        assertEquals(super.isEmpty(),false);
        super.addCard(null);
        //TODO
    }

    @Test
    public void testSize() {
        assertEquals(super.size(),0);
        super.addCard(itemcard1);
        super.addCard(itemcard1);
        assertEquals(super.size(),2);
    }

    @Test
    public void testIsEmpty() {
        assertEquals(super.isEmpty(),true);
        super.addCard(itemcard1);
        assertEquals(super.isEmpty(),false);
    }

    @Test
    public void testIsDiscardedCardsEmpty() throws CardFinishedException {
        super.addCard(itemcard1);
        assertEquals(super.isDiscardedCardsEmpty(),true);
        Card c = super.drawCard();
        super.discardCard(c);
        assertEquals(super.isDiscardedCardsEmpty(),false);
    }

    @Test(expected=CardFinishedException.class)
    public void testDrawCard() throws CardFinishedException {
        super.addCard(itemcard1); 
        Card c = super.drawCard();
        assertEquals(c, new ItemCard(ConstantsCard.ADRENALINE));
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
        fail("not implemented");
    }

}
