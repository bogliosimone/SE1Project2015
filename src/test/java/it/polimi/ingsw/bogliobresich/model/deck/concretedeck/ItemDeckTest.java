package it.polimi.ingsw.bogliobresich.model.deck.concretedeck;

import static org.junit.Assert.assertEquals;
import it.polimi.ingsw.bogliobresich.model.cards.AdrenalineItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.AttackItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.Card;
import it.polimi.ingsw.bogliobresich.model.cards.ConstantsCard;
import it.polimi.ingsw.bogliobresich.model.cards.DefenceItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.SedativesItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.TeleportItemCard;
import it.polimi.ingsw.bogliobresich.model.deck.Deck;
import it.polimi.ingsw.bogliobresich.model.deck.DeckFactory;
import it.polimi.ingsw.bogliobresich.model.deck.MyDeckFactory;
import it.polimi.ingsw.bogliobresich.model.deck.exception.CardFinishedException;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ItemDeckTest extends Deck {

    private Card itemcard1;
    private Card itemcard2;
    private Card itemcard3;
    private Card itemcard4;
    private Card itemcard5;
    private int lastId;
    
    @Before
    public void setUp() throws Exception {
        itemcard1 = new AdrenalineItemCard(1);
        itemcard2 = new DefenceItemCard(2);
        itemcard3 = new AttackItemCard(3);
        itemcard4 = new SedativesItemCard(4);
        itemcard5 = new TeleportItemCard(5);
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
        
        super.addCard(itemcard1,1);
        super.shuffle();
        assertEquals(super.isEmpty(),false);
        assertEquals(super.drawCard(), new AdrenalineItemCard(1));
    }

    @Test
    public void testReShuffle() throws CardFinishedException {
        super.setReShuffle(true);
        super.addCard(itemcard1,1);
        Card c = super.drawCard();
        super.discardCard(itemcard1);
        super.reShuffle();
        c = super.drawCard();
        assertEquals(true,true);
        assertEquals(itemcard1,c);
    }

    @Test
    public void testAddCard() {
        super.addCard(itemcard1,1);
        assertEquals(super.isEmpty(),false);
    }

    @Test
    public void testSize() {
        assertEquals(super.size(),0);
        super.addCard(itemcard1,1);
        super.addCard(itemcard1,1);
        assertEquals(super.size(),2);
    }

    @Test
    public void testIsEmpty() {
        assertEquals(super.isEmpty(),true);
        super.addCard(itemcard1,1);
        assertEquals(super.isEmpty(),false);
    }

    @Test
    public void testIsDiscardedCardsEmpty() throws CardFinishedException {
        super.addCard(itemcard1,1);
        assertEquals(super.isDiscardedCardsEmpty(),true);
        Card c = super.drawCard();
        super.discardCard(c);
        assertEquals(super.isDiscardedCardsEmpty(),false);
    }

    @Test(expected=CardFinishedException.class)
    public void testDrawCard() throws CardFinishedException {
        super.addCard(itemcard1,1); 
        Card c = super.drawCard();
        assertEquals(c, itemcard1);
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
        assertEquals(new AdrenalineItemCard(1),itemcard1);
    }

}
